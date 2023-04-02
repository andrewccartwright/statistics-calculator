import axios from "axios";
import React, { SyntheticEvent } from "react";

const Form = (props: {content: any, setContent: React.Dispatch<React.SetStateAction<any>>, setResults: React.Dispatch<React.SetStateAction<any>>, path: string}) => {
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

        axios.post(`${path}`, content)
            .then((res) => {
                setResults(res.data);
            })
            .catch((err) => {
                console.log(err);
            });
    }

    return (
        <form onSubmit={handleSubmit} method="post">
            {
                Object.keys(content).map((item) => {
                    if (item == "includesSuccess") {
                        return (
                            <div key={item} className="form-item">
                                <label htmlFor={`${item}-input`} className="form-label">Select a value for {item}:</label>
                                <input name={`${item}-input`} type="checkbox" />
                            </div>
                        )
                    }
                    

                    return (
                        <div key={item} className="form-item">
                            <label htmlFor={`${item}-input`} className="form-label">Enter a value for {item}:</label>
                            <input name={`${item}-input`} type="number" className="form-input" min={0} max={item == "p" ? 1 : null} step=".01" required />
                        </div>
                    )
                })
            }

            <button className="submit-btn">Submit</button>
        </form>
    )
}

export default Form;