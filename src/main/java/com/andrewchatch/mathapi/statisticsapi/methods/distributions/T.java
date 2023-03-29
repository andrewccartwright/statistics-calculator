package com.andrewchatch.mathapi.statisticsapi.methods.distributions;

import com.andrewchatch.mathapi.statisticsapi.methods.scores.TScore;

public class T {
    protected double sampleMean;
    protected double popMean;
    protected double sampleStDev;
    protected double sampleSize;
    protected double degreesFreedom;

    public T(double sampleMean, double popMean, double sampleStDev, double sampleSize) {
        this.sampleMean = sampleMean;
        this.popMean = popMean;
        this.sampleStDev = sampleStDev;
        this.sampleSize = sampleSize;
        this.degreesFreedom = sampleSize - 1;
    }

    public T(T t) {
        this.sampleMean = t.sampleMean;
        this.popMean = t.popMean;
        this.sampleStDev = t.sampleStDev;
        this.sampleSize = t.sampleSize;
        this.degreesFreedom = t.sampleSize - 1;
    }

    public void setSampleMean(double sampleMean) {
        this.sampleMean = sampleMean;
    }

    public void setPopMean(double popMean) {
        this.popMean = popMean;
    }

    public void setSampleStDev(double sampleStDev) {
        this.sampleStDev = sampleStDev;
    }

    public void setSampleSize(double sampleSize) {
        this.sampleSize = sampleSize;
    }

    public void setDegreesFreedom(double degreesFreedom) {
        this.degreesFreedom = degreesFreedom;
    }

    public double getSampleMean() {
        return this.sampleMean;
    }

    public double getPopMean() {
        return this.popMean;
    }

    public double getSampleStDev() {
        return this.sampleStDev;
    }

    public double getSampleSize() {
        return this.sampleSize;
    }

    public double getDegreesFreedom() {
        return this.degreesFreedom;
    }

    public double tCDF(TScore t) {
        double degreesFreedom = t.getDegreesFreedom();
        double tScore = t.calculateTScore();

        double gammaROver2 = gammaFunction(degreesFreedom / 2.0);
        double denom = Math.sqrt(Math.PI * degreesFreedom) * gammaROver2;

        double gammaRPlus1Over2 = tScore * gammaFunction((degreesFreedom + 1) / 2.0);
        double hypergeometric = hypergeometricFunction(1.0 / 2.0, (degreesFreedom + 1) / 2.0, 3.0 / 2.0, -Math.pow(tScore, 2) / degreesFreedom);
        double numer = gammaRPlus1Over2 * hypergeometric;

        double prob = 1.0 / 2.0 + numer / denom;

        if (prob < 0) {
            return 0;
        }
        else {
            return prob;
        }
    }

    private double hypergeometricFunction(double a, double b, double c, double z) {

        double sum = 1;
        for (int i = 0; i < 40; i++) {
            double currentATerm = factorial(a + i) / factorial(a - 1);
            double currentBTerm = factorial(b + i) / factorial(b - 1);
            double currentCTerm = factorial(c + i) / factorial(c - 1);
            double currentZTerm = (Math.pow(z, i + 1) / factorial(i + 1));

            double currentTerm = ((currentATerm * currentBTerm) / currentCTerm) * currentZTerm;
            sum += currentTerm;
        }

        return sum;
    }

    private double gammaFunction(double num) {
        double area = 0;
        double start = 0;
        double end = 1000;
        double step = 0.01;

        for (double t = start; t < end; t += step) {
            area += Math.pow(t, num - 1) * Math.pow(Math.E, -t);
        }

        return area;
    }

    private double factorial(double num) {
        if (num <= 0) {
            return 1;
        }
        else {
            return num * factorial(num - 1);
        }

    }
}
