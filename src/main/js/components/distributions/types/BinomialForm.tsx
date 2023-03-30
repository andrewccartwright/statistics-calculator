import React, { useEffect, useState } from "react";
import { Binomial } from "../../util/DistTypes";
import DistForm from "../DistForm";

const BinomialForm = () => {
    const [binomial, setBinomial] = useState<Binomial>();

    return (
        <div>
            <DistForm />
        </div>
    )
}

export default BinomialForm;