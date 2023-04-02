import { Normal } from "./DistTypes";

interface ZScore extends Normal {

}

interface TScore {
    [index: string]: number,
    sampleMean: number,
    popMean: number,
    sampleStDev: number,
    sampleSize: number
}

export { ZScore, TScore }