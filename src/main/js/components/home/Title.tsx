import React from "react";

const Title = (props: {title: string}) => {
    const { title } = props;

    return (
        <h2 className="title">{title}</h2>
    )
}

export default Title;