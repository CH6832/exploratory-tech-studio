#!/bin/bash

################################################################################
# Script Name:       Ubuntu Backup Script
# Description:       This script creates a backup of specified directories.
# Author:            Christoph Hartleb
# Date:              July 21, 2024
# Version:           1.1
# 
# Usage:             ./backup.sh [-d backup_dir] [-l dir1 dir2 ...]
# 
# Notes:             
#   - Customize the script according to your specific requirements.
#   - Some tasks may require root privileges. Ensure proper permissions.
#   - Test the script thoroughly in a safe environment before production use.
# https://google.github.io/styleguide/shellguide.html
################################################################################

# Default configuration variables
BACKUP_DIR="/path/to/backup/directory"
DIRECTORIES=(
    "/etc"
    "/home"
    "/var/www"
    "/opt/myapp"
    "/var/log"
    "/usr/local/bin"
    "/usr/share"
    "/srv/data"
    "/var/backups"
    "/var/lib"
    "/root"
)

# Function to display help
display_help() {
    echo "Usage: $0 [-d backup_dir] [-l dir1 dir2 ...]"
    echo
    echo "   -d, --directory   Specify the directory to store backups (default: /path/to/backup/directory)"
    echo "   -l, --list        List of directories to include in the backup (space-separated)"
    echo "   -h, --help        Display this help message"
    echo
}

# Parse command line arguments
while [[ "$#" -gt 0 ]]; do
    case $1 in
        -d|--directory) BACKUP_DIR="$2"; shift ;;
        -l|--list) IFS=' ' read -r -a DIRECTORIES <<< "$2"; shift ;;
        -h|--help) display_help; exit 0 ;;
        *) echo "Unknown parameter passed: $1"; display_help; exit 1 ;;
    esac
    shift
done

# Function to log messages
log_message() {
    echo "$(date '+%Y-%m-%d %H:%M:%S') - $1"
}

# Check if the script is running with root privileges
if [[ $EUID -ne 0 ]]; then
   log_message "Warning: It is recommended to run this script with root privileges."
fi

# Check if backup directory exists, if not, create it
if [ ! -d "$BACKUP_DIR" ]; then
    log_message "Creating backup directory: $BACKUP_DIR"
    mkdir -p "$BACKUP_DIR"
    if [ $? -ne 0 ]; then
        log_message "Error: Unable to create backup directory: $BACKUP_DIR"
        exit 1
    fi
fi

# Calculate the required space
required_space=$(du -bs "${DIRECTORIES[@]}" | awk '{sum += $1} END {print sum}')
available_space=$(df -k "$BACKUP_DIR" | awk 'NR==2 {print $4 * 1024}')
if [ "$available_space" -lt "$required_space" ]; then
    log_message "Error: Insufficient disk space to create backup."
    exit 1
fi

# Create the backup
BACKUP_FILE="backup_$(date +%Y%m%d_%H%M%S).tar.gz"
log_message "Creating backup..."
tar -czf "$BACKUP_DIR/$BACKUP_FILE" "${DIRECTORIES[@]}"

# Check if the backup was successful
if [ $? -eq 0 ]; then
    log_message "Backup created successfully: $BACKUP_DIR/$BACKUP_FILE"
else
    log_message "Error: Backup creation failed."
fi
