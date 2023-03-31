import React from "react";
import { Link } from "react-router-dom";

const SubLink = (props: {section: string, type: string}) => {
    const {section, type} = props;
    const path = type.toLowerCase();
    return (
        <Link to={`/${section}/${path}`} state={{path: path}} className="sub-links" >{type}</Link>
    )
}

export default SubLink;