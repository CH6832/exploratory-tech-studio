#!/bin/bash

################################################################################
# Script Name:       Empty Trash Script
# Description:       This script empties the trash by invoking the `trash-empty` command.
#                    It checks if the `trash-empty` command is available, logs the
#                    operation, and provides user feedback.
# Author:            Christoph Hartleb
# Date:              July 21, 2024
# Version:           1.1
# 
# Usage:             ./empty_trash.sh
# 
# Notes:             
#   - Ensure that the `trash-cli` package (which provides `trash-empty`) is installed.
#   - Some tasks may require root privileges. Ensure proper permissions.
#   - Test the script thoroughly in a safe environment before production use.
# https://google.github.io/styleguide/shellguide.html
################################################################################

# Log file location
LOG_FILE="/var/log/empty_trash.log"

# Function to log messages
log_message() {
    echo "$(date '+%Y-%m-%d %H:%M:%S') - $1" >> "$LOG_FILE"
}

# Check if `trash-empty` command exists
if ! command -v trash-empty &> /dev/null; then
    log_message "Error: trash-empty command not found. Please install the trash-cli package."
    echo "Error: trash-empty command not found. Please install the trash-cli package."
    exit 1
fi

# Empty the trash
echo "Emptying the trash..."
log_message "Emptying the trash..."
if trash-empty; then
    log_message "Trash emptied successfully."
    echo "Trash emptied successfully."
else
    log_message "Error: Failed to empty the trash."
    echo "Error: Failed to empty the trash."
    exit 1
fi
