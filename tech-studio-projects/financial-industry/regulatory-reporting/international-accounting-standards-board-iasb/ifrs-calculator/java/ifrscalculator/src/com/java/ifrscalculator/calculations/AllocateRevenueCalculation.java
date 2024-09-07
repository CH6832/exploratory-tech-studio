package com.java.ifrscalculator.calculations;

import java.util.HashMap;
import java.util.Map;

public class AllocateRevenueCalculation implements FinancialCalculations {
    private final Map<String, Double> standaloneSellingPrices;
    private final double totalTransactionPrice;

    public AllocateRevenueCalculation(Map<String, Double> standaloneSellingPrices, double totalTransactionPrice) {
        this.standaloneSellingPrices = standaloneSellingPrices;
        this.totalTransactionPrice = totalTransactionPrice;
    }

    @Override
    public double calculate() {
        double totalPrice = standaloneSellingPrices.values().stream().mapToDouble(Double::doubleValue).sum();
        Map<String, Double> allocatedPrices = new HashMap<>();
        for (Map.Entry<String, Double> entry : standaloneSellingPrices.entrySet()) {
            allocatedPrices.put(entry.getKey(), (entry.getValue() / totalPrice) * totalTransactionPrice);
        }
        return allocatedPrices.values().stream().mapToDouble(Double::doubleValue).sum(); // Returning sum as a placeholder
    }

    public Map<String, Double> getAllocatedPrices() {
        double totalPrice = standaloneSellingPrices.values().stream().mapToDouble(Double::doubleValue).sum();
        Map<String, Double> allocatedPrices = new HashMap<>();
        for (Map.Entry<String, Double> entry : standaloneSellingPrices.entrySet()) {
            allocatedPrices.put(entry.getKey(), (entry.getValue() / totalPrice) * totalTransactionPrice);
        }
        return allocatedPrices;
    }
}