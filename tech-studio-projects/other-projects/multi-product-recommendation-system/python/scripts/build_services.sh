#!/bin/bash
# ─────────────────────────────────────────────────────────────────────
# build_services.sh
# Script to build Docker images for all services using Docker Compose.
# This script is useful for rebuilding the images after changes to the code.
# ─────────────────────────────────────────────────────────────────────

# -*- coding: utf-8 -*-

# Set the environment to be used (default is 'development')
ENVIRONMENT="${1:-development}"

# Print the environment
echo "Building Docker images for the $ENVIRONMENT environment..."

# Use Docker Compose to build images for all services
docker-compose -f docker-compose.$ENVIRONMENT.yml build

# Check if the images were built successfully
if [ $? -eq 0 ]; then
    echo "Docker images built successfully!"
else
    echo "Failed to build Docker images."
    exit 1
fi
