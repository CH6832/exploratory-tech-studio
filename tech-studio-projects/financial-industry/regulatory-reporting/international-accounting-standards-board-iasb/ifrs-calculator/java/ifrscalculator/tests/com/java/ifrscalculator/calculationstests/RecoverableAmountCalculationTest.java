package com.java.ifrscalculator.calculationstests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.java.ifrscalculator.calculations.RecoverableAmountCalculation;

public class RecoverableAmountCalculationTest {

    @Test
    public void testCalculateRecoverableAmount() {
        RecoverableAmountCalculation calc = new RecoverableAmountCalculation(800.0, 600.0);
        double result = calc.calculate();
        assertEquals(800.0, result, 0.01); // Adjust expected value as necessary
    }
}
