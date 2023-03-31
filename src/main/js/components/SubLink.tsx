import React from "react";
import { Link } from "react-router-dom";

const SubLink = (props: {section: string, type: string, setResults: React.Dispatch<React.SetStateAction<any>>}) => {
    const {section, type, setResults} = props;
    const path = type.toLowerCase();

    const handleClick = () => {
        const form = document.getElementsByTagName('form')[0];
        const inputs = Array.from(form.getElementsByTagName('input'))

        inputs.map((input) => {
            input.value = "";
        });

        setResults(null);
    }

    return (
        <Link to={`/${section}/${path}`} state={{path: path}} className="sub-links" onClick={handleClick} >{type}</Link>
    )
}

export default SubLink;