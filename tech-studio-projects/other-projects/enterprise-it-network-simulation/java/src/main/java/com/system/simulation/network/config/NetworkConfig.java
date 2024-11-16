package com.system.simulation.network.config;

import java.util.List;
import java.util.Map;

/**
 * NetworkConfig holds the network configuration parameters needed for the simulation.
 * This includes network name, IP range, gateway information, DNS servers, subnet mask,
 * and the list of supported routing protocols.
 */
public class NetworkConfig {

    private String networkName;              // Name of the network
    private String networkIPRange;           // IP range for the network
    private String gatewayIP;                // Gateway IP address for the network
    private List<String> dnsServers;         // List of DNS server IP addresses
    private int subnetMask;                  // Subnet mask for the network
    private List<String> routingProtocols;   // Supported routing protocols (e.g., RIP, OSPF)

    /**
     * Constructor for initializing a NetworkConfig instance.
     *
     * @param networkName       the name of the network.
     * @param networkIPRange    the IP range of the network.
     * @param gatewayIP         the IP address of the network's gateway.
     * @param dnsServers        the list of DNS servers.
     * @param subnetMask        the subnet mask for the network.
     * @param routingProtocols  the list of routing protocols to be used in the network.
     */
    public NetworkConfig(String networkName, String networkIPRange, String gatewayIP,
                         List<String> dnsServers, int subnetMask, List<String> routingProtocols) {
        this.networkName = networkName;
        this.networkIPRange = networkIPRange;
        this.gatewayIP = gatewayIP;
        this.dnsServers = dnsServers;
        this.subnetMask = subnetMask;
        this.routingProtocols = routingProtocols;
    }

    public NetworkConfig(String networkName, int i, String corporateNet) {
    }

    /**
     * @return the name of the network.
     */
    public String getNetworkName() {
        return networkName;
    }

    /**
     * Sets the network name.
     *
     * @param networkName the name to set for the network.
     */
    public void setNetworkName(String networkName) {
        this.networkName = networkName;
    }

    /**
     * @return the IP range of the network.
     */
    public String getNetworkIPRange() {
        return networkIPRange;
    }

    /**
     * Sets the IP range for the network.
     *
     * @param networkIPRange the IP range to set.
     */
    public void setNetworkIPRange(String networkIPRange) {
        this.networkIPRange = networkIPRange;
    }

    /**
     * @return the gateway IP address of the network.
     */
    public String getGatewayIP() {
        return gatewayIP;
    }

    /**
     * Sets the gateway IP address for the network.
     *
     * @param gatewayIP the IP address of the gateway to set.
     */
    public void setGatewayIP(String gatewayIP) {
        this.gatewayIP = gatewayIP;
    }

    /**
     * @return the list of DNS servers in the network.
     */
    public List<String> getDnsServers() {
        return dnsServers;
    }

    /**
     * Sets the list of DNS servers.
     *
     * @param dnsServers the list of DNS servers to set.
     */
    public void setDnsServers(List<String> dnsServers) {
        this.dnsServers = dnsServers;
    }

    /**
     * @return the subnet mask of the network.
     */
    public int getSubnetMask() {
        return subnetMask;
    }

    /**
     * Sets the subnet mask for the network.
     *
     * @param subnetMask the subnet mask to set.
     */
    public void setSubnetMask(int subnetMask) {
        this.subnetMask = subnetMask;
    }

    /**
     * @return the list of routing protocols used in the network.
     */
    public List<String> getRoutingProtocols() {
        return routingProtocols;
    }

    /**
     * Sets the routing protocols for the network.
     *
     * @param routingProtocols the list of routing protocols to set.
     */
    public void setRoutingProtocols(List<String> routingProtocols) {
        this.routingProtocols = routingProtocols;
    }

    /**
     * Retrieves the router name, assuming it to be the network name.
     *
     * @return the network name, which may be used as the router's name.
     */
    public String getRouterName() {
        return networkName;
    }

    /**
     * Retrieves the IP address of the network's gateway.
     *
     * @return the gateway IP address of the network.
     */
    public String getIpAddress() {
        return gatewayIP;
    }

    /**
     * Retrieves a routing table representation.
     * This is a placeholder implementation; in a real scenario, it would return the configured routes.
     *
     * @return an empty map as a placeholder for the routing table.
     */
    public Map<String, String> getRoutingTable() {
        return Map.of(); // Placeholder implementation
    }

    /**
     * Provides a string representation of the NetworkConfig instance.
     *
     * @return a string summarizing the network configuration.
     */
    @Override
    public String toString() {
        return "NetworkConfig{" +
                "networkName='" + networkName + '\'' +
                ", networkIPRange='" + networkIPRange + '\'' +
                ", gatewayIP='" + gatewayIP + '\'' +
                ", dnsServers=" + dnsServers +
                ", subnetMask=" + subnetMask +
                ", routingProtocols=" + routingProtocols +
                '}';
    }
}
