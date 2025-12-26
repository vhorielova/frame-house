/**
 * This is a reusable button component.
 */
import './Components.css'

interface Props {
    children: string;
    onClick: () => void;}


export default function Button({ children, onClick }: Props) {


    return <button className="button submit-button" onClick={onClick}>
        {children}
    </button>
}