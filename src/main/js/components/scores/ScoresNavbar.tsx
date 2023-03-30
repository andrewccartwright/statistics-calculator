import React from "react";
import SubNavbar from "../SubNavbar";

const ScoresNavbar = () => {
    const types = ["T Score", "Z Score"];

    return (
        <div>
            <SubNavbar types={types} />
        </div>
    )
}

export default ScoresNavbar;