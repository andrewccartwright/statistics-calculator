import React from "react";
import { Link } from "react-router-dom";

const ListItem = (props: {to: string, name: string}) => {
    const { to, name } = props;

    return (
        <div>
            <li className="home-nav-list"><Link to={to} className="home-links">{name}</Link></li>
            <hr />
        </div>
    )
}

export default ListItem;