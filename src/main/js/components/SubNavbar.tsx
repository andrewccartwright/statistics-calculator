import React from "react";
import SubLink from "./SubLink";

const SubNavbar = (props: {section: string, types: string[], setResults: React.Dispatch<React.SetStateAction<any>>}) => {
    const { section, types, setResults } = props;

    return (
        <div className="sub-navbar">
            <div className="sub-links-section links">
                {
                    types.map((type) => {
                        return <SubLink section={section} type={type} key={type} setResults={setResults} />
                    })
                }
            </div>
        </div>
    )
}

export default SubNavbar;