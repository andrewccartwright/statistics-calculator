package com.andrewchatch.mathapi.statisticsapi.methods.distributions;

import java.util.HashMap;

public class Exponential {
    private double x;
    private double lambda;

    public Exponential(double x, double lambda) {
        if (x < 0) {
            throw new IllegalArgumentException("X must be greater than or equal to 0.");
        }

        this.x = x;
        this.lambda = lambda;
    }

    public Exponential(Exponential exp) {
        if (exp.x < 0) {
            throw new IllegalArgumentException("X must be greater than or equal to 0.");
        }

        this.x = exp.x;
        this.lambda = exp.lambda;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setLambda(double lambda) {
        this.lambda = lambda;
    }

    public double getX() {
        return this.x;
    }

    public double getLambda() {
        return this.lambda;
    }

    private double calculateProbability(double x, double lambda) {
        double exponent = - lambda * x;
        double prob = 1 - Math.pow(Math.E, exponent);

        return prob;
    }

    public HashMap<String, Double> getProbability(double x, double lambda) {
        double leftArea = calculateProbability(x, lambda);
        double rightArea = 1 - leftArea;

        HashMap<String,Double> map = new HashMap<String,Double>();

        map.put("P(X < x)", leftArea);
        map.put("P(X > x)", rightArea);

        return map;
    }
}
