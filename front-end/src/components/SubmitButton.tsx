/**
 * This is a reusable button component.
 */
import './Components.css'

interface Props {
    children: string;
}


export default function SubmitButton({ children }: Props) {


    return <button type="submit" className="button accent-hoover submit-button">
        {children}
    </button>
}