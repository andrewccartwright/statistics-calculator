import React, { useEffect, useState } from "react";
import DistForm from "./DistForm";
import DistResults from "./DistResults";

const DistItem = (props: { defaultContent: any, path: string}) => {
    const {defaultContent, path} = props;
    const [content, setContent] = useState(defaultContent);
    const [results, setResults] = useState();

    return (
        <div>
            <DistForm content={content} setContent={setContent} setResults={setResults} path={path} />
            {results && <DistResults results={results} />}
        </div>
    )
}

export default DistItem;