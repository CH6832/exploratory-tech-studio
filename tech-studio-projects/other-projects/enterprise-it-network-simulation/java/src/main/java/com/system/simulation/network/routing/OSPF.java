package com.system.simulation.network.routing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class OSPF {

    private static final Logger logger = LoggerFactory.getLogger(OSPF.class);

    // Constructor for OSPF
    public OSPF() {
        // Initialization logic for OSPF (if needed)
    }

    /**
     * Simulates the OSPF routing table calculation.
     *
     * @param routingTable The current routing table.
     * @param destination The destination address.
     * @param nextHop The next hop address for routing.
     */
    public void calculateShortestPath(Map<String, String> routingTable, String destination, String nextHop) {
        try {
            // Simulating OSPF shortest path calculation
            routingTable.put(destination, nextHop);
            logger.info("OSPF Routing table updated: Destination {} -> Next Hop {}", destination, nextHop);
        } catch (Exception e) {
            logger.error("Error calculating shortest path in OSPF: {}", e.getMessage());
        }
    }
}
