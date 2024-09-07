package com.java.ifrscalculator.calculations;

public class PresentValueObligationCalculation implements FinancialCalculations {
    private double[] benefitPayments;
    private double discountRate;

    public PresentValueObligationCalculation(double[] benefitPayments, double discountRate) {
        this.benefitPayments = benefitPayments;
        this.discountRate = discountRate;
    }

    @Override
    public double calculate() {
        double presentValue = 0.0;
        for (int t = 0; t < benefitPayments.length; t++) {
            presentValue += benefitPayments[t] / Math.pow(1 + discountRate, t + 1);
        }
        return presentValue;
    }
}