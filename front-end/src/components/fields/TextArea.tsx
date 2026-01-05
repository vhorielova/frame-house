import FieldErrors from './FieldErrors';
import './Fields.css'

interface Props {
    name: string;
    placeholder: string;
    value: string;
    onChange: (result: string) => void;
    errors?: string[];
}

export default function TextArea({name, placeholder, value, onChange, errors=[]}: Props) {

    return <div className="field">
        <span className="field-name">{name}</span>
        <textarea 
            className="field-box field-text-area field-placeholder" 
            placeholder={placeholder} 
            value={value} 
            onChange={(e) => onChange(e.target.value)}>
         </textarea>
        <FieldErrors errors={errors}/>
    </div>

    

}