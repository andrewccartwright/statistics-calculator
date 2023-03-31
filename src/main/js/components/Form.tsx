import React, { FormEventHandler, useEffect, useState } from "react";

const Form = (props: {content: any, setContent: React.Dispatch<React.SetStateAction<any>>, handleSubmit: FormEventHandler<HTMLFormElement> }) => {
    const { content, handleSubmit } = props;

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

            <button className="btn btn-primary">Submit</button>
        </form>
    )
}

export default Form;