package com.example.bandcsimu;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * The ProofOfStake class implements a proof-of-stake consensus mechanism.
 * This class is responsible for managing the stakes of validators and
 * selecting a validator based on their stake proportions.
 */
public class ProofOfStake {
    private Map<String, Double> stakes;  // Stores the stakes of validators (validator name -> stake amount)
    private Random random;                // Random number generator for validator selection

    /**
     * Constructor for the ProofOfStake class.
     * Initializes the stakes map and the random number generator.
     */
    public ProofOfStake() {
        this.stakes = new HashMap<>();   // Initialize the stakes map
        this.random = new Random();       // Initialize the random number generator
    }

    /**
     * Adds a stake for a given validator.
     * If the validator already has a stake, it increases it by the specified amount.
     * Otherwise, it creates a new entry in the stakes map.
     *
     * @param validator The name of the validator.
     * @param amount The amount of stake to add.
     */
    public void addStake(String validator, double amount) {
        // Update the validator's stake, defaulting to 0.0 if not present
        stakes.put(validator, stakes.getOrDefault(validator, 0.0) + amount);
    }

    /**
     * Selects a validator based on the proportion of their stake relative to the total stakes.
     * A random number is generated and the validator is selected according to their stake percentage.
     *
     * @return The name of the selected validator.
     */
    public String selectValidator() {
        double total = totalStake();           // Calculate the total stake
        double rand = random.nextDouble();     // Generates a random number between 0.0 and 1.0

        double cumulative = 0;                 // Cumulative stake for selection
        for (Map.Entry<String, Double> stake : stakes.entrySet()) {
            cumulative += stake.getValue() / total; // Calculate cumulative probability
            if (rand < cumulative) {           // Select the validator based on random value
                return stake.getKey();         // Return the selected validator
            }
        }

        // Fallback: return the first validator if something goes wrong
        return stakes.keySet().iterator().next(); 
    }

    /**
     * Calculates the total stake held by all validators.
     *
     * @return The total amount of stakes across all validators.
     */
    public double totalStake() {
        // Sum all stake amounts from the stakes map
        return stakes.values().stream().mapToDouble(Double::doubleValue).sum();
    }
}
