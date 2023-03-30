import React from "react";
import SubLink from "../SubLink";
import SubNavbar from "../SubNavbar";

const DistNavbar = () => {
    const types = ["Binomial", "Exponential", "Geometric", "Normal", "Poisson", "Student's T"]

    return (
        <div id="dist-navbar">
            <SubNavbar types={types} />
        </div>
    )
}

export default DistNavbar;