import React from "react";
import { Link } from "react-router-dom";

const Navbar = () => {
    return (
        <div className="navbar-section">
            <div id="links">
                <Link to="/" className="nav-links">Home</Link>
                <Link to="/statistics" className="nav-links">Summary Statistics</Link>
                <Link to="/distributions" className="nav-links">Probability Distributions</Link>
                <Link to="/scores" className="nav-links">Scores</Link>
            </div>
        </div>
    )
}

export default Navbar;