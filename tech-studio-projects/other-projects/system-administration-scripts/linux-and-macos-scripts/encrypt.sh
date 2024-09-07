#!/bin/bash

################################################################################
# Script Name:       Encrypt File/Directory Script
# Description:       This script encrypts a file or directory using GPG based on the
#                    recipient's public key or key ID. It prompts for the file or directory 
#                    path and recipient's key, performs encryption, and provides feedback.
# Author:            Christoph Hartleb
# Date:              July 21, 2024
# Version:           1.1
# 
# Usage:             ./encrypt.sh
# 
# Notes:             
#   - Ensure that GPG is installed and properly configured on your system.
#   - Test the script thoroughly in a safe environment before production use.
# https://google.github.io/styleguide/shellguide.html
################################################################################

# Log file location
LOG_FILE="/var/log/encrypt.log"

# Function to log messages
log_message() {
    echo "$(date '+%Y-%m-%d %H:%M:%S') - $1" >> "$LOG_FILE"
}

# Function to encrypt a file or directory
encrypt() {
    # Prompt for file or directory path
    read -e -p "Enter the path of the file or directory to encrypt: " target
    
    # Check if the target file or directory exists
    if [ -e "$target" ]; then
        # Prompt for recipient's public key
        read -p "Enter the recipient's public key (email or key ID): " recipient
        
        # Perform encryption
        echo "Encrypting $target for $recipient..."
        if gpg --encrypt --recipient "$recipient" "$target"; then
            log_message "Encryption successful for $target with recipient $recipient."
            echo "Encryption successful."
        else
            log_message "Error: Encryption failed for $target with recipient $recipient."
            echo "Error: Encryption failed."
            exit 1
        fi
    else
        log_message "Error: File or directory '$target' not found."
        echo "Error: File or directory '$target' not found."
        exit 1
    fi
}

# Main function
main() {
    echo "File Encryption Script"
    echo "----------------------"
    
    # Call the encrypt function
    encrypt
}

# Call the main function
main
