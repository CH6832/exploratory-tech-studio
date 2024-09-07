package com.java.ifrscalculator.calculations;

public class FairValueCalculation implements FinancialCalculations {
    private final double coupon;
    private final double faceValue;
    private final double discountRate;
    private final int periods;

    public FairValueCalculation(double coupon, double faceValue, double discountRate, int periods) {
        this.coupon = coupon;
        this.faceValue = faceValue;
        this.discountRate = discountRate;
        this.periods = periods;
    }

    @Override
    public double calculate() {
        double fairValue = 0.0;
        for (int t = 1; t <= periods; ++t) {
            fairValue += coupon / Math.pow(1 + discountRate, t);
        }
        fairValue += faceValue / Math.pow(1 + discountRate, periods);
        return fairValue;
    }
}
