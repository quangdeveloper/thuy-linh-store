
const InvalidField = (props) =>{

    return (
        <span style={{color: "red"}}>
            {
                props.touch && props.error ?
                props.error
                : null
            }
        </span>
    )
}

export default InvalidField