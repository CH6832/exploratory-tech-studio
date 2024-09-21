#!/bin/bash

################################################################################
# Script Name:       File Synchronization Script
# Description:       Synchronizes files or directories between a local directory
#                    (SOURCE_DIR) and a remote directory (DESTINATION_DIR) using rsync.
#                    Configuration variables can be set via command-line arguments.
# Author:            Christoph Hartleb
# Date:              April 13, 2024
# Version:           1.1
# 
# Usage:             ./data_sync.sh [--source SOURCE_DIR] [--destination DESTINATION_DIR] [--options RSYNC_OPTIONS]
# 
# Options:
#   --source         Specify the source directory (default: /path/to/source).
#   --destination    Specify the destination directory (default: user@remote_host:/path/to/destination).
#   --options        Specify rsync options (default: "-avz --delete --exclude='*.log'").
#   -h, --help       Display this help message.
# 
# Notes: 
#   - Ensure rsync is installed on both local and remote machines.
#   - Test the script thoroughly in a safe environment before production use.
# https://google.github.io/styleguide/shellguide.html
################################################################################

# Default configuration variables
SOURCE_DIR="/path/to/source"
DESTINATION_DIR="user@remote_host:/path/to/destination"
RSYNC_OPTIONS="-avz --delete --exclude='*.log'"

# Function to display help
display_help() {
    echo "Usage: $0 [--source SOURCE_DIR] [--destination DESTINATION_DIR] [--options RSYNC_OPTIONS]"
    echo
    echo "Synchronizes files between local and remote directories using rsync."
    echo
    echo "Options:"
    echo "   --source         Specify the source directory (default: $SOURCE_DIR)."
    echo "   --destination    Specify the destination directory (default: $DESTINATION_DIR)."
    echo "   --options        Specify rsync options (default: $RSYNC_OPTIONS)."
    echo "   -h, --help       Display this help message."
    echo
}

# Function to check if a command exists
command_exists() {
    command -v "$1" >/dev/null 2>&1
}

# Parse command-line arguments
while [[ "$#" -gt 0 ]]; do
    case $1 in
        --source) SOURCE_DIR="$2"; shift ;;
        --destination) DESTINATION_DIR="$2"; shift ;;
        --options) RSYNC_OPTIONS="$2"; shift ;;
        -h|--help) display_help; exit 0 ;;
        *) echo "Unknown parameter passed: $1"; display_help; exit 1 ;;
    esac
    shift
done

# Check if rsync is installed
if ! command_exists rsync; then
    echo "Error: rsync is not installed."
    exit 1
fi

# Function to synchronize files or directories
sync_files() {
    echo "Synchronizing files from '$SOURCE_DIR' to '$DESTINATION_DIR'..."
    
    # Run rsync command to synchronize files
    rsync $RSYNC_OPTIONS "$SOURCE_DIR" "$DESTINATION_DIR"
    
    # Check rsync exit status
    if [ $? -eq 0 ]; then
        echo "Synchronization completed successfully."
    else
        echo "Error: Synchronization failed."
        exit 1
    fi
}

# Main function
main() {
    echo "File Synchronization Script"
    echo "--------------------------"
    
    # Confirm synchronization
    read -p "Are you sure you want to synchronize files from '$SOURCE_DIR' to '$DESTINATION_DIR'? (y/n): " choice
    case "$choice" in
        [yY]|[yY][eE][sS])
            sync_files
            ;;
        *)
            echo "Synchronization aborted."
            ;;
    esac
}

# Call the main function
main
