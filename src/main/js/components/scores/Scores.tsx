import React, { useState } from "react";
import { Navigate, Route, Routes } from "react-router-dom";
import SubNavbar from "../SubNavbar";
import { scoreArray } from "../util/ScoreDefaultValues";
import Item from "../Item";

const Scores = () => {
    const [content, setContent] = useState<any>();
    const [results, setResults] = useState<any>();
    const types = ["T Score", "Z Score"];
    const section = "scores"

    return (
        <div className="main-section">
            <SubNavbar section={section} types={types} setResults={setResults} />

            <Routes>
                <Route path="/" element={<Navigate replace to="/scores/t score" />} />

                { scoreArray.map((scoreItem) => {
                    return <Route path={scoreItem["path"]} key={scoreItem["path"]} element={<Item content={scoreItem['defaultValue']} setContent={setContent} results={results} setResults={setResults} path={scoreItem["path"]} section={section} />} />
                })}
            </Routes>
        </div>
    )
}

export default Scores;