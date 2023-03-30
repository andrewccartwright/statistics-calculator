import React from "react";
import { Link } from "react-router-dom";

const SubLink = (props: {type: string}) => {
    const {type} = props;
    const path = type.toLowerCase();
    return (
        <Link to={`/distributions/${path}`} className="dist-links" >{type}</Link>
    )
}

export default SubLink;