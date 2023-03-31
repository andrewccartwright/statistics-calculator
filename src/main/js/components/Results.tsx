import React from "react";

const Results = (props: { results: any}) => {
    const { results } = props;
    
    return (
        <div>
            <table className="results-section">
                <tbody>
                {
                    Object.keys(results).map((key) => {
                        return (
                            <tr key={key}>
                                <td><strong>{key}:</strong></td>
                                <td>{Math.round(results[key] * 10000) / 10000}</td>
                            </tr>
                        )
                    })
                }
                </tbody>
            </table>
            
        </div>
    )
}

export default Results;