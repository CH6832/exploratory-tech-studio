package com.system.simulation.network.routing;

import java.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Simulated RIP (Routing Information Protocol) implementation.
 * This class models basic RIP functions such as routing table updates
 * and route advertisements in a network.
 */
public class RIP {

    private static final Logger logger = LoggerFactory.getLogger(RIP.class);

    private final Map<String, Route> routingTable;
    private final int infinityMetric = 16; // RIP uses 16 as the infinity metric to indicate unreachable routes

    public void printRoutingTable(Map<String, String> routingTable, String s, String s1) {
    }

    public void updateRoutingTable(Map<String, String> routingTable, String s, String s1) {
    }

    /**
     * Represents a routing entry with destination, metric, and next hop information.
     */
    private static class Route {
        String destination;
        int metric; // Number of hops to destination
        String nextHop;

        Route(String destination, int metric, String nextHop) {
            this.destination = destination;
            this.metric = metric;
            this.nextHop = nextHop;
        }
    }

    public RIP() {
        this.routingTable = new HashMap<>();
    }

    /**
     * Adds or updates a route in the routing table.
     * @param destination The destination network.
     * @param metric The hop count to the destination.
     * @param nextHop The next hop router.
     */
    public void addOrUpdateRoute(String destination, int metric, String nextHop) {
        if (metric >= infinityMetric) {
            logger.warn("Metric exceeds limit for {}. Marking as unreachable.", destination);
            routingTable.remove(destination);
            return;
        }

        Route currentRoute = routingTable.get(destination);
        if (currentRoute == null || metric < currentRoute.metric) {
            routingTable.put(destination, new Route(destination, metric, nextHop));
            logger.info("Route updated for destination {}: metric = {}, next hop = {}", destination, metric, nextHop);
        }
    }

    /**
     * Simulates the advertisement of the routing table to neighboring routers.
     * @param neighbor The neighboring router to send updates to.
     */
    public void advertiseRoutes(RIP neighbor) {
        for (Route route : routingTable.values()) {
            // Apply "split horizon" to prevent routing loops
            if (!route.nextHop.equals(neighbor.toString())) {
                neighbor.receiveRoute(route.destination, route.metric + 1, this.toString());
            }
        }
    }

    /**
     * Receives a route update from a neighboring router.
     * @param destination The destination network.
     * @param metric The hop count to the destination.
     * @param nextHop The router from which the route was advertised.
     */
    public void receiveRoute(String destination, int metric, String nextHop) {
        addOrUpdateRoute(destination, metric, nextHop);
    }

    /**
     * Logs the current state of the routing table.
     */
    public void printRoutingTable() {
        logger.info("Current Routing Table:");
        for (Map.Entry<String, Route> entry : routingTable.entrySet()) {
            Route route = entry.getValue();
            logger.info("Destination: {}, Metric: {}, Next Hop: {}", route.destination, route.metric, route.nextHop);
        }
    }
}
