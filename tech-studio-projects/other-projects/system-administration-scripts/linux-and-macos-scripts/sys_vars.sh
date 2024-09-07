#!/bin/bash

################################################################################
# Script Name:       Ubuntu Automation Script
# Description:       Write an overview of all system variables, including environment,
#                    shell, and special shell variables.
# Author:            Christoph Hartleb
# Date:              July 21, 2024
# Version:           1.1
# 
# Usage:             ./sys_vars.sh
# 
# Notes:             
#   - Ensure proper permissions and test the script in a safe environment.
#   - Customize as needed.
# https://google.github.io/styleguide/shellguide.html
################################################################################

# Output file
OUTPUT_FILE="system_variables_overview.txt"

# Create or overwrite the output file
{
    # Header
    echo "System Variables Overview"
    echo "========================="
    echo ""

    # Environment Variables
    echo "Environment Variables:"
    echo "---------------------"
    env
    echo ""

    # Shell Variables
    echo "Shell Variables:"
    echo "----------------"
    set
    echo ""

    # Special Shell Variables
    echo "Special Shell Variables:"
    echo "------------------------"
    echo "\$0 (Script Name): $0"
    echo "\$1 (First Argument): $1"
    echo "\$2 (Second Argument): $2"
    echo "\$# (Number of Arguments): $#"    
    echo "\$@ (All Arguments): $@"
    echo "\$? (Exit Status): $?"
    echo "\$$ (Process ID of the Shell): $$"
} > "$OUTPUT_FILE"

echo "Overview of system variables created in $OUTPUT_FILE"
