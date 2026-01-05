import { useState } from "react";

import "./Fields.css"
import FieldErrors from "./FieldErrors";

interface Props {
    name: string;
    placeholder: string;
    values: string[];
    onChange: (resultList: string[]) => void;
    hints?: string[];
    errors?: string[];
}


export default function ListInput({ name, placeholder, values, onChange, hints=[], errors=[] }: Props) {
    
    // Hooks
    const [value, setValue] = useState('');

    // Options for input hints
    const optionsId = name + "-options";
    const options = hints.map((v) => <option key={v}>{v}</option>);
    // Added elements
    const valueElements = values.map((v) => 
        <ValueElement key={v} onRemove={() => removeValueFromList(v)}>
            {v.charAt(0).toUpperCase() + v.slice(1)}
        </ValueElement>);


    // Value list management functions
    function addValueToList(newValue: string) {
        newValue = newValue.trim().toLowerCase();
        if (values.includes(newValue)) {
            console.error(`Add value: Element '${newValue}' is already on the list!`);
            return;
        }
        if (!value) {
            console.error(`Add value: Element is empty!`);
            return;
        }
        const newValues = [...values, newValue];
        onChange(newValues);
        setValue('');
    }

    function removeValueFromList(oldValue: string) {

        const index = values.indexOf(oldValue);
        if (index === -1) {
            console.error(`Add value: Element '${oldValue}' is not on the list! List is ${values}`);
            for (const v of values){
                console.log(v);
            }
            return;
        }

        const newValues = [...values];

        newValues.splice(index, 1);
        onChange(newValues);
    }


    return <div className="field">
        
        <datalist id={optionsId}>
            {options}
        </datalist>

        <span className="field-name">{name}</span>

        <div className="list-input-bar">
            <input 
                className="field-box field-placeholder list-input" 
                list={optionsId} type="text" placeholder={placeholder}
                onChange={(e) => {setValue(e.target.value)}}
                onKeyDown={(e) => {
                    if (e.key === 'Enter') {
                        e.preventDefault();
                        addValueToList(value);
                    }
                }}
                value={value}
            />
            <button 
                className="button plus-button" 
                type="button" 
                onClick={()=>{addValueToList(value)}}
            >+</button>
        </div>
        <div className="list-input-chosen-container">
            {valueElements}
        </div>

        <FieldErrors errors={errors}/>
        
    </div>

}

interface ValueElementProps {
    children: string;
    onRemove: () => void;
}

function ValueElement({ children, onRemove }: ValueElementProps) {

    return <div className="list-input-chosen-item">
        <div className="list-input-text">{children}</div>
        <button 
            className="remove-button"
            type="button" onClick={onRemove}
        >✕</button>        
    </div>
    
}