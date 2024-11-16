package com.system.simulation.devices;

import com.system.simulation.network.config.FirewallRules;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class Firewall {

    private static final Logger logger = LoggerFactory.getLogger(Firewall.class);

    private List<String> allowedIps;

    // Constructor
    public Firewall(FirewallRules allowedIps) {
        this.allowedIps = (List<String>) allowedIps;
    }

    public Firewall() {

    }

    /**
     * Applies the firewall rules to allow or block incoming traffic.
     */
    public void applyRules() {
        Object incomingIp = null;
        try {
            if (allowedIps.contains(incomingIp)) {
                logger.info("Incoming connection from {} allowed.", incomingIp);
            } else {
                logger.warn("Incoming connection from {} blocked by firewall.", incomingIp);
            }
        } catch (Exception e) {
            logger.error("Error applying firewall rules for {}: {}", incomingIp, e.getMessage());
        }
    }

    public void setAllowedIps(List<String> allowedIps) {
        this.allowedIps = allowedIps;
    }

    public void simulateMITM() {
    }

    public boolean filterPacket(String s) {
        return false;
    }

    public boolean isInMitmMode() {
        return false;
    }
}

