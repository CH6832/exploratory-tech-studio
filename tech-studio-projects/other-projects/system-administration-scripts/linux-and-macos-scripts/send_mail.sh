#!/bin/bash

################################################################################
# Script Name:       Ubuntu Automation Script
# Description:       A script for automatically sending an email with options for
#                    recipient, subject, body, and attachments.
# Author:            Christoph Hartleb
# Date:              July 21, 2024
# Version:           1.1
# 
# Usage:             ./send_mail.sh
# 
# Notes:             
#   - Ensure that `mail` or a compatible email sending utility is installed and configured.
#   - Customize the script according to your specific requirements.
#   - Some tasks may require root privileges. Ensure proper permissions.
#   - Test the script thoroughly in a safe environment before production use.
# https://google.github.io/styleguide/shellguide.html
################################################################################

# Function to display usage information
usage() {
    echo "Usage: $0 -r recipient -s subject -b body [-a attachment]"
    exit 1
}

# Parse command line arguments
while getopts "r:s:b:a:" opt; do
    case "$opt" in
        r) RECIPIENT="$OPTARG" ;;
        s) SUBJECT="$OPTARG" ;;
        b) BODY="$OPTARG" ;;
        a) ATTACHMENT="$OPTARG" ;;
        *) usage ;;
    esac
done

# Check if required arguments are provided
if [ -z "$RECIPIENT" ] || [ -z "$SUBJECT" ] || [ -z "$BODY" ]; then
    usage
fi

# Send the email
if [ -n "$ATTACHMENT" ]; then
    # Send email with attachment
    echo "$BODY" | mail -s "$SUBJECT" -a "$ATTACHMENT" "$RECIPIENT"
else
    # Send email without attachment
    echo "$BODY" | mail -s "$SUBJECT" "$RECIPIENT"
fi

# Check if the email was sent successfully
if [ $? -eq 0 ]; then
    echo "Email sent successfully to $RECIPIENT."
else
    echo "Error: Failed to send email."
fi
