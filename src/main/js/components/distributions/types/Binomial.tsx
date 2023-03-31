import React, { useState } from "react";
import { Binomial } from "../../util/DistTypes";
import DistForm from "../DistForm";

const defaultValue: Binomial = {
    "n": null,
    "x": null,
    "p": null
}

const Binomial = () => {
    const [binomial, setBinomial] = useState<Binomial>(defaultValue);

    return (
        <div>
            <DistForm content={binomial} setContent={setBinomial} path="/binomial" />
        </div>
    )
}

export default Binomial;