package com.system.banking.model;

/**
 * Represents a bank account in the banking system.
 * This class models the properties of a user's account including account number, type, balance, and currency.
 */
public class Account {

    // Field for the user ID associated with the account
    private String userId;

    // Field for the unique account number
    private String accountNumber;

    // Field for the type of the account (e.g., "savings", "checking")
    private String accountType;

    // Field for the balance of the account
    private double balance;

    // Field for the currency of the account (e.g., "USD")
    private String currency;

    /**
     * Gets the user ID associated with the account.
     *
     * @return The user ID
     */
    public String getUserId() {
        return userId;
    }

    /**
     * Sets the user ID associated with the account.
     *
     * @param userId The user ID to set
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * Gets the account number.
     *
     * @return The account number
     */
    public String getAccountNumber() {
        return accountNumber;
    }

    /**
     * Sets the account number.
     *
     * @param accountNumber The account number to set
     */
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    /**
     * Gets the type of the account (e.g., "savings", "checking").
     *
     * @return The account type
     */
    public String getAccountType() {
        return accountType;
    }

    /**
     * Sets the type of the account.
     *
     * @param accountType The account type to set
     */
    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    /**
     * Gets the balance of the account.
     *
     * @return The account balance
     */
    public double getBalance() {
        return balance;
    }

    /**
     * Sets the balance of the account.
     *
     * @param balance The balance to set
     */
    public void setBalance(double balance) {
        this.balance = balance;
    }

    /**
     * Gets the currency of the account (e.g., "USD").
     *
     * @return The account currency
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * Sets the currency of the account.
     *
     * @param currency The currency to set (e.g., "USD")
     */
    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
