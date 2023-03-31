import React from "react";

const Results = (props: { results: any}) => {
    const { results } = props;
    
    return (
        <div>
            {
                <p>Results</p>
                // Object.keys(results).map((key) => {
                //     return (
                //         <div key={key}>
                //             {results[key]}
                //         </div>
                //     )
                // })
            }
        </div>
    )
}

export default Results;