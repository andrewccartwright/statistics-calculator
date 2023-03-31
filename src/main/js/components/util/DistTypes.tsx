interface Binomial {
    [index: string]: number,
    x: number,
    n: number,
    p: number
}

interface Exponential {
    [index: string]: number,
    x: number,
    lambda: number
}

interface Geometric {
    [index: string]: number | boolean,
    x: number,
    p: number,
    includesSuccess: boolean
}

interface Normal {
    [index: string]: number,
    x: number,
    popMean: number,
    popStDev: number
}

interface Poisson {
    [index: string]: number,
    x: number,
    lambda: number
}

interface T {
    [index: string]: number,
    sampleMean: number,
    popMean: number,
    sampleStDev: number,
    sampleSize: number,
    degreesFreedom: number
}

export { Binomial, Exponential, Geometric, Normal, Poisson, T }