import React, { useState } from "react";
import { Exponential } from "../../util/DistTypes";
import DistForm from "../DistForm";

const defaultValue: Exponential = {
    "lambda": null,
    "x": null
}

const Exponential = () => {
    const [exponential, setExponential] = useState<Exponential>(defaultValue);

    return (
        <div>
            <DistForm content={exponential} setContent={setExponential} path="/exponential" />
        </div>
    )
}

export default Exponential;