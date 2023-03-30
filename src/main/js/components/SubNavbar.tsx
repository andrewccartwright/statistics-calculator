import React from "react";
import SubLink from "./SubLink";

const SubNavbar = (props: {section: string, types: string[]}) => {
    const { section, types } = props;

    return (
        <div className="sub-navbar">
            <div className="sub-links-section links">
                {
                    types.map((type) => {
                        return <SubLink section={section} type={type} key={type}/>
                    })
                }
            </div>
        </div>
    )
}

export default SubNavbar;