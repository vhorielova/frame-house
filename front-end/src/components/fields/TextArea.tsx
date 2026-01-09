import FieldErrors from './FieldErrors';

import '../../style-utilities/borders.css'
import './fields.css'

interface Props {
    name: string;
    placeholder: string;
    onChange: (result: string) => void;
    errors?: string[];
}

export default function TextArea({name, placeholder, onChange, errors=[]}: Props) {

    return <div className="field">
        <span className="field-name">{name}</span>
        <textarea className="secondary-border accent-hoover accent-focus field-box field-text-area field-placeholder" placeholder={placeholder} onChange={(e) => onChange(e.target.value)}></textarea>

        <FieldErrors errors={errors}/>
    </div>

    

}