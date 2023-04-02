import React, { useState } from "react";
import { Navigate, Route, Routes } from "react-router-dom";
import { distArray } from "../util/DistDefaultValues";
import Item from "../Item";
import SubNavbar from "../SubNavbar";

const Distributions = () => {
    const [content, setContent] = useState<any>();
    const [results, setResults] = useState<any>();
    const types = ["Binomial", "Exponential", "Geometric", "Normal", "Poisson"];
    const section = "distributions";

    return (
        <div className="main-section">
            <SubNavbar section={section} types={types} setResults={setResults} />

            <Routes>
                <Route path="/" element={<Navigate replace to="/distributions/binomial" />} />
                {
                    distArray.map((distItem) => {
                        return <Route path={distItem["path"]} key={distItem["path"]} element={<Item content={distItem['defaultValue']} setContent={setContent} results={results} setResults={setResults} path={distItem["path"]} section={section} />} />
                    })
                }
            </Routes>
        </div>
    )
}

export default Distributions;