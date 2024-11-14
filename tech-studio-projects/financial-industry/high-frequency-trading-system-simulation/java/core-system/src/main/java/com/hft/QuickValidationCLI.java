package com.hft;

import com.hft.matching.OrderMatchingEngine;
import com.hft.risk.MarginCalculator;
import com.hft.risk.RiskManager;

public class QuickValidationCLI {

    public static void main(String[] args) {
        try {
            System.out.println("Starting Quick Validation...");

            // Validate Order Matching Engine
            OrderMatchingEngine engine = new OrderMatchingEngine();
            validateOrderMatching(engine);

            // Validate Margin Calculator
            MarginCalculator marginCalculator = new MarginCalculator();
            validateMarginCalculation(marginCalculator);

            // Validate Risk Manager
            RiskManager riskManager = new RiskManager();
            validateRiskManagement(riskManager);

            System.out.println("Quick Validation Passed: All checks successful.");

        } catch (Exception e) {
            System.err.println("Quick Validation Failed: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void validateOrderMatching(OrderMatchingEngine engine) {
        // Mock order data for testing
        double buyOrderValue = 1000;
        double sellOrderValue = 1000;

        // Example order matching logic
        boolean isMatched = engine.matchOrder(buyOrderValue, sellOrderValue);
        if (!isMatched) {
            throw new IllegalStateException("Order Matching failed: Expected match not found.");
        }
        System.out.println("Order Matching Validation Passed");
    }

    private static void validateMarginCalculation(MarginCalculator marginCalculator) {
        double testOrderValue = 5000;
        String riskTier = "DEFAULT";

        // Margin calculation validation
        double margin = marginCalculator.calculateMargin(testOrderValue, riskTier);
        if (margin <= 0) {
            throw new IllegalStateException("Margin Calculation failed: Margin should be positive.");
        }
        System.out.println("Margin Calculation Validation Passed");
    }

    private static void validateRiskManagement(RiskManager riskManager) {
        // Risk validation test
        double orderValue = 5000;
        boolean isRiskAccepted = riskManager.checkRisk(orderValue);
        if (!isRiskAccepted) {
            throw new IllegalStateException("Risk Management Validation failed: Order incorrectly flagged as risky.");
        }
        System.out.println("Risk Management Validation Passed");
    }
}
