import React, { useState } from "react";
import { Normal } from "../../util/DistTypes";
import DistForm from "../DistForm";

const defaultValue:  Normal = {
    "x": null,
    "popMean": null,
    "popStDev": null
}

const Normal = () => {
    const [ normal, setNormal] = useState<Normal>(defaultValue);

    return (
        <div>
            <DistForm content={normal} setContent={setNormal} path="/normal" />
        </div>
    )
}

export default Normal;