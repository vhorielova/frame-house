/**
 * This is a reusable Form component.
 */
import { type FormEvent, type ReactNode } from "react";
import '../style-utilities/borders.css'
import './Form.css'


interface Props {
    children: ReactNode[];
    title: string;
    onSubmit: (e: FormEvent) => void;
}
/**
 * A template for making forms. Takes title as component and children for inputs and ect.
 */
export default function Form({ children, title, onSubmit }: Props) {

    return <form className="primary-border form" onSubmit={onSubmit}>
        <h2>{title}</h2>
        {children}
    </form>
}