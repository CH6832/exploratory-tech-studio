package com.system.simulation.firewall;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;

public class TcpUdpLimits {

    private static final Logger logger = LoggerFactory.getLogger(TcpUdpLimits.class);

    private Set<Integer> allowedTcpPorts;
    private Set<Integer> allowedUdpPorts;

    // Constructor
    public TcpUdpLimits() {
        allowedTcpPorts = new HashSet<>();
        allowedUdpPorts = new HashSet<>();
    }

    /**
     * Adds a TCP port to the allowed list.
     *
     * @param port The TCP port to allow.
     */
    public void allowTcpPort(int port) {
        allowedTcpPorts.add(port);
        logger.info("Allowed TCP port: {}", port);
    }

    /**
     * Adds a UDP port to the allowed list.
     *
     * @param port The UDP port to allow.
     */
    public void allowUdpPort(int port) {
        allowedUdpPorts.add(port);
        logger.info("Allowed UDP port: {}", port);
    }

    /**
     * Checks if a TCP connection is allowed based on port.
     *
     * @param port The port for the TCP connection.
     * @return True if allowed, false otherwise.
     */
    public boolean isTcpAllowed(int port) {
        try {
            if (allowedTcpPorts.contains(port)) {
                logger.info("TCP connection to port {} allowed.", port);
                return true;
            } else {
                logger.warn("TCP connection to port {} blocked by firewall.", port);
                return false;
            }
        } catch (Exception e) {
            logger.error("Error checking TCP port {}: {}", port, e.getMessage());
            return false;
        }
    }

    /**
     * Checks if a UDP connection is allowed based on port.
     *
     * @param port The port for the UDP connection.
     * @return True if allowed, false otherwise.
     */
    public boolean isUdpAllowed(int port) {
        try {
            if (allowedUdpPorts.contains(port)) {
                logger.info("UDP connection to port {} allowed.", port);
                return true;
            } else {
                logger.warn("UDP connection to port {} blocked by firewall.", port);
                return false;
            }
        } catch (Exception e) {
            logger.error("Error checking UDP port {}: {}", port, e.getMessage());
            return false;
        }
    }
}
