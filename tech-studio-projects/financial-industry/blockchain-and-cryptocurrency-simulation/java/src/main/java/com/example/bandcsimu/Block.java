package com.example.bandcsimu;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.util.List;

/**
 * The Block class represents a block in the blockchain.
 * Each block contains an index, a reference to the previous block's hash, a list of transactions,
 * a timestamp, and its own hash. The hash ensures the integrity and immutability of the block's data.
 * 
 * The blockchain is secured by cryptographic hashing, where the block's hash depends on its contents.
 * Any tampering with the block will invalidate the hash, ensuring the chain's security.
 */
public class Block {

    // Fields representing the block's properties
    private int index;  // The position of this block in the chain.
    private String previousHash;  // The hash of the previous block in the chain.
    private List<String> transactions;  // List of transactions included in this block.
    private String timestamp;  // Timestamp when the block was created.
    private String hash;  // The cryptographic hash of this block.

    /**
     * Constructor for creating a new Block.
     * 
     * @param index The index or position of this block in the blockchain.
     * @param previousHash The hash of the previous block, linking this block to the chain.
     * @param block The list of transactions included in this block.
     * 
     * Why it's important:
     * - The `index` helps determine the block's position in the chain.
     * - The `previousHash` ensures continuity and immutability in the chain by linking this block 
     *   to the one before it.
     * - The transactions represent the core function of a blockchain, which is recording the transfer of assets.
     */
    public Block(int index, String previousHash, Object block) {
        this.index = index;
        this.previousHash = previousHash;
        this.transactions = (List<String>) block;  // Transactions data is cast into a list.
        this.timestamp = Instant.now().toString();  // Capture the current timestamp when the block is created.
        this.hash = calculateHash(index, previousHash, block, timestamp);  // Calculate the block's hash based on its contents.
    }

    // Getter for the block's hash
    public String getHash() {
        return hash;
    }

    // Getter for the previous block's hash
    public String getPreviousHash() {
        return previousHash;
    }

    // Getter for the transactions list
    public List<String> getTransactions() {
        return transactions;
    }

    // Getter for the block's timestamp
    public String getTimestamp() {
        return timestamp;
    }

    // Getter for the block's index
    public int getIndex() {
        return index;
    }

    /**
     * Prints the block's information, including its index, previous hash, current hash, and transactions.
     * 
     * Why it's important:
     * - Displaying block information is useful for debugging, validation, and monitoring the blockchain.
     * - It allows developers and users to verify the contents of the block.
     */
    public void printBlock() {
        System.out.println("Block " + index + ":");
        System.out.println("Previous Hash: " + previousHash);
        System.out.println("Hash: " + hash);
        System.out.println("Transactions:");
        for (String tx : transactions) {
            System.out.println("  " + tx);
        }
    }

    /**
     * Static method to calculate the cryptographic hash of a block.
     * 
     * @param index The block's index in the chain.
     * @param previousHash The hash of the previous block, providing continuity.
     * @param block The list of transactions included in this block.
     * @param timestamp The timestamp of the block's creation.
     * @return The SHA-256 hash of the block as a hexadecimal string.
     * 
     * What it's doing:
     * - It combines the block's index, previous hash, timestamp, and transactions into a single string.
     * - This string is hashed using SHA-256, ensuring that any change in the block's contents 
     *   will result in a different hash.
     * 
     * Why it's important:
     * - Cryptographic hashes are critical in blockchains because they guarantee the integrity of the block. 
     *   If someone alters the block, the hash will no longer match, making the change easily detectable.
     */
    public static String calculateHash(int index, String previousHash, Object block, String timestamp) {
        try {
            // Build the data string by concatenating the block's index, previous hash, and timestamp.
            StringBuilder data = new StringBuilder();
            data.append(index).append(previousHash).append(timestamp);

            // Append each transaction to the data string.
            for (String transaction : ((Block) block).getTransactions()) {
                data.append(transaction);
            }

            // Create a SHA-256 digest (hash) of the concatenated string.
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(data.toString().getBytes(StandardCharsets.UTF_8));

            // Convert the hash bytes to a hexadecimal string.
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);  // Convert byte to hex
                if (hex.length() == 1) hexString.append('0');  // Pad with leading zero if needed
                hexString.append(hex);
            }

            return hexString.toString();  // Return the hex string representing the hash.

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-256 algorithm not found!");  // SHA-256 should always be available.
        }
    }
}
