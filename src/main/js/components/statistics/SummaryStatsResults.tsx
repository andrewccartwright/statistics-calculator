import React from "react";
import { SummaryStats } from "./SummaryStatsForm";

const SummaryStatsResults = (props: {summaryStats: SummaryStats}) => {
    const summaryStats = props.summaryStats;
    console.log(summaryStats);

    return (
        <div>
            <table className="summary-stats-table">
                <tbody>
                    {
                        Object.keys(summaryStats).map((key) => {
                            return <tr key={key}>
                                <td><strong>{key}:</strong></td>
                                <td>{Math.round(summaryStats[key] * 100) / 100}</td>
                            </tr>
                        })
                    }
                </tbody>
            </table>
        </div>
    )
}

export default SummaryStatsResults;