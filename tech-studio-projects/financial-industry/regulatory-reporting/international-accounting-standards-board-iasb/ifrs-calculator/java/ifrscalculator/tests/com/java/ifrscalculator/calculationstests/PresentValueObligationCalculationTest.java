package com.java.ifrscalculator.calculationstests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.java.ifrscalculator.calculations.PresentValueObligationCalculation;

public class PresentValueObligationCalculationTest {

    @Test
    public void testCalculatePresentValueObligation() {
        double[] payments = {100.0, 100.0, 100.0}; // Simplified example
        PresentValueObligationCalculation calc = new PresentValueObligationCalculation(payments, 0.05);
        double result = calc.calculate();
        assertEquals(286.53, result, 0.01); // Adjust expected value as necessary
    }
}
