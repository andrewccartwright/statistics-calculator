import React from "react";
import SubLink from "../SubLink";
import SubNavbar from "../SubNavbar";

const DistNavbar = () => {
    const types = ["Binomial", "Exponential", "Geometric", "Normal", "Poisson"]

    return (
        <div id="dist-navbar">
            <SubNavbar section={"distributions"} types={types} />
        </div>
    )
}

export default DistNavbar;