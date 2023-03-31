import React from "react";
import DistForm from "./DistForm";
import DistResults from "./DistResults";

const DistItem = (props: { content: any, setContent: React.Dispatch<React.SetStateAction<any>>, results: any, setResults: React.Dispatch<React.SetStateAction<any>>, path: string}) => {
    const {content, setContent, results, setResults, path} = props;

    return (
        <div>
            <DistForm content={content} setContent={setContent} setResults={setResults} path={path} />
            {results && <DistResults results={results} />}
        </div>
    )
}

export default DistItem;