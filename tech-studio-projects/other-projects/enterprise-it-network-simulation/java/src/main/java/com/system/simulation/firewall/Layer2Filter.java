package com.system.simulation.firewall;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;

public class Layer2Filter {

    private static final Logger logger = LoggerFactory.getLogger(Layer2Filter.class);

    private Set<String> allowedMacAddresses;

    // Constructor
    public Layer2Filter() {
        allowedMacAddresses = new HashSet<>();
    }

    /**
     * Adds a MAC address to the allowed list.
     *
     * @param macAddress The MAC address to allow.
     */
    public void allowMacAddress(String macAddress) {
        allowedMacAddresses.add(macAddress);
        logger.info("Allowed MAC address: {}", macAddress);
    }

    /**
     * Filters an incoming packet by checking the MAC address.
     *
     * @param macAddress The MAC address of the incoming packet.
     * @return True if allowed, false otherwise.
     */
    public boolean filterPacket(String macAddress) {
        try {
            if (allowedMacAddresses.contains(macAddress)) {
                logger.info("Packet from MAC address {} allowed.", macAddress);
                return true;
            } else {
                logger.warn("Packet from MAC address {} blocked by Layer 2 filter.", macAddress);
                return false;
            }
        } catch (Exception e) {
            logger.error("Error filtering packet from MAC address {}: {}", macAddress, e.getMessage());
            return false;
        }
    }
}

