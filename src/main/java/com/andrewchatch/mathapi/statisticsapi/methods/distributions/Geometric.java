package com.andrewchatch.mathapi.statisticsapi.methods.distributions;

import java.util.HashMap;

import com.andrewchatch.mathapi.statisticsapi.exceptions.InvalidProbabilityException;

public class Geometric {
    private int x;
    private double p;
    private boolean includesSuccess;

    public Geometric(int x, double p, boolean includesSuccess) {
        if (x <= 0) {
            throw new IllegalArgumentException("X must be an integer greater than 0.");
        }
        if (p < 0 || p > 1) {
            throw new InvalidProbabilityException("e");
        }

        this.x = x;
        this.p = p;
        this.includesSuccess = includesSuccess;
    }

    public Geometric(Geometric geo) {
        System.out.println(geo.includesSuccess);
        this.x = geo.x;
        this.p = geo.p;
        this.includesSuccess = geo.includesSuccess;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setP(double p) {
        this.p = p;
    }

    public int getX() {
        return this.x;
    }

    public double getP() {
        return this.p;
    }

    public boolean getIncludesSuccess() {
        return this.includesSuccess;
    }

    private double calculateProbability(int x, double p, boolean includesSuccess) {
        double prob;
        if (includesSuccess) {
            prob = Math.pow(1 - p, (x - 1)) * p;
        }
        else {
            prob = Math.pow(1 - p, x) * p;
        }
        return prob;
    }

    private double calculateCumulativeProbInclusive(int x, double p, boolean includesSuccess) {
        double sum = 0;
        for (int i = 0; i <= this.x; i++) {
            sum += calculateProbability(x, p, includesSuccess);
        }

        return sum;
    }

    private double calculateCumulativeProbExclusive(int x, double p, boolean includesSuccess) {
        double sum = 0;
        for (int i = 0; i < this.x; i++) {
            sum += calculateProbability(x, p, includesSuccess);
        }

        return sum;
    }

    public HashMap<String,Double> getProbability(int x, double p, boolean includesSuccess) {
        double pEqX = calculateProbability(x, p, includesSuccess);
        double cumulativePInclusive = calculateCumulativeProbInclusive(x, p, includesSuccess);
        double cumulativeQInclusive = 1 - cumulativePInclusive;
        double cumulativePExclusive = calculateCumulativeProbExclusive(x, p, includesSuccess);
        double cumulativeQExclusive = 1 - cumulativePExclusive;

        HashMap<String,Double> map = new HashMap<String,Double>();

        map.put("P(X = x)", pEqX);
        map.put("P(X \u2265 x)", cumulativePInclusive);
        map.put("P(X > x)", cumulativeQInclusive);
        map.put("P(X < x)", cumulativePExclusive);
        map.put("P(X \u2264 x)", cumulativeQExclusive);
        return map;
    }
}
