import './Components.css'

interface Props {
    name: string;
    placeholder: string;
    onChange: (result: string) => void;
}

/**
 * A template for making from fields.
 * Takes field's name, placeholder's text and onChange event handler.
 */
export default function Field({name, placeholder, onChange}: Props) {

    return <div className="field">
        <span className="field-name">{name}</span>
        <input className="field-input" type="text" placeholder={placeholder} onChange={(e) => onChange(e.target.value)}></input>
    </div>

}