package com.system.simulation.network.packets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UdpPacket {

    private static final Logger logger = LoggerFactory.getLogger(UdpPacket.class);
    private String sourceIP;
    private String destIP;
    private String data;

    // Constructor for UdpPacket
    public UdpPacket(String sourceIP, String destIP, String data) {
        this.sourceIP = sourceIP;
        this.destIP = destIP;
        this.data = data;
    }

    /**
     * Simulates sending the UDP packet.
     */
    public void sendPacket() {
        try {
            logger.info("Sending UDP packet from {} to {} with data: {}", sourceIP, destIP, data);
            // Simulate network transmission with low latency
        } catch (Exception e) {
            logger.error("Error sending UDP packet: {}", e.getMessage());
        }
    }

    // Getters and setters for sourceIP, destIP, and data
}

