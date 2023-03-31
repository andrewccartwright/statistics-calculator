import React from "react";
import '../css/Results.scss';

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
                                <td className="keys-column"><strong>{key}:</strong></td>
                                <td className="values-column">{Math.round(results[key] * 10000) / 10000}</td>
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