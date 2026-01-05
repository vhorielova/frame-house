import { useRef, useState, type ChangeEvent } from "react";
import FieldErrors from "./FieldErrors";


interface Props {
    name: string;
    onChange: (file: File) => void;
    errors?: string[];
}


export default function FileInput({ name, onChange, errors=[] }: Props) {

    const [filename, setFilename] = useState('');

    const hiddenFileInput = useRef<HTMLInputElement>(null);

    // Rendering placeholder or filename
    let innerText;
    if (filename) {
        innerText = <>
            <span className="field-file-pseudo-placeholder">
                File is uploaded
            </span>
            <br/>
            {filename}
        </>
    } else {
        innerText = <span className="field-file-pseudo-placeholder">
            Click to select a file
        </span>;
    }

    function handleClick() {
        hiddenFileInput.current?.click();
    };

    function handleChange(event: ChangeEvent<HTMLInputElement>) {
        const fileUploaded = event.target.files?.[0];

        if (fileUploaded) {
            setFilename(fileUploaded.name);
            onChange(fileUploaded);
        }

    }

    return <div className="field">
        <span className="field-name">{name}</span>

        {/* Is not rendered */}
        <input 
            className="field-file-invisible" 
            ref={hiddenFileInput}
            type="file"
            accept="image/*"
            onChange={handleChange}
        />

        <button 
            className="field-box field-file-input" 
            type="button"
            onClick={handleClick}
        >
            {innerText}
        </button>

        <FieldErrors errors={errors}/>
    </div>
}