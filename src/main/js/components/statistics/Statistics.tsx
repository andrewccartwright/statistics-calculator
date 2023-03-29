import React from "react";
import { Route, Routes } from "react-router-dom";
import SummaryStatsForm from "./SummaryStatsForm";

const Statistics = () => {
    return (
        <div className="main-section">
            <Routes>
                <Route path="/" element={<SummaryStatsForm />} />
            </Routes>
        </div>
    )
}

export default Statistics;