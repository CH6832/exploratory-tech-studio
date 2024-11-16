package com.system.simulation.network.config;

import java.util.List;

/**
 * FirewallRules holds configuration rules for a firewall.
 * This includes IP addresses, protocols, and port configurations
 * that the firewall will allow or block based on predefined conditions.
 */
public class FirewallRules {

    private List<String> allowedIPs;
    private List<String> blockedIPs;
    private List<String> allowedProtocols;  // e.g., TCP, UDP, ICMP
    private List<Integer> allowedPorts;
    private List<Integer> blockedPorts;

    // Constructor
    public FirewallRules(List<String> allowedIPs, List<String> blockedIPs, List<String> allowedProtocols,
                         List<Integer> allowedPorts, List<Integer> blockedPorts) {
        this.allowedIPs = allowedIPs;
        this.blockedIPs = blockedIPs;
        this.allowedProtocols = allowedProtocols;
        this.allowedPorts = allowedPorts;
        this.blockedPorts = blockedPorts;
    }

    public FirewallRules() {

    }

    // Getters and setters for the attributes
    public List<String> getAllowedIPs() {
        return allowedIPs;
    }

    public void setAllowedIPs(List<String> allowedIPs) {
        this.allowedIPs = allowedIPs;
    }

    public List<String> getBlockedIPs() {
        return blockedIPs;
    }

    public void setBlockedIPs(List<String> blockedIPs) {
        this.blockedIPs = blockedIPs;
    }

    public List<String> getAllowedProtocols() {
        return allowedProtocols;
    }

    public void setAllowedProtocols(List<String> allowedProtocols) {
        this.allowedProtocols = allowedProtocols;
    }

    public List<Integer> getAllowedPorts() {
        return allowedPorts;
    }

    public void setAllowedPorts(List<Integer> allowedPorts) {
        this.allowedPorts = allowedPorts;
    }

    public List<Integer> getBlockedPorts() {
        return blockedPorts;
    }

    public void setBlockedPorts(List<Integer> blockedPorts) {
        this.blockedPorts = blockedPorts;
    }

    @Override
    public String toString() {
        return "FirewallRules{" +
                "allowedIPs=" + allowedIPs +
                ", blockedIPs=" + blockedIPs +
                ", allowedProtocols=" + allowedProtocols +
                ", allowedPorts=" + allowedPorts +
                ", blockedPorts=" + blockedPorts +
                '}';
    }

    public void addRule(String allow, String s) {

    }
}

