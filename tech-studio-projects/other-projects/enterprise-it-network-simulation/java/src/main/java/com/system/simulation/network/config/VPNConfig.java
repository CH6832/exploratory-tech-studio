package com.system.simulation.network.config;

import java.util.List;

/**
 * VPNConfig stores the configuration details for the VPN server,
 * including encryption algorithms, tunneling protocols, allowed IPs, and more.
 */
public class VPNConfig {

    private String encryptionAlgorithm;
    private String tunnelingProtocol;
    private List<String> allowedIPRanges;
    private int vpnPort;
    private boolean compressionEnabled;
    private boolean loggingEnabled;

    // Constructor
    public VPNConfig(String encryptionAlgorithm, String tunnelingProtocol, List<String> allowedIPRanges,
                     int vpnPort, boolean compressionEnabled, boolean loggingEnabled) {
        this.encryptionAlgorithm = encryptionAlgorithm;
        this.tunnelingProtocol = tunnelingProtocol;
        this.allowedIPRanges = allowedIPRanges;
        this.vpnPort = vpnPort;
        this.compressionEnabled = compressionEnabled;
        this.loggingEnabled = loggingEnabled;
    }

    public VPNConfig(String corporateVPN, String tunnelingProtocol, int i) {
    }

    // Getters and setters for each attribute
    public String getEncryptionAlgorithm() {
        return encryptionAlgorithm;
    }

    public void setEncryptionAlgorithm(String encryptionAlgorithm) {
        this.encryptionAlgorithm = encryptionAlgorithm;
    }

    public String getTunnelingProtocol() {
        return tunnelingProtocol;
    }

    public void setTunnelingProtocol(String tunnelingProtocol) {
        this.tunnelingProtocol = tunnelingProtocol;
    }

    public List<String> getAllowedIPRanges() {
        return allowedIPRanges;
    }

    public void setAllowedIPRanges(List<String> allowedIPRanges) {
        this.allowedIPRanges = allowedIPRanges;
    }

    public int getVpnPort() {
        return vpnPort;
    }

    public void setVpnPort(int vpnPort) {
        this.vpnPort = vpnPort;
    }

    public boolean isCompressionEnabled() {
        return compressionEnabled;
    }

    public void setCompressionEnabled(boolean compressionEnabled) {
        this.compressionEnabled = compressionEnabled;
    }

    public boolean isLoggingEnabled() {
        return loggingEnabled;
    }

    public void setLoggingEnabled(boolean loggingEnabled) {
        this.loggingEnabled = loggingEnabled;
    }

    // Display VPN configuration
    @Override
    public String toString() {
        return "VPNConfig{" +
                "encryptionAlgorithm='" + encryptionAlgorithm + '\'' +
                ", tunnelingProtocol='" + tunnelingProtocol + '\'' +
                ", allowedIPRanges=" + allowedIPRanges +
                ", vpnPort=" + vpnPort +
                ", compressionEnabled=" + compressionEnabled +
                ", loggingEnabled=" + loggingEnabled +
                '}';
    }
}
