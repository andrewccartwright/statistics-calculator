package com.andrewchatch.mathapi.statisticsapi.methods.scores;

import java.util.HashMap;

import com.andrewchatch.mathapi.statisticsapi.methods.distributions.T;

public class TScore extends T {

    public TScore(double sampleMean, double popMean, double sampleStDev, double sampleSize) {
        super(sampleMean, popMean, sampleStDev, sampleSize);
    }

    public TScore(TScore t) {
        super(t);
    }

    public double calculateTScore() {
        double denom = this.sampleStDev / Math.sqrt(this.sampleSize);
        return (this.sampleMean - this.popMean) / denom;
    }

    public HashMap<String,Double> getProbability() {
        double tScore = this.calculateTScore();

        HashMap<String,Double> map = new HashMap<String, Double>();
        double prob = this.tCDF(this);

        map.put("T Score", tScore);
        map.put("P(T < t)", prob);
        map.put("P(T > t)", 1 - prob);

        return map;
    }
}
