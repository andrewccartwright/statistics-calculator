import React from "react";
import Form from "./Form";
import Results from "./Results";

const Item = (props: { content: any, setContent: React.Dispatch<React.SetStateAction<any>>, results: any, setResults: React.Dispatch<React.SetStateAction<any>>, path: string, section: string}) => {
    const {content, setContent, results, setResults, path, section} = props;

    return (
        <div>
            <Form content={content} setContent={setContent} setResults={setResults} path={`/${section}${path}`} />
            {results && <Results results={results} />}
        </div>
    )
}

export default Item;