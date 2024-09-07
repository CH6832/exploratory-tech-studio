package com.fintech.algotrading.orderbook;

public class MatchResult {

    private final boolean success;
    private final String details;

    // Constructor initializes the result status and details.
    public MatchResult(boolean success, String details) {
        this.success = success;
        this.details = details;
    }

    // Getter for success status.
    public boolean isSuccess() {
        return success;
    }

    // Getter for details.
    public String getDetails() {
        return details;
    }
}
