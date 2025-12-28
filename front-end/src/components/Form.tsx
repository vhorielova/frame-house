/**
 * This is a reusable Form component.
 */
import { type ReactNode } from "react";
import './Components.css'


interface Props {
    children: ReactNode[];
    title: string;
}
/**
 * A template for making forms. Takes title as component and children for inputs and ect.
 */
export default function Form({ children, title }: Props) {

    return <div className="form">
        <h2>{title}</h2>
        {children}
    </div>
}