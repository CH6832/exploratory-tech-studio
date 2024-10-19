package com.example.bandcsimu;

public class Transaction {
    private String sender;
    private String receiver;
    private double amount;
    private String signature;

    // Constructor
    public Transaction(String sender, String receiver, double amount) {
        this.sender = sender;
        this.receiver = receiver;
        this.amount = amount;
        String data = getTransactionData();
        // this.signature = signTransaction(data);  // Signature generation placeholder
    }

    // Getters
    public String getSender() {
        return sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public double getAmount() {
        return amount;
    }

    public String getSignature() {
        return signature;
    }

    // Generate transaction data as a string (used for signing or hashing)
    public String getTransactionData() {
        return sender + receiver + amount;
    }
}
