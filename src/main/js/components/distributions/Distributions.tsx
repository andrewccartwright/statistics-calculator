import React, { useEffect, useState } from "react";
import { Route, Routes } from "react-router-dom";
import DistNavbar from "./DistNavbar";
import { distArray } from "../util/DefaultValues";
import DistItem from "./DistItem";

const Distributions = () => {
    const [content, setContent] = useState<any>();
    const [results, setResults] = useState<any>();

    return (
        <div className="main-section">
            <DistNavbar setResults={setResults} />

            <Routes>
                {
                    distArray.map((distItem) => {
                        return <Route path={distItem["path"]} key={distItem["path"]} element={<DistItem content={distItem['defaultValue']} setContent={setContent} results={results} setResults={setResults} path={distItem["path"]} />} />
                    })
                }
            </Routes>
        </div>
    )
}

export default Distributions;