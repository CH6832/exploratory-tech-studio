package com.system.simulation.firewall;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QoS {

    private static final Logger logger = LoggerFactory.getLogger(QoS.class);

    // Constructor
    public QoS() {
        // Initialization logic for QoS (if needed)
    }

    /**
     * Prioritizes network traffic based on the type of service.
     *
     * @param serviceType The type of service (e.g., VoIP, Video, etc.).
     * @param data The data to prioritize.
     * @return Prioritized data (simulated).
     */
    public String prioritizeTraffic(String serviceType, String data) {
        try {
            if ("VoIP".equalsIgnoreCase(serviceType)) {
                logger.info("Prioritizing VoIP traffic.");
                // Simulate low latency for VoIP traffic
            } else if ("Video".equalsIgnoreCase(serviceType)) {
                logger.info("Prioritizing Video traffic.");
                // Simulate lower latency for video streaming
            } else {
                logger.info("No special priority for service: {}", serviceType);
            }
            return "Prioritized: " + data;
        } catch (Exception e) {
            logger.error("Error prioritizing traffic for {}: {}", serviceType, e.getMessage());
            return "Error in traffic prioritization";
        }
    }
}
