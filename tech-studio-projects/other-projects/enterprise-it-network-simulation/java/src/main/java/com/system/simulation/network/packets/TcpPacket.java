package com.system.simulation.network.packets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TcpPacket {

    private static final Logger logger = LoggerFactory.getLogger(TcpPacket.class);
    private String sourceIP;
    private String destIP;
    private String data;

    // Constructor for TcpPacket
    public TcpPacket(String sourceIP, String destIP, String data) {
        this.sourceIP = sourceIP;
        this.destIP = destIP;
        this.data = data;
    }

    /**
     * Simulates sending the TCP packet.
     */
    public void sendPacket() {
        try {
            logger.info("Sending TCP packet from {} to {} with data: {}", sourceIP, destIP, data);
            // Simulate network transmission with low latency
        } catch (Exception e) {
            logger.error("Error sending TCP packet: {}", e.getMessage());
        }
    }

    // Getters and setters for sourceIP, destIP, and data
}
