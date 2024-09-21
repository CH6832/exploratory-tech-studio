package com.java.ifrscalculator.calculations;

public class GoodwillCalculation implements FinancialCalculations {
    private double purchasePrice;
    private double fairValueAssets;
    private double fairValueLiabilities;

    public GoodwillCalculation(double purchasePrice, double fairValueAssets, double fairValueLiabilities) {
        this.purchasePrice = purchasePrice;
        this.fairValueAssets = fairValueAssets;
        this.fairValueLiabilities = fairValueLiabilities;
    }

    @Override
    public double calculate() {
        return purchasePrice - (fairValueAssets - fairValueLiabilities);
    }
}