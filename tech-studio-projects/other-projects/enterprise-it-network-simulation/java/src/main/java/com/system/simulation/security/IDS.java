package com.system.simulation.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class IDS {

    private static final Logger logger = LoggerFactory.getLogger(IDS.class);

    // Constructor for IDS
    public IDS() {
        // Initialization logic for IDS (if needed)
    }

    /**
     * Simulates intrusion detection by analyzing network traffic for suspicious patterns.
     *
     * @param trafficList A list of network traffic events to analyze.
     * @return True if an intrusion is detected, false otherwise.
     */
    public boolean detectIntrusion(List<String> trafficList) {
        try {
            // Iterate through the traffic list and look for patterns
            for (String traffic : trafficList) {
                if (isSuspicious(traffic)) {
                    logger.warn("Suspicious traffic detected: {}", traffic);
                    return true; // Intrusion detected
                }
            }
            logger.info("No intrusion detected.");
            return false;
        } catch (Exception e) {
            logger.error("Error analyzing traffic: {}", e.getMessage());
            return false;
        }
    }

    /**
     * Simulates basic pattern matching to detect suspicious traffic.
     *
     * @param traffic The network traffic to check.
     * @return True if traffic is suspicious, false otherwise.
     */
    private boolean isSuspicious(String traffic) {
        // Simulated check for suspicious keywords or patterns
        return traffic.contains("suspicious");
    }
}
