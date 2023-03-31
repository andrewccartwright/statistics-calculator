import React, { useEffect, useState } from "react";
import { Route, Routes } from "react-router-dom";
import DistNavbar from "./DistNavbar";
import { distArray } from "../util/DefaultValues";
import DistItem from "./DistItem";

const Distributions = () => {
    const [array, setArray] = useState(distArray);

    return (
        <div className="main-section">
            <DistNavbar />

            <Routes>
                {
                    array.map((distItem) => {
                        return <Route key={distItem["path"]} path={distItem["path"]} element={<DistItem defaultContent={distItem['defaultValue']} path={distItem["path"]} />} />
                    })
                }
            </Routes>
        </div>
    )
}

export default Distributions;