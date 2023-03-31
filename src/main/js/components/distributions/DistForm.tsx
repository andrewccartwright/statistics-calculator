import axios from "axios";
import React, { SyntheticEvent, useState } from "react";
import Form from "../Form";

const DistForm = (props: {content: any, setContent: React.Dispatch<React.SetStateAction<any>>, setResults: React.Dispatch<React.SetStateAction<any>>, path: string}) => {
    const { content, setContent, setResults, path } = props;

    const handleSubmit = (event: SyntheticEvent) => {
        event.preventDefault();
        const target = event.target as HTMLFormElement;

        const inputs = Array.from(target.getElementsByTagName('input'));
        const values = inputs.map((input) => {
            return Number(input.value);
        });

        const tempContent = content;

        Object.keys(content).map((item, index) => {
            tempContent[item] = values[index];
        });

        setContent(tempContent);

        axios.post(`/distributions${path}`, content)
            .then((res) => {
                setResults(res.data);
            })
            .catch((err) => {
                console.log(err);
            });
    }

    return (
        <div>
            <Form content={content} setContent={setContent} handleSubmit={handleSubmit} />
        </div>
    )
}

export default DistForm;