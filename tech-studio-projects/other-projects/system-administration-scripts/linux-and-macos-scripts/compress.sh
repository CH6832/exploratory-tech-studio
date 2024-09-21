#!/bin/bash

################################################################################
# Script Name:       File Compression Script
# Description:       Prompts for a file or directory to compress and the destination path. Determines the compression tool based on the destination file extension (.gz or .zip).
# Author:            Christoph Hartleb
# Date:              July 21, 2024
# Version:           1.1
# 
# Usage:             ./compress.sh
# 
# Notes:             
#   - Customize the script according to your specific requirements.
#   - Ensure that required tools (gzip, zip) are installed.
#   - Test the script thoroughly in a safe environment before production use.
# https://google.github.io/styleguide/shellguide.html
################################################################################

# Function to display help
display_help() {
    echo "Usage: $0"
    echo
    echo "Prompts for a file or directory to compress and the destination path."
    echo "Determines the compression tool based on the destination file extension (.gz or .zip)."
    echo
    echo "Options:"
    echo "   -h, --help        Display this help message."
    echo
}

# Function to check if a command exists
command_exists() {
    command -v "$1" >/dev/null 2>&1
}

# Check for required tools
for tool in gzip zip; do
    if ! command_exists "$tool"; then
        echo "Error: $tool is not installed."
        exit 1
    fi
done

# Function to compress files or directories
compress_files() {
    echo "Compressing files or directories..."
    
    # Prompt for source path
    read -e -p "Enter the path of the file or directory to compress: " source
    
    # Check if the source file or directory exists
    if [ ! -e "$source" ]; then
        echo "Error: File or directory '$source' not found."
        exit 1
    fi
    
    # Prompt for destination path
    read -e -p "Enter the destination path for the compressed file: " destination
    
    # Check if the destination directory exists
    if [ ! -d "$(dirname "$destination")" ]; then
        echo "Error: Destination directory does not exist."
        exit 1
    fi
    
    # Determine compression tool based on file extension
    case "$destination" in
        *.gz)
            # Compress using gzip
            gzip -c "$source" > "$destination"
            ;;
        *.zip)
            # Compress using zip
            zip -r "$destination" "$source"
            ;;
        *)
            echo "Error: Unsupported file extension for destination file. Supported extensions are .gz and .zip."
            exit 1
            ;;
    esac
    
    # Check compression status
    if [ $? -eq 0 ]; then
        echo "Compression completed successfully."
    else
        echo "Error: Compression failed."
        exit 1
    fi
}

# Main function
main() {
    if [[ "$1" == "-h" || "$1" == "--help" ]]; then
        display_help
        exit 0
    fi
    
    echo "File Compression Script"
    echo "-----------------------"
    
    # Call the compress_files function
    compress_files
}

# Call the main function
main "$@"
