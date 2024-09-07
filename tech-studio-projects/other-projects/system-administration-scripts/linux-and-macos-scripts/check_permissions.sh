#!/bin/bash

################################################################################
# Script Name:       Ubuntu Permissions Check Script
# Description:       A Bash script to check and display permissions for a specified file or folder.
# Author:            Christoph Hartleb
# Date:              July 21, 2024
# Version:           1.1
# 
# Usage:             ./check_permissions.sh <file_or_folder>
# 
# Notes:             
#   - Customize the script according to your specific requirements.
#   - Some tasks may require root privileges. Ensure proper permissions.
#   - Test the script thoroughly in a safe environment before production use.
# https://google.github.io/styleguide/shellguide.html
################################################################################

# Function to display help
display_help() {
    echo "Usage: $0 <file_or_folder>"
    echo
    echo "   <file_or_folder>   Path to the file or folder whose permissions you want to check"
    echo "   -h, --help        Display this help message"
    echo
}

# Check if help is requested
if [[ "$1" == "-h" || "$1" == "--help" ]]; then
    display_help
    exit 0
fi

# Check if file/folder path is provided as an argument
if [ $# -ne 1 ]; then
    echo "Error: Invalid number of arguments."
    display_help
    exit 1
fi

# Store the file/folder path provided as an argument
FILE_OR_FOLDER="$1"

# Check if the file/folder exists
if [ ! -e "$FILE_OR_FOLDER" ]; then
    echo "Error: File or folder '$FILE_OR_FOLDER' does not exist."
    exit 1
fi

# Display permissions for the file/folder
echo "$(date '+%Y-%m-%d %H:%M:%S') - Checking permissions for: $FILE_OR_FOLDER"
echo "--------------------------------"
ls -ld "$FILE_OR_FOLDER"
echo ""
