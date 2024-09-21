#!/bin/bash

################################################################################
# Script Name:       Ubuntu Automation Script
# Description:       Monitors system metrics such as CPU usage, memory usage, disk usage, and system load average.
#                    Provides real-time updates and logs the results.
# Author:            Christoph Hartleb
# Date:              July 21, 2024
# Version:           1.1
# 
# Usage:             ./system_monitoring.sh
# 
# Notes:             
#   - Make sure to customize the script according to your specific requirements.
#   - Some tasks may require root privileges. Ensure proper permissions.
#   - Test the script thoroughly in a safe environment before production use.
# https://google.github.io/styleguide/shellguide.html
################################################################################

# Define thresholds
CPU_THRESHOLD=80
MEMORY_THRESHOLD=80
DISK_THRESHOLD=80
LOAD_THRESHOLD=2.0  # Load average threshold

# Function to check CPU usage
check_cpu() {
    local CPU_USAGE
    CPU_USAGE=$(top -bn1 | grep "Cpu(s)" | sed "s/.*, *\([0-9.]*\)%* id.*/\1/" | awk '{print 100 - $1}')
    
    if [ "$(echo "$CPU_USAGE > $CPU_THRESHOLD" | bc)" -eq 1 ]; then
        echo "High CPU Usage: $CPU_USAGE%"
    else
        echo "CPU Usage: $CPU_USAGE%"
    fi
}

# Function to check memory usage
check_memory() {
    local MEMORY_USAGE
    MEMORY_USAGE=$(free | grep Mem | awk '{print $3/$2 * 100.0}')
    
    if [ "$(echo "$MEMORY_USAGE > $MEMORY_THRESHOLD" | bc)" -eq 1 ]; then
        echo "High Memory Usage: $MEMORY_USAGE%"
    else
        echo "Memory Usage: $MEMORY_USAGE%"
    fi
}

# Function to check disk usage
check_disk() {
    local DISK_USAGE
    DISK_USAGE=$(df -h / | awk 'NR==2 {print $5}' | sed 's/%//')
    
    if [ "$DISK_USAGE" -gt "$DISK_THRESHOLD" ]; then
        echo "High Disk Usage: $DISK_USAGE%"
    else
        echo "Disk Usage: $DISK_USAGE%"
    fi
}

# Function to check disk usage for specific directories
check_disk_space() {
    local dir
    local USAGE
    for dir in "/var/log" "/tmp" "/home"; do
        USAGE=$(df -h "$dir" | awk 'NR==2 {print $5}' | sed 's/%//')
        echo "Disk Usage for $dir: $USAGE%"
    done
}

# Function to check system load average
check_load_average() {
    local LOAD_AVERAGE
    LOAD_AVERAGE=$(uptime | awk -F'average:' '{print $2}' | awk '{print $1}')
    
    if [ "$(echo "$LOAD_AVERAGE > $LOAD_THRESHOLD" | bc)" -eq 1 ]; then
        echo "High System Load Average: $LOAD_AVERAGE"
    else
        echo "System Load Average: $LOAD_AVERAGE"
    fi
}

# Function to check logged-in users
check_logged_in_users() {
    local LOGGED_IN_USERS
    LOGGED_IN_USERS=$(who | wc -l)
    echo "Logged-in Users: $LOGGED_IN_USERS"
}

# Main function
main() {
    echo "System Monitoring - Press Ctrl+C to exit"
    while true; do
        echo "$(date)"
        check_cpu
        check_memory
        check_disk
        check_disk_space
        check_load_average
        check_logged_in_users
        echo ""
        sleep 5  # Adjust the interval as needed (in seconds)
    done
}

# Call the main function
main
