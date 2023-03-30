type Binomial = {
    [index: string]: number,
    x: number,
    n: number,
    p: number
}

type Exponential = {
    [index: string]: number,
    x: number,
    lambda: number
}

type Geometric = {
    [index: string]: number | boolean,
    x: number,
    p: number,
    includesSuccess: boolean
}

type Normal = {
    [index: string]: number,
    x: number,
    popMean: number,
    popStDev: number
}

type Poisson = {
    [index: string]: number,
    x: number,
    lambda: number
}

type T = {
    [index: string]: number,
    popMean: number,
    sampleStDev: number,
    sampleSize: number,
    degreesFreedom: number
}

export { Binomial, Exponential, Geometric, Normal, Poisson, T }