import React from "react";
import '../../css/Home.scss';
import { Link } from "react-router-dom";
import Title from "./Title";
import ListItem from "./ListItem";

const sections = [
    {
        "title": "Summary Statistics",
        "links": [
            {
                "to": "/statistics",
                "name": "One-Variable Summary Statistics"
            }
        ]
    },
    {
        "title": "Distributions",
        "links": [
            {
                "to": "/distributions/binomial",
                "name": "Binomial"
            },
            {
                "to": "/distributions/exponential",
                "name": "Exponential"
            },
            {
                "to": "/distributions/geometric",
                "name": "Geometric"
            },
            {
                "to": "/distributions/normal",
                "name": "Normal"
            },
            {
                "to": "/distributions/poisson",
                "name": "Poisson"
            }
        ]
    },
    {
        "title": "Scores",
        "links": [
            {
                "to": "/scores/z score",
                "name": "Z Score"
            },
            {
                "to": "/scores/t score",
                "name": "T Score"
            }
        ]
    }
]

const Home = () => {
    return (
        <div id='home-section' className="main-section">
            <div id='home-links-section'>
                {
                    sections.map((section) => {
                        return (
                            <div key={section.title}>
                                <Title title={section.title} />
                                <hr />
                                {
                                    section.links.map((link) => {
                                        return (
                                            <div key={link.name}>
                                                <ListItem to={link.to} name={link.name} />
                                            </div>
                                        )
                                    })
                                }
                            </div>
                        )
                    })
                }
            </div>
        </div>
    )
}

export default Home;