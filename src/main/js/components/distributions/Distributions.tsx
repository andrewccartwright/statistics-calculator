import React from "react";
import { Route, Routes } from "react-router-dom";
import DistNavbar from "./DistNavbar";
import BinomialForm from "./types/BinomialForm";

const Distributions = () => {
    return (
        <div className="main-section">
            <DistNavbar />

            <Routes>
                <Route path="/binomial" element={<BinomialForm />} />
            </Routes>
        </div>
    )
}

export default Distributions;