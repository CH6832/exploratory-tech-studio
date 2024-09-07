package com.java.ifrscalculator.calculationstests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.java.ifrscalculator.calculations.FairValueCalculation;

public class FairValueCalculationTest {

    @Test
    public void testCalculateFairValue() {
        FairValueCalculation calc = new FairValueCalculation(100.0, 1000.0, 0.05, 10);
        double result = calc.calculate();
        assertEquals(1335.98, result, 0.01); // Adjust expected value as necessary
    }
}
