#!/bin/bash

################################################################################
# Script Name:       Deployment Automation Script
# Description:       Automates the deployment process by pulling the latest code
#                    from a Git repository, building the application, and restarting
#                    the application service.
# Author:            Christoph Hartleb
# Date:              July 21, 2024
# Version:           1.1
# 
# Usage:             ./deploy.sh [--repo GIT_REPO] [--dir APP_DIR] [--service SERVICE_NAME]
# 
# Options:
#   --repo           Git repository URL (default: https://github.com/your/repository.git).
#   --dir            Directory of the application (default: /path/to/your/application).
#   --service        Service name to restart (default: your-service-name).
#   -h, --help       Display this help message.
# 
# Notes:
#   - Ensure git and systemctl are installed and properly configured.
#   - Test the script thoroughly in a safe environment before production use.
# https://google.github.io/styleguide/shellguide.html
################################################################################

# Default configuration variables
GIT_REPO="https://github.com/your/repository.git"
APP_DIR="/path/to/your/application"
SERVICE_NAME="your-service-name"

# Function to display help
display_help() {
    echo "Usage: $0 [--repo GIT_REPO] [--dir APP_DIR] [--service SERVICE_NAME]"
    echo
    echo "Automates the deployment process by pulling the latest code from a Git repository,"
    echo "building the application, and restarting the application service."
    echo
    echo "Options:"
    echo "   --repo           Git repository URL (default: $GIT_REPO)."
    echo "   --dir            Directory of the application (default: $APP_DIR)."
    echo "   --service        Service name to restart (default: $SERVICE_NAME)."
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
        --repo) GIT_REPO="$2"; shift ;;
        --dir) APP_DIR="$2"; shift ;;
        --service) SERVICE_NAME="$2"; shift ;;
        -h|--help) display_help; exit 0 ;;
        *) echo "Unknown parameter passed: $1"; display_help; exit 1 ;;
    esac
    shift
done

# Check if required commands are available
for cmd in git systemctl; do
    if ! command_exists "$cmd"; then
        echo "Error: $cmd is not installed."
        exit 1
    fi
done

# Function to deploy the application
deploy() {
    echo "Deploying the application..."
    
    # Navigate to the application directory
    if [ ! -d "$APP_DIR" ]; then
        echo "Error: Application directory '$APP_DIR' does not exist."
        exit 1
    fi
    
    cd "$APP_DIR" || exit
    
    # Pull the latest code from the Git repository
    echo "Pulling latest code from $GIT_REPO..."
    git pull origin master
    if [ $? -ne 0 ]; then
        echo "Error: Failed to pull from Git repository."
        exit 1
    fi
    
    # Build the application (if needed)
    # Uncomment and adjust this if a build step is required
    # echo "Building the application..."
    # npm install && npm run build
    # if [ $? -ne 0 ]; then
    #     echo "Error: Build failed."
    #     exit 1
    # fi
    
    # Restart the application service
    echo "Restarting service $SERVICE_NAME..."
    sudo systemctl restart "$SERVICE_NAME"
    if [ $? -ne 0 ]; then
        echo "Error: Failed to restart the service."
        exit 1
    fi
    
    echo "Deployment completed successfully."
}

# Main function
main() {
    echo "Automated Deployment Script"
    echo "--------------------------"
    
    # Confirm deployment
    read -p "Are you sure you want to deploy the application to '$APP_DIR' and restart '$SERVICE_NAME'? (y/n): " choice
    case "$choice" in
        [yY]|[yY][eE][sS])
            deploy
            ;;
        *)
            echo "Deployment aborted."
            ;;
    esac
}

# Call the main function
main
