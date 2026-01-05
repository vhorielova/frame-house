interface Props {
    errors: string[]
}

export default function FieldErrors( { errors }: Props) {

    const errorElements = errors.map(e => <div className='error'>{e}</div>);
    
    return <div className="errors">
        {errorElements}
    </div>

}