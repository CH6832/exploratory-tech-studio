package com.system.simulation.security;

import com.system.simulation.devices.Firewall;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MitmAttack {

    private static final Logger logger = LoggerFactory.getLogger(MitmAttack.class);

    // Constructor for MitmAttack
    public MitmAttack(Firewall firewall) {
        // Initialization logic for attack (if needed)
    }

    /**
     * Simulates a Man-in-the-Middle (MITM) attack by intercepting communication.
     *
     * @param sender The sender of the message.
     * @param receiver The intended receiver of the message.
     * @param message The message being sent.
     * @return The intercepted message.
     */
    public String interceptMessage(String sender, String receiver, String message) {
        try {
            // Log the interception
            logger.warn("Intercepting message from {} to {}: {}", sender, receiver, message);
            // Simulate altering the message (e.g., changing "Hello" to "Goodbye")
            String alteredMessage = message.replace("Hello", "Goodbye");
            logger.info("Message altered during MITM attack: {}", alteredMessage);
            return alteredMessage;
        } catch (Exception e) {
            logger.error("Error during MITM attack simulation: {}", e.getMessage());
            return null;
        }
    }

    public void launch() {

    }
}
