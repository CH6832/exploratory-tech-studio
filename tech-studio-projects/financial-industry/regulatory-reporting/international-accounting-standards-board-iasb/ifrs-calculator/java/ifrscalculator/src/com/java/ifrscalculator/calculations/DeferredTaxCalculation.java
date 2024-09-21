package com.java.ifrscalculator.calculations;

public class DeferredTaxCalculation implements FinancialCalculations {
    private double carryingAmount;
    private double taxBase;
    private double taxRate;

    public DeferredTaxCalculation(double carryingAmount, double taxBase, double taxRate) {
        this.carryingAmount = carryingAmount;
        this.taxBase = taxBase;
        this.taxRate = taxRate;
    }

    @Override
    public double calculate() {
        double temporaryDifference = carryingAmount - taxBase;
        return temporaryDifference * taxRate;
    }
}