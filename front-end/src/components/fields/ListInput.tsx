import { useState } from "react";

import "./Fields.css"


export default function ListInput() {

    const [value, setValue] = useState('');
    const [values, setValues] = useState<string[]>([]);

    function addValueToList(newValue: string) {
        setValues([...values, newValue]);
    }

    const variants = ["Fantasy", "Comedy", "Romance", "Action"];
    const options = variants.map((v) => <option>{v}</option>);
    const name = "List input";

    const valueElements = values.map((v) => <ValueElement>{v}</ValueElement>);

    return <div className="field">

        <datalist id="options">
            {options}
        </datalist>

        <span className="field-name">{name}</span>

        <div className="list-input-bar">
            <input 
                className="field-box field-placeholder list-input" 
                list="options" type="text" placeholder="Choose it"
                onChange={(e) => {setValue(e.target.value)}}
            />
            <button className="button plus-button" onClick={()=>{addValueToList(value)}}>+</button>
        </div>
        <div className="list-input-chosen-container">
            {valueElements}
        </div>
        
    </div>

}

interface ValueElementProps {
    children: string;
}

function ValueElement({ children }: ValueElementProps) {

    return <div className="list-input-chosen-item" key={children}>
        <div className="list-input-text">{children}</div>
        <button className="remove-button">-</button>        
    </div>
    
}