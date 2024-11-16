package com.system.simulation.network;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class Switch {

    private static final Logger logger = LoggerFactory.getLogger(Switch.class);

    // Simulate MAC address table (map)
    private Map<String, String> macTable;

    public Switch(Map<String, String> macTable) {
        this.macTable = macTable;
    }

    /**
     * Forwards the packet to the correct destination port based on the MAC address.
     *
     * @param sourceMac The source MAC address.
     * @param destMac The destination MAC address.
     * @param packet The packet to forward.
     */
    public void forwardPacket(String sourceMac, String destMac, String packet) {
        try {
            if (macTable.containsKey(destMac)) {
                String port = macTable.get(destMac);
                logger.info("Forwarding packet from {} to {} on port {}", sourceMac, destMac, port);
            } else {
                logger.warn("Destination MAC address not found. Broadcasting packet.");
                // Simulate broadcast
            }
        } catch (Exception e) {
            logger.error("Error forwarding packet: {}", e.getMessage());
        }
    }
}
