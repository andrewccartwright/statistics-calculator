import React from "react";
import { Route, Routes } from "react-router-dom";
import DistNavbar from "./DistNavbar";
import Binomial from "./types/Binomial";
import Exponential from "./types/Exponential";
import Geometric from "./types/Geometric";
import Normal from "./types/Normal";
import Poisson from "./types/Poisson";

const Distributions = () => {
    return (
        <div className="main-section">
            <DistNavbar />

            <Routes>
                <Route path="/binomial" element={<Binomial />} />
                <Route path="/exponential" element={<Exponential />} />
                <Route path="/geometric" element={<Geometric />} />
                <Route path="/normal" element={<Normal />} />
                <Route path="/poisson" element={<Poisson />} />
            </Routes>
        </div>
    )
}

export default Distributions;