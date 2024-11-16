package com.system.simulation.security;

import com.system.simulation.devices.Router;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The PacketLoss class simulates packet loss in the network. It randomly drops a
 * percentage of packets to simulate the instability of network connections.
 */
public class PacketLoss {

    private static final Logger logger = LoggerFactory.getLogger(PacketLoss.class);

    private final Router router;
    private double lossPercentage = 0; // Percentage of packets to be dropped (0.0 to 1.0)

    /**
     * Constructs a PacketLoss object with the specified router and loss percentage.
     *
     * @param router The router where the packet loss simulation will take place.
     */
    public PacketLoss(Router router) {
        this.router = router;
        this.lossPercentage = lossPercentage;
    }

    public PacketLoss(Router router, Router router1, double lossPercentage) {
        this.router = router1;
        this.lossPercentage = lossPercentage;
    }

    /**
     * Simulates packet loss by randomly dropping packets with a given probability.
     */
    public void simulate() {
        try {
            logger.info("Simulating packet loss with a loss rate of {}%...", lossPercentage * 100);

            // Simulate the sending of packets, with some being dropped randomly
            int totalPackets = 0;

            logger.info("Packet loss simulation completed.");
        } catch (Exception e) {
            logger.error("An error occurred during packet loss simulation: {}", e.getMessage());
        }
    }
}
