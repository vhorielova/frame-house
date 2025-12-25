import './Components.css'

interface Props {
    name: string;
    placeholder: string;
    onChange: (result: string) => void;
}

export default function TextArea({name, placeholder, onChange}: Props) {

    return <div className="field">
        <span className="field-name">{name}</span>
        <textarea className="field-box field-text-area field-placeholder" placeholder={placeholder} onChange={(e) => onChange(e.target.value)}></textarea>
    </div>

    

}