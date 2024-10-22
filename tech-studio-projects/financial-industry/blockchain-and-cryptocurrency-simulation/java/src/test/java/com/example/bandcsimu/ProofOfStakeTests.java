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
package com.example.bandcsimu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class contains unit tests for the ProofOfStake class, which simulates the 
 * Proof-of-Stake (PoS) consensus mechanism. The tests validate that:
 * - Stakeholders can add stakes correctly.
 * - The correct validator is selected based on their stake.
 * - The total stake in the system is accurately calculated.
 *
 * PoS is a consensus mechanism where validators are chosen based on the amount of 
 * cryptocurrency they hold (their "stake"), so it's important to ensure that both 
 * the staking logic and validator selection work correctly.
 */
class ProofOfStakeTests {

    // Instance of the ProofOfStake class to be used in each test case.
    private ProofOfStake proofOfStake;

    /**
     * This method is executed before each test, setting up the ProofOfStake instance.
     * 
     * Why it's important:
     * - The @BeforeEach annotation ensures that each test starts with a clean state.
     * - We initialize the ProofOfStake object and add stakes for "Alice" and "Bob" to 
     *   simulate a real-world scenario where multiple users are participating in staking.
     */
    @BeforeEach
    void setUp() {
        proofOfStake = new ProofOfStake();

        // Add initial stakes for Alice and Bob. Alice has 50, and Bob has 100.
        proofOfStake.addStake("Alice", 50);
        proofOfStake.addStake("Bob", 100);
    }

    /**
     * Tests the selection of a validator from the stakeholders.
     * 
     * What we are testing:
     * - That the selectValidator method correctly returns either "Alice" or "Bob".
     * - Since validators are chosen based on stake, both Alice and Bob should have a 
     *   chance to be selected, with Bob having a higher probability due to his larger stake.
     * 
     * Why it's important:
     * - The selection of validators in a Proof-of-Stake system is crucial to maintaining 
     *   security and fairness. This test ensures that only stakeholders (Alice and Bob) 
     *   can be selected as validators.
     */
    @Test
    void testSelectValidator() {
        // Call the selectValidator method to choose a validator.
        String validator = proofOfStake.selectValidator();

        // Assert that the selected validator is either Alice or Bob, since they are the only participants.
        assertTrue(validator.equals("Alice") || validator.equals("Bob"), 
                   "Validator must be either Alice or Bob.");
    }

    /**
     * Tests the calculation of the total stake in the system.
     * 
     * What we are testing:
     * - That the totalStake method correctly calculates the combined stakes of Alice and Bob.
     * 
     * Why it's important:
     * - The total stake is critical for the functioning of a Proof-of-Stake system. It 
     *   directly influences the validator selection process. This test ensures that 
     *   the sum of all stakes is accurate.
     */
    @Test
    void testTotalStake() {
        // Calculate the total stake held by all stakeholders (Alice and Bob).
        double total = proofOfStake.totalStake();

        // Assert that the total stake is 150 (Alice's 50 + Bob's 100).
        assertEquals(150.0, total, 
                     "Total stake should be 150.0 after adding 50 for Alice and 100 for Bob.");
    }
}
