package com.java.ifrscalculator.calculationstests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.java.ifrscalculator.calculations.GoodwillCalculation;

public class GoodwillCalculationTest {

    @Test
    public void testCalculateGoodwill() {
        GoodwillCalculation calc = new GoodwillCalculation(1200.0, 1000.0, 200.0);
        double result = calc.calculate();
        assertEquals(400.0, result, 0.01); // Adjust expected value as necessary
    }
}
