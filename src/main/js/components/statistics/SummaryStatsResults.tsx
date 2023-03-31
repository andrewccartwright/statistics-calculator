import React from "react";
import { SummaryStats } from "./SummaryStatsForm";
import '../../css/Results.scss';

const SummaryStatsResults = (props: {summaryStats: SummaryStats}) => {
    const summaryStats = props.summaryStats;
    console.log(summaryStats);

    return (
        <div>
            <table className="results-section">
                <tbody>
                    {
                        Object.keys(summaryStats).map((key) => {
                            return <tr key={key}>
                                <td className="keys-column"><strong>{key}:</strong></td>
                                <td className="values-column">{Math.round(summaryStats[key] * 100) / 100}</td>
                            </tr>
                        })
                    }
                </tbody>
            </table>
        </div>
    )
}

export default SummaryStatsResults;