package com.java.ifrscalculator.calculationstests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import com.java.ifrscalculator.calculations.AllocateRevenueCalculation;

public class AllocateRevenueCalculationTest {

    @Test
    public void testAllocateRevenue() {
        Map<String, Double> prices = new HashMap<>();
        prices.put("Product A", 100.0);
        prices.put("Product B", 200.0);
        
        AllocateRevenueCalculation calc = new AllocateRevenueCalculation(prices, 300.0);
        Map<String, Double> result = calc.getAllocatedPrices();

        assertEquals(100.0, result.get("Product A"), 0.01);
        assertEquals(200.0, result.get("Product B"), 0.01);
    }
}

