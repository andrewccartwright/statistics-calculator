import React from "react";
import SubLink from "./SubLink";

const SubNavbar = (props: {types: string[]}) => {
    const { types } = props;

    return (
        <div className="sub-navbar">
            <div className="sub-links-section links">
                {
                    types.map((type) => {
                        return <SubLink type={type} key={type}/>
                    })
                }
            </div>
        </div>
    )
}

export default SubNavbar;