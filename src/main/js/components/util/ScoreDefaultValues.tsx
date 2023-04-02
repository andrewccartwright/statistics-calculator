import { TScore, ZScore } from "./ScoreTypes";

const defaultZScore: ZScore = {
    "x": null,
    "popMean": null,
    "popStDev": null
}

const defaultTScore: TScore = {
    "sampleMean": null,
    "popMean": null,
    "sampleStDev": null,
    "sampleSize": null
}

interface ScoreItem {
    "defaultValue": any,
    "path": string
}

const scoreArray: ScoreItem[] = [
    {
        "defaultValue": defaultZScore,
        "path": "/z score"
    },
    {
        "defaultValue": defaultTScore,
        "path": "/t score"
    }
]

export { scoreArray }