import FieldErrors from './FieldErrors';

import '../../style-utilities/borders.css'
import './fields.css'

interface Props {
    name: string;
    placeholder: string;
    onChange: (result: string) => void;
    errors?: string[];
}

/**
 * A template for making from fields.
 * Takes field's name, placeholder's text and onChange event handler.
 */
export default function Input({name, placeholder, onChange, errors=[]}: Props) {

    return <div className="field">
        <span className="field-name">{name}</span>
        <input className="secondary-border accent-hoover accent-focus field-box field-input field-placeholder" type="text" placeholder={placeholder} onChange={(e) => onChange(e.target.value)}></input>
        
        <FieldErrors errors={errors}/>
        
    </div>

}