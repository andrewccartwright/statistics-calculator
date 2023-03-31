import React, { useState } from "react";
import { Geometric } from "../../util/DistTypes";
import DistForm from "../DistForm";

const defaultValue: Geometric = {
    "x": null,
    "p": null,
    "includesSuccess": null
}

const Geometric = () => {
    const [geometric, setGeometric] = useState<Geometric>(defaultValue); 

    return (
        <div>
            <DistForm content={geometric} setContent={setGeometric} path="/geometric" />
        </div>
    )
}

export default Geometric;