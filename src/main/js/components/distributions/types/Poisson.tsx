import React, { useState } from "react";
import { Poisson } from "../../util/DistTypes";
import DistForm from "../DistForm";

const defaultValue:  Poisson = {
    "lambda": null,
    "x": null
}

const Poisson = () => {
    const [ poisson, setPoisson] = useState<Poisson>(defaultValue);

    return (
        <div>
            {poisson && <DistForm content={poisson} setContent={setPoisson} path="/poisson" />}
        </div>
    )
}

export default Poisson;