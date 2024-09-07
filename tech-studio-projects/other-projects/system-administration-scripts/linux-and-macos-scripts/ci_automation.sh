#!/bin/bash

################################################################################
# Script Name:       CI Pipeline Script
# Description:       A basic CI pipeline for a Java application.
#                    Includes steps to compile code, run tests, analyze code with SonarQube, build a Docker image, and optionally push it to a registry.
# Author:            Christoph Hartleb
# Date:              July 21, 2024
# Version:           1.1
# 
# Usage:             ./ci_pipeline.sh [--push] [--image-name IMAGE_NAME]
# 
# Options:
#   --push            Push Docker image to a registry (optional).
#   --image-name      Specify the Docker image name (default: your-image-name).
#   -h, --help        Display this help message.
# 
# Notes:             
#   - Ensure Maven, Docker, and SonarQube Scanner are installed.
#   - Customize the script according to your specific requirements.
#   - Some tasks may require root privileges. Ensure proper permissions.
#   - Test the script thoroughly in a safe environment before production use.
# https://google.github.io/styleguide/shellguide.html
################################################################################

# Default configuration variables
PUSH_IMAGE=false
IMAGE_NAME="your-image-name"

# Function to display help
display_help() {
    echo "Usage: $0 [--push] [--image-name IMAGE_NAME]"
    echo
    echo "   --push            Push Docker image to a registry (optional)."
    echo "   --image-name      Specify the Docker image name (default: $IMAGE_NAME)."
    echo "   -h, --help        Display this help message."
    echo
}

# Parse command line arguments
while [[ "$#" -gt 0 ]]; do
    case $1 in
        --push) PUSH_IMAGE=true ;;
        --image-name) IMAGE_NAME="$2"; shift ;;
        -h|--help) display_help; exit 0 ;;
        *) echo "Unknown parameter passed: $1"; display_help; exit 1 ;;
    esac
    shift
done

# Function to check if a command exists
command_exists() {
    command -v "$1" >/dev/null 2>&1
}

# Check for required tools
for tool in mvn docker sonar-scanner; do
    if ! command_exists "$tool"; then
        echo "Error: $tool is not installed."
        exit 1
    fi
done

# Function to compile the Java code
compile_code() {
    echo "$(date '+%Y-%m-%d %H:%M:%S') - Compiling the Java code..."
    mvn compile
    if [ $? -ne 0 ]; then
        echo "Error: Compilation failed."
        exit 1
    fi
}

# Function to run tests
run_tests() {
    echo "$(date '+%Y-%m-%d %H:%M:%S') - Running tests..."
    mvn test
    if [ $? -ne 0 ]; then
        echo "Error: Tests failed."
        exit 1
    fi
}

# Function to perform code analysis with SonarQube
run_sonarqube_analysis() {
    echo "$(date '+%Y-%m-%d %H:%M:%S') - Performing code analysis with SonarQube..."
    sonar-scanner
    if [ $? -ne 0 ]; then
        echo "Error: SonarQube analysis failed."
        exit 1
    fi
}

# Function to build Docker image for Kubernetes deployment
build_docker_image() {
    echo "$(date '+%Y-%m-%d %H:%M:%S') - Building Docker image for Kubernetes deployment..."
    docker build -t "$IMAGE_NAME:latest" .
    if [ $? -ne 0 ]; then
        echo "Error: Docker image build failed."
        exit 1
    fi
}

# Function to push Docker image to a registry (optional)
push_docker_image() {
    echo "$(date '+%Y-%m-%d %H:%M:%S') - Pushing Docker image to registry..."
    docker push "$IMAGE_NAME:latest"
    if [ $? -ne 0 ]; then
        echo "Error: Docker image push failed."
        exit 1
    fi
}

# Main function
main() {
    echo "Continuous Integration Pipeline"
    echo "------------------------------"
    
    # Call each CI pipeline step
    compile_code
    run_tests
    run_sonarqube_analysis
    build_docker_image
    if [ "$PUSH_IMAGE" = true ]; then
        push_docker_image
    fi
}

# Call the main function
main
