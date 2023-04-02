import { Binomial, Exponential, Geometric, Normal, Poisson } from "./DistTypes";

const defaultBinomial: Binomial = {
    "n": null,
    "x": null,
    "p": null
}

const defaultExponential: Exponential = {
    "lambda": null,
    "x": null
}

const defaultGeometric: Geometric = {
    "x": null,
    "p": null,
    "includesSuccess": null
}

const defaultNormal:  Normal = {
    "x": null,
    "popMean": null,
    "popStDev": null
}

const defaultPoisson:  Poisson = {
    "lambda": null,
    "x": null
}

const defaultValues = [defaultBinomial, defaultExponential, defaultGeometric, defaultNormal, defaultPoisson]

interface DistItem {
    "defaultValue": any,
    "path": string
}

const distArray: DistItem[] = [
    {
        "defaultValue": defaultBinomial,
        "path": "/binomial"
    },
    {
        "defaultValue": defaultExponential,
        "path": "/exponential"
    },
    {
        "defaultValue": defaultGeometric,
        "path": "/geometric"
    },
    {
        "defaultValue": defaultNormal,
        "path": "/normal"
    },
    {
        "defaultValue": defaultPoisson,
        "path": "/poisson"
    }
]

export { distArray }