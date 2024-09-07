#!/bin/bash

################################################################################
# Script Name:       Ubuntu Automation Script
# Description:       This script provides functions to check for system errors,
#                    hardware failures, and software conflicts. It can be extended
#                    with more specific commands based on your system's configuration
#                    and the types of issues encountered.
# Author:            Christoph Hartleb
# Date:              July 21, 2024
# Version:           1.1
# 
# Usage:             ./ubuntu_automation.sh
# 
# Notes:             
#   - Customize the script according to your specific requirements.
#   - Some tasks may require root privileges. Ensure proper permissions.
#   - Test the script thoroughly in a safe environment before production use.
# https://google.github.io/styleguide/shellguide.html
################################################################################

# Function to check system errors
check_system_errors() {
    echo "Checking system errors..."
    local errors
    errors=$(dmesg | grep -i error)
    if [ -n "$errors" ]; then
        echo "$errors"
    else
        echo "No system errors found."
    fi
    echo ""
}

# Function to check hardware failures
check_hardware_failures() {
    echo "Checking hardware failures..."
    
    # Check SMART status for disks
    if command -v smartctl > /dev/null; then
        echo "Checking SMART status..."
        smartctl --all /dev/sda  # Adjust /dev/sda to your disk device
    else
        echo "smartctl not found. Install it with 'sudo apt-get install smartmontools'."
    fi
    
    # Check memory status (if available)
    if command -v memtest86+ > /dev/null; then
        echo "Checking memory..."
        memtest86+  # Run memtest or provide instructions
    else
        echo "memtest86+ not found. Install it with 'sudo apt-get install memtest86+'."
    fi
    
    echo ""
}

# Function to check software conflicts
check_software_conflicts() {
    echo "Checking software conflicts..."
    
    # Check for held packages
    held_packages=$(dpkg --get-selections | grep hold)
    if [ -n "$held_packages" ]; then
        echo "Held packages found:"
        echo "$held_packages"
    else
        echo "No held packages found."
    fi

    # Check for broken dependencies
    broken_dependencies=$(sudo apt-get check 2>&1 | grep -i "broken")
    if [ -n "$broken_dependencies" ]; then
        echo "Broken dependencies found:"
        echo "$broken_dependencies"
    else
        echo "No broken dependencies found."
    fi

    echo ""
}

# Main function
main() {
    echo "Troubleshooting and Debugging Script"
    echo "------------------------------------"
    echo ""
    check_system_errors
    check_hardware_failures
    check_software_conflicts
    echo "Troubleshooting and debugging complete."
}

# Call the main function
main
