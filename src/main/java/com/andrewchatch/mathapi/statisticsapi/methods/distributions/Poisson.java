package com.andrewchatch.mathapi.statisticsapi.methods.distributions;

import java.util.HashMap;

public class Poisson {
    private int x;
    private double lambda;

    public Poisson(int x, double lambda) {
        if (x < 0) {
            throw new IllegalArgumentException("X must be an integer greater than or equal to 0.");
        }
        if (lambda <= 0) {
            throw new IllegalArgumentException("Lambda must be greater than 0.");
        }

        this.x = x;
        this.lambda = lambda;
    }

    public Poisson(Poisson p) {
        if (p.x < 0) {
            throw new IllegalArgumentException("X must be an integer greater than or equal to 0.");
        }
        if (p.lambda <= 0) {
            throw new IllegalArgumentException("Lambda must be greater than 0.");
        }

        this.x = p.x;
        this.lambda = p.lambda;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setLambda(double lambda) {
        this.lambda = lambda;
    }

    public int getX() {
        return this.x;
    }

    public double getLambda() {
        return this.lambda;
    }

    private double calculateProbability(int x, double lambda) {
        double exp = Math.pow(Math.E, -lambda);
        double fraction = Math.pow(lambda, x) / factorial(x);
        double prob = exp * fraction;
        
        return prob;
    }

    private double calculateCumulativeProbInclusive(int x, double lambda) {
        double sum = 0;
        for (int i = 0; i <= this.x; i++) {
            sum += calculateProbability(i, lambda);
        }

        return sum;
    }

    private double calculateCumulativeProbExclusive(int x, double lambda) {
        double sum = 0;
        for (int i = 0; i < this.x; i++) {
            sum += calculateProbability(i, lambda);
        }

        return sum;
    }

    public HashMap<String,Double> getProbability(int x, double lambda) {
        double pEqX = calculateProbability(x, lambda);
        double cumulativePInclusive = calculateCumulativeProbInclusive(x, lambda);
        double cumulativeQInclusive = 1 - cumulativePInclusive;
        double cumulativePExclusive = calculateCumulativeProbExclusive(x, lambda);
        double cumulativeQExclusive = 1 - cumulativePExclusive;

        HashMap<String,Double> map = new HashMap<String,Double>();
    
        map.put("P(X = x)", pEqX);
        map.put("P(X \u2265 x)", cumulativePInclusive);
        map.put("P(X > x)", cumulativeQInclusive);
        map.put("P(X < x)", cumulativePExclusive);
        map.put("P(X \u2264 x)", cumulativeQExclusive);
        return map;
    }

    private int factorial(int n) {
        if (n == 0) {
            return 1;
        }
        return n * this.factorial(n - 1);
    }
}
