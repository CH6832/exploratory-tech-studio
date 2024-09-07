package com.java.ifrscalculator.calculations;

public class RecoverableAmountCalculation implements FinancialCalculations {
    private double fairValueLessCostsToSell;
    private double valueInUse;

    public RecoverableAmountCalculation(double fairValueLessCostsToSell, double valueInUse) {
        this.fairValueLessCostsToSell = fairValueLessCostsToSell;
        this.valueInUse = valueInUse;
    }

    @Override
    public double calculate() {
        return Math.max(fairValueLessCostsToSell, valueInUse);
    }
}
