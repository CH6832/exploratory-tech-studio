#!/bin/bash

################################################################################
# Script Name:       Automated Incident Response Script
# Description:       Simulates an automated incident response for a detected security incident.
#                    Logs incident details, notifies the security team, captures system state,
#                    and performs containment and mitigation actions.
# Author:            Christoph Hartleb
# Date:              July 21, 2024
# Version:           1.1
# 
# Usage:             ./detect_security_incidents.sh [--incident "DETAILS"] [--email "EMAIL"] [--log "LOG_DIR"]
# 
# Options:
#   --incident       Details of the detected security incident.
#   --email          Email address to notify the security team.
#   --log            Directory to store logs and system state information.
#   -h, --help       Display this help message.
# 
# Notes:             
#   - Ensure mail utility is configured for email notifications.
#   - Ensure the script has proper permissions to access and modify system logs.
#   - Test the script thoroughly in a safe environment before production use.
# https://google.github.io/styleguide/shellguide.html
################################################################################

# Default configuration
EMAIL="securityteam@example.com"
LOG_DIR="/var/log/security_incidents"
INCIDENT="Unauthorized access attempt detected from IP address 192.168.1.100"

# Function to display help
display_help() {
    echo "Usage: $0 [--incident \"DETAILS\"] [--email \"EMAIL\"] [--log \"LOG_DIR\"]"
    echo
    echo "Simulates an automated incident response for a detected security incident."
    echo
    echo "Options:"
    echo "   --incident       Details of the detected security incident."
    echo "   --email          Email address to notify the security team."
    echo "   --log            Directory to store logs and system state information."
    echo "   -h, --help       Display this help message."
    echo
}

# Parse command-line arguments
while [[ "$#" -gt 0 ]]; do
    case $1 in
        --incident) INCIDENT="$2"; shift ;;
        --email) EMAIL="$2"; shift ;;
        --log) LOG_DIR="$2"; shift ;;
        -h|--help) display_help; exit 0 ;;
        *) echo "Unknown parameter passed: $1"; display_help; exit 1 ;;
    esac
    shift
done

# Function to handle a detected security incident
handle_security_incident() {
    echo "Security Incident Detected!"
    echo "Taking initial response actions..."
    
    # Ensure log directory exists
    mkdir -p "$LOG_DIR"
    
    # Log the incident
    echo "$(date): Security incident detected. Details: $INCIDENT" >> "$LOG_DIR/security_incidents.log"
    if [ $? -ne 0 ]; then
        echo "Error: Failed to log incident details."
        exit 1
    fi
    
    # Notify the security team via email
    echo "Security incident detected. Details: $INCIDENT" | mail -s "Security Incident Alert" "$EMAIL"
    if [ $? -ne 0 ]; then
        echo "Error: Failed to send email notification."
        exit 1
    fi
    
    # Capture system state information
    echo "Capturing system state information..."
    cp /var/log/auth.log "$LOG_DIR/auth.log_backup"
    cp /etc/passwd "$LOG_DIR/passwd_backup"
    cp /etc/shadow "$LOG_DIR/shadow_backup"
    cp /etc/group "$LOG_DIR/group_backup"
    cp /var/log/syslog "$LOG_DIR/syslog_backup"
    if [ $? -ne 0 ]; then
        echo "Error: Failed to capture system state information."
        exit 1
    fi
    
    # Take actions to contain and mitigate the incident
    echo "Taking actions to contain and mitigate the incident..."
    # Example actions (customize as needed):
    # - Isolate affected systems
    # - Disable compromised accounts
    # - Update firewall rules
    # - Perform additional investigations
    
    echo "Initial response actions completed."
}

# Main function
main() {
    echo "Automated Incident Response Script"
    echo "---------------------------------"
    
    # Confirm incident handling
    read -p "Are you sure you want to handle the incident? (y/n): " choice
    case "$choice" in
        [yY]|[yY][eE][sS])
            handle_security_incident
            ;;
        *)
            echo "Incident handling aborted."
            ;;
    esac
}

# Call the main function
main
