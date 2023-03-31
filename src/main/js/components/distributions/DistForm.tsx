import axios from "axios";
import React, { FormEventHandler, SyntheticEvent } from "react";
import Form from "../Form";
import { Binomial, Exponential, Geometric, Normal, Poisson} from '../util/DistTypes';

const DistForm = (props: {content: any, setContent: React.Dispatch<React.SetStateAction<any>>, path: string}) => {
    const { content, setContent, path } = props;

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
                console.log(res);
            })
            .catch((err) => {
                console.log(err);
            });
    }

    return (
        <div>
            <Form content={content} handleSubmit={handleSubmit} />
        </div>
    )
}

export default DistForm;