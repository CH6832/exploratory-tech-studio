#!/bin/bash

################################################################################
# Script Name:       Ubuntu Automation Script
# Description:       This script sets up basic network configurations, including IP address,
#                    subnet mask, default gateway, DNS server, and firewall rules. Ensure to
#                    customize the values according to your network setup and requirements.
# Author:            Christoph Hartleb
# Date:              July 21, 2024
# Version:           1.1
# 
# Usage:             ./network_mgmt.sh
# 
# Notes:             
#   - Make sure to customize the script according to your specific requirements.
#   - Some tasks may require root privileges. Ensure proper permissions.
#   - Test the script thoroughly in a safe environment before production use.
# https://google.github.io/styleguide/shellguide.html
################################################################################

# Check if running as root
if [ "$(id -u)" -ne 0 ]; then
    echo "Please run this script as root."
    exit 1
fi

# Set network interface name
INTERFACE="eth0"

# Set IP address and subnet mask
IP_ADDRESS="192.168.1.100"
SUBNET_MASK="24"  # Use CIDR notation for easier handling

# Set gateway IP address
GATEWAY="192.168.1.1"

# Set DNS server
DNS_SERVER="8.8.8.8"

# Function to apply firewall rules
configure_firewall() {
    echo "Configuring firewall rules..."
    
    iptables -A INPUT -p tcp --dport 22 -j ACCEPT
    iptables -A INPUT -p tcp --dport 80 -j ACCEPT
    iptables -A INPUT -p tcp --dport 443 -j ACCEPT
    iptables -A OUTPUT -j ACCEPT
    iptables -t nat -A POSTROUTING -o $INTERFACE -j MASQUERADE
    echo "Firewall rules configured."
}

# Function to set network configurations
configure_network() {
    echo "Configuring network settings..."
    
    # Remove existing IP address
    ip addr flush dev $INTERFACE
    
    # Set new IP address
    ip addr add $IP_ADDRESS/$SUBNET_MASK dev $INTERFACE
    ip link set $INTERFACE up
    
    # Set default gateway
    ip route add default via $GATEWAY
    
    # Set DNS server
    echo "nameserver $DNS_SERVER" > /etc/resolv.conf
    
    echo "Network configuration completed."
}

# Function to enable IP forwarding
enable_ip_forwarding() {
    echo "Enabling IP forwarding..."
    echo 1 > /proc/sys/net/ipv4/ip_forward
    echo "IP forwarding enabled."
}

# Main function
main() {
    echo "Network Management Script"
    echo "------------------------"
    
    configure_firewall
    configure_network
    enable_ip_forwarding
    
    echo "Network management complete."
}

# Call the main function
main
