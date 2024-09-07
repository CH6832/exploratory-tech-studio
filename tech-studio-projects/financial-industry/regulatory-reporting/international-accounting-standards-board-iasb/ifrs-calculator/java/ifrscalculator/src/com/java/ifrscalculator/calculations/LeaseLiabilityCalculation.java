package com.java.ifrscalculator.calculations;

public class LeaseLiabilityCalculation implements FinancialCalculations {
    private double[] leasePayments;
    private double discountRate;

    public LeaseLiabilityCalculation(double[] leasePayments, double discountRate) {
        this.leasePayments = leasePayments;
        this.discountRate = discountRate;
    }

    @Override
    public double calculate() {
        double leaseLiability = 0.0;
        for (int t = 0; t < leasePayments.length; t++) {
            leaseLiability += leasePayments[t] / Math.pow(1 + discountRate, t + 1);
        }
        return leaseLiability;
    }
}