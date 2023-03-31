import React from "react";
import SubLink from "../SubLink";
import SubNavbar from "../SubNavbar";

const DistNavbar = (props: {setResults: React.Dispatch<React.SetStateAction<any>>}) => {
    const types = ["Binomial", "Exponential", "Geometric", "Normal", "Poisson"]

    return (
        <div id="dist-navbar">
            <SubNavbar section={"distributions"} types={types} setResults={props.setResults} />
        </div>
    )
}

export default DistNavbar;