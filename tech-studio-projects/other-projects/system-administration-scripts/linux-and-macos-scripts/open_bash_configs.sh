#!/bin/bash

################################################################################
# Script Name:       Ubuntu Automation Script
# Description:       A template for automating various tasks in Ubuntu.
#                    This script searches for and opens configuration files
#                    modified today based on specified file extensions and directories.
# Author:            Christoph Hartleb
# Date:              July 21, 2024
# Version:           1.1
# 
# Usage:             ./open_bash_configs.sh
# 
# Notes:             
#   - Make sure to customize the script according to your specific requirements.
#   - Some tasks may require root privileges. Ensure proper permissions.
#   - Test the script thoroughly in a safe environment before production use.
# https://google.github.io/styleguide/shellguide.html
################################################################################

# Define the preferred text editor
TEXT_EDITOR="${TEXT_EDITOR:-nano}"  # Default to nano if no editor is specified

# Check if the specified text editor is available
if ! command -v "$TEXT_EDITOR" &> /dev/null; then
    echo "Error: Specified text editor '$TEXT_EDITOR' is not installed."
    exit 1
fi

# Get today's date in YYYY-MM-DD format
TODAY=$(date +%Y-%m-%d)

# Define relevant directories where configuration files might be located
CONFIG_DIRS=(
    "/etc/"
    "/usr/local/etc/"
    "/home/user/config/"
    # Add more directories as needed
)

# Define relevant file extensions for configuration files
FILE_EXTENSIONS=(
    "*.conf"
    "*.cfg"
    "*.ini"
    # Add more extensions as needed
)

# Flag to track if any files are opened
FILES_FOUND=0

# Find and open relevant configuration files for today
for dir in "${CONFIG_DIRS[@]}"; do
    for ext in "${FILE_EXTENSIONS[@]}"; do
        # Use find command to search for files with specified extensions in the given directory
        find "$dir" -type f -name "$ext" -exec grep -l "$TODAY" {} + | while read -r file; do
            # Check if file exists before attempting to open
            if [ -f "$file" ]; then
                echo "Opening $file"
                "$TEXT_EDITOR" "$file"
                FILES_FOUND=1
            else
                echo "Warning: $file does not exist."
            fi
        done
    done
done

if [ $FILES_FOUND -eq 0 ]; then
    echo "No configuration files found or modified today."
fi
