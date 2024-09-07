package com.java.ifrscalculator.calculationstests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.java.ifrscalculator.calculations.DeferredTaxCalculation;

public class DeferredTaxCalculationTest {

    @Test
    public void testCalculateDeferredTax() {
        DeferredTaxCalculation calc = new DeferredTaxCalculation(1000.0, 800.0, 0.30);
        double result = calc.calculate();
        assertEquals(60.0, result, 0.01); // Adjust expected value as necessary
    }
}
