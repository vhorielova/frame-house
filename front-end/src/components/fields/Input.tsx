import FieldErrors from './FieldErrors';
import './Fields.css'

interface Props {
    name: string;
    placeholder: string;
    value: string;
    onChange: (result: string) => void;
    errors?: string[];
}

/**
 * A template for making from fields.
 * Takes field's name, placeholder's text and onChange event handler.
 */
export default function Input({name, placeholder, value, onChange, errors=[]}: Props) {

    return <div className="field">
        <span className="field-name">{name}</span>
        <input 
            className="field-box field-input field-placeholder" 
            type="text" 
            placeholder={placeholder} 
            value={value} onChange={(e) => onChange(e.target.value)}>
        </input>
        
        <FieldErrors errors={errors}/>
        
    </div>

}