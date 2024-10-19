package com.example.bandcsimu;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.util.List;

public class Block {
    private int index;
    private String previousHash;
    private List<String> transactions;
    private String timestamp;
    private String hash;

    // Constructor
    public Block(int index, String previousHash, Object block) {
        this.index = index;
        this.previousHash = previousHash;
        this.transactions = (List<String>) block;
        this.timestamp = Instant.now().toString();  // Use the current timestamp
        this.hash = calculateHash(index, previousHash, block, timestamp);
    }

    // Getter for hash
    public String getHash() {
        return hash;
    }

    // Getter for previous hash
    public String getPreviousHash() {
        return previousHash;
    }

    // Getter for transactions
    public List<String> getTransactions() {
        return transactions;
    }

    // Getter for timestamp
    public String getTimestamp() {
        return timestamp;
    }

    // Print Block Information
    public void printBlock() {
        System.out.println("Block " + index + ":");
        System.out.println("Previous Hash: " + previousHash);
        System.out.println("Hash: " + hash);
        System.out.println("Transactions:");
        for (String tx : transactions) {
            System.out.println("  " + tx);
        }
    }

    // Getter for index
    public int getIndex() {
        return index;
    }

    // Calculate the hash of the block
    public static String calculateHash(int index, String previousHash, Object block, String timestamp) {
        try {
            // Concatenate the block's data
            StringBuilder data = new StringBuilder();
            data.append(index).append(previousHash).append(timestamp);
            for (String transaction : ((Block) block).getTransactions()) {
                data.append(transaction);
            }

            // Create SHA-256 Hash
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(data.toString().getBytes(StandardCharsets.UTF_8));

            // Convert byte array to hex string
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-256 algorithm not found!");
        }
    }
}
