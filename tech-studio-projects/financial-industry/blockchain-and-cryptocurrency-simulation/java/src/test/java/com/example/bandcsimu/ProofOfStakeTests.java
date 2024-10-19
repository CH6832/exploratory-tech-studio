package com.example.bandcsimu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProofOfStakeTests {

    private ProofOfStake proofOfStake;

    @BeforeEach
    void setUp() {
        proofOfStake = new ProofOfStake();
        proofOfStake.addStake("Alice", 50);
        proofOfStake.addStake("Bob", 100);
    }

    @Test
    void testSelectValidator() {
        String validator = proofOfStake.selectValidator();
        assertTrue(validator.equals("Alice") || validator.equals("Bob"));
    }

    @Test
    void testTotalStake() {
        double total = proofOfStake.totalStake();
        assertEquals(150.0, total);
    }
}
