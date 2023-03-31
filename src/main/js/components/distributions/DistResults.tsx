import React from "react";
import Results from "../Results";

const DistResults = (props: {results: any}) => {
    const { results } = props;

    return (
        <div>
            <Results results={results} />
        </div>
    )
}

export default DistResults;