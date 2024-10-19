package com.example.bandcsimu;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class ProofOfStake {
    private Map<String, Double> stakes;  // Stores the stakes of validators
    private Random random;

    // Constructor
    public ProofOfStake() {
        this.stakes = new HashMap<>();
        this.random = new Random();
    }

    // Add stake for a validator
    public void addStake(String validator, double amount) {
        stakes.put(validator, stakes.getOrDefault(validator, 0.0) + amount);
    }

    // Select a validator based on the proportion of their stake
    public String selectValidator() {
        double total = totalStake();
        double rand = random.nextDouble();  // Generates a random number between 0.0 and 1.0

        double cumulative = 0;
        for (Map.Entry<String, Double> stake : stakes.entrySet()) {
            cumulative += stake.getValue() / total;
            if (rand < cumulative) {
                return stake.getKey();
            }
        }

        // If something goes wrong, return the first validator (this is a fallback)
        return stakes.keySet().iterator().next();
    }

    // Calculate the total stake held by all validators
    public double totalStake() {
        return stakes.values().stream().mapToDouble(Double::doubleValue).sum();
    }
}
