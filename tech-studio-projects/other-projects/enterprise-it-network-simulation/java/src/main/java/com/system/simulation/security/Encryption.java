package com.system.simulation.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class Encryption {

    private static final Logger logger = LoggerFactory.getLogger(Encryption.class);

    // Constructor for Encryption
    public Encryption() {
        // Initialization logic for encryption (if necessary)
    }

    /**
     * Simulates data encryption using SHA-256.
     *
     * @param data The data to encrypt.
     * @return The encrypted data (Base64 encoded).
     */
    public String encryptData(String data) {
        try {
            // Create SHA-256 hash of the data
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encryptedBytes = digest.digest(data.getBytes());
            String encryptedData = Base64.getEncoder().encodeToString(encryptedBytes);
            logger.info("Data encrypted successfully.");
            return encryptedData;
        } catch (NoSuchAlgorithmException e) {
            logger.error("Encryption failed: {}", e.getMessage());
            return null;
        }
    }

    /**
     * Decrypts the encrypted data (simulated as we are using one-way encryption here).
     *
     * @param encryptedData The encrypted data.
     * @return The decrypted data.
     */
    public String decryptData(String encryptedData) {
        // In real scenarios, decryption would happen here. For simulation, we return null.
        logger.warn("Decryption not supported for SHA-256 (one-way encryption).");
        return null;
    }
}
