import React from "react";
import { HashRouter as Router, Routes, Route, createHashRouter, createRoutesFromElements, RouterProvider } from 'react-router-dom';
import Distributions from "./distributions/Distributions";
import Home from "./home/Home";
import Navbar from "./Navbar";
import Scores from "./scores/Scores";
import Statistics from "./statistics/Statistics";

const App = () => {

    return (
        <div className="text-center">
            <Navbar />
            <Routes>
                <Route path="/" element={<Home />} />
                <Route path="/statistics/*" element={<Statistics />} />
                <Route path="/distributions/*" element={<Distributions />} />
                <Route path="/scores/*" element={<Scores />} />
            </Routes>
            
        </div>
    )
}

export default App;