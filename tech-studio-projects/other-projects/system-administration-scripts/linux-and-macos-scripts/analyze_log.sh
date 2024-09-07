#!/bin/bash

################################################################################
# Script Name:       Ubuntu Automation Script
# Description:       This script analyzes a specified log file (default is /var/log/syslog)
#                    for occurrences of a specified error keyword (default is "error"). 
#                    It counts the total number of errors and extracts the five most 
#                    recent error messages for further analysis.
# Author:            Christoph Hartleb
# Date:              July 21, 2024
# Version:           1.1
# 
# Usage:             ./analyze_log.sh [-f log_file] [-k error_keyword]
# 
# Notes:             
#   - Make sure to customize the script according to your specific requirements.
#   - Some tasks may require root privileges. Ensure proper permissions.
#   - Test the script thoroughly in a safe environment before production use.
# https://google.github.io/styleguide/shellguide.html
################################################################################

# Default configuration variables
LOG_FILE="/var/log/syslog"  # Default log file path
ERROR_KEYWORD="error"       # Default keyword to search for in log files

# Function to display help
display_help() {
    echo "Usage: $0 [-f log_file] [-k error_keyword]"
    echo
    echo "   -f, --file       Specify the log file to analyze (default: /var/log/syslog)"
    echo "   -k, --keyword    Specify the error keyword to search for (default: 'error')"
    echo "   -h, --help       Display this help message"
    echo
}

# Parse command line arguments
while [[ "$#" -gt 0 ]]; do
    case $1 in
        -f|--file) LOG_FILE="$2"; shift ;;
        -k|--keyword) ERROR_KEYWORD="$2"; shift ;;
        -h|--help) display_help; exit 0 ;;
        *) echo "Unknown parameter passed: $1"; display_help; exit 1 ;;
    esac
    shift
done

# Function to analyze log files for errors
analyze_logs() {
    echo "$(date '+%Y-%m-%d %H:%M:%S') - Analyzing log file: $LOG_FILE"
    
    # Check if the log file exists and is readable
    if [[ ! -f "$LOG_FILE" || ! -r "$LOG_FILE" ]]; then
        echo "$(date '+%Y-%m-%d %H:%M:%S') - Error: Log file $LOG_FILE does not exist or is not readable."
        exit 1
    fi

    # Count occurrences of errors
    error_count=$(grep -ci "$ERROR_KEYWORD" "$LOG_FILE")
    
    # Display analysis results
    echo "$(date '+%Y-%m-%d %H:%M:%S') - Total number of errors: $error_count"
    
    # Extract and display recent error messages
    recent_errors=$(grep -i "$ERROR_KEYWORD" "$LOG_FILE" | tail -n 5)
    if [[ -n "$recent_errors" ]]; then
        echo "$(date '+%Y-%m-%d %H:%M:%S') - Recent error messages:"
        echo "$recent_errors"
    else
        echo "$(date '+%Y-%m-%d %H:%M:%S') - No recent error messages found."
    fi
}

# Main function
main() {
    echo "$(date '+%Y-%m-%d %H:%M:%S') - Log Analysis Script"
    echo "-------------------"
    
    # Check for root privileges if necessary
    if [[ $EUID -ne 0 ]]; then
       echo "$(date '+%Y-%m-%d %H:%M:%S') - Warning: It is recommended to run this script with root privileges."
    fi
    
    # Analyze log files
    analyze_logs
}

# Call the main function
main
