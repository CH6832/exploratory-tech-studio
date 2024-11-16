package com.system.simulation.security;

import com.system.simulation.devices.Router;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The DDoSAttack class simulates a Distributed Denial of Service (DDoS) attack.
 * In this attack, the router is overwhelmed with requests, potentially exhausting
 * its resources and causing a denial of service.
 */
public class DDoSAttack {

    private static final Logger logger = LoggerFactory.getLogger(DDoSAttack.class);

    private final Router router;
    private final int attackDuration;  // Duration for the attack (in seconds)
    private final int packetsPerSecond; // Number of packets to send per second

    /**
     * Constructs the DDoSAttack with the specified router and attack parameters.
     *
     * @param router The router to attack.
     * @param attackDuration The duration of the attack in seconds.
     * @param packetsPerSecond The number of packets to send per second during the attack.
     */
    public DDoSAttack(Router router, int attackDuration, int packetsPerSecond) {
        this.router = router;
        this.attackDuration = attackDuration;
        this.packetsPerSecond = packetsPerSecond;
    }

    public DDoSAttack(Router router, Router router1, int attackDuration, int packetsPerSecond) {

        this.router = router1;
        this.attackDuration = attackDuration;
        this.packetsPerSecond = packetsPerSecond;
    }

    public DDoSAttack(Router router) {

        this.router = null;
        attackDuration = 0;
        packetsPerSecond = 0;
    }

    /**
     * Launches the DDoS attack by sending a high volume of requests to the router.
     * The router is overwhelmed with packets over a specified duration.
     */
    public void launch() {
        try {
            logger.info("Launching DDoS Attack on router {}...", router.getIpAddress());

            // Simulate the DDoS attack by sending packets
            long startTime = System.currentTimeMillis();
            long endTime = startTime + (attackDuration * 1000);

            while (System.currentTimeMillis() < endTime) {
                for (int i = 0; i < packetsPerSecond; i++) {
                    sendPacket(i);
                }
                // Simulate a short delay between sending batches of packets
                Thread.sleep(1000);  // Sleep for 1 second between batches of packets
            }

            logger.info("DDoS Attack on router {} completed successfully.", router.getIpAddress());
        } catch (InterruptedException e) {
            logger.error("Error during DDoS Attack: {}", e.getMessage());
        }
    }

    /**
     * Simulates sending a single packet to the router.
     * In a real-world attack, this would involve sending network packets to
     * overwhelm the router's processing capacity.
     *
     * @param packetNumber The number of the packet being sent (used for logging purposes).
     */
    private void sendPacket(int packetNumber) {
        // Log the packet being sent
        logger.info("Sending DDoS packet {} to router {}...", packetNumber, router.getIpAddress());

        // Simulate the router processing the packet (in a real-world scenario, the router would
        // process the packet and might perform actions like dropping packets, etc.)
        router.receivePacket("DDoS Request " + packetNumber);
    }
}

