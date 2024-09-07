package com.java.ifrscalculator.calculationstests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.java.ifrscalculator.calculations.LeaseLiabilityCalculation;

public class LeaseLiabilityCalculationTest {

    @Test
    public void testCalculateLeaseLiability() {
        double[] payments = {100.0, 100.0, 100.0}; // Simplified example
        LeaseLiabilityCalculation calc = new LeaseLiabilityCalculation(payments, 0.05);
        double result = calc.calculate();
        assertEquals(286.53, result, 0.01); // Adjust expected value as necessary
    }
}
