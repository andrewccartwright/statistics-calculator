package com.andrewchatch.mathapi.statisticsapi.methods.distributions;

import java.util.HashMap;

// import com.andrewchatch.statisticsapi.methods.scores.ZScore;

public class Normal {
    protected double x;
    protected double popMean;
    protected double popStDev;

    public Normal(double x, double popMean, double popStDev) {
        this.x = x;
        this.popMean = popMean;
        this.popStDev = popStDev;
    }

    public Normal(Normal normal) {
        this.x = normal.x;
        this.popMean = normal.popMean;
        this.popStDev = normal.popStDev;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setMean(double popMean) {
        this.popMean = popMean;
    }

    public void setStDev(double popStDev) {
        this.popStDev = popStDev;
    }

    public double getX() {
        return this.x;
    }

    public double getMean() {
        return this.popMean;
    }

    public double getStDev() {
        return this.popStDev;
    }

    private double computeZScore(double x, double popMean, double popStDev) {
        System.out.println(x + " " + popMean + " " + popStDev);
        double zScore = (x - popMean) / popStDev;
        return zScore;
    }

    private double[] normalCDF(double x, double popMean, double popStDev) {
        double zScore = computeZScore(x, popMean, popStDev);
        double leftArea = (1 + erfFunction(0, zScore/Math.sqrt(2), 0.01)) / 2;
        double rightArea = 1 - leftArea;

        if (zScore < 0) {
            double temp = leftArea;
            leftArea = rightArea;
            rightArea = temp;
        }

        double[] arr = {zScore, leftArea, rightArea};
        return arr;
    }

    private double erfFunction(double start, double end, double step) {
        double area = 0;

        if (end < 0) {
            for(double z = start; z > end; z -= step) {
                area += Math.exp(-Math.pow((z + step/2),2)) * step;
            }
        }
        else {
            for(double z = start; z < end; z += step) {
                area += Math.exp(-Math.pow((z + step/2),2)) * step;
            }
        }

        return area * (2 / Math.sqrt(Math.PI));
    }

    public HashMap<String,Double> getProbability(double x, double popMean, double popStDev) {
        double[] probs = normalCDF(x, popMean, popStDev);

        HashMap<String,Double> map = new HashMap<String,Double>();
        map.put("Z Score", probs[0]);
        map.put("P(X < x)", probs[1]);
        map.put("P(X > x)", probs[2]);

        return map;
    }
}
