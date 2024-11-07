#!/bin/bash
# ─────────────────────────────────────────────────────────────────────
# start_services.sh
# Script to start all microservices using Docker Compose.
# This will spin up all containers defined in the `docker-compose.yml` file.
# ─────────────────────────────────────────────────────────────────────

# -*- coding: utf-8 -*- 

# Set the environment to be used (default is 'development')
ENVIRONMENT="${1:-development}"

# Print the environment
echo "Starting services in the $ENVIRONMENT environment..."

# Use Docker Compose to bring up the services
docker-compose -f docker-compose.$ENVIRONMENT.yml up --build --detach

# Check if the services started successfully
if [ $? -eq 0 ]; then
    echo "Services started successfully!"
else
    echo "Failed to start services."
    exit 1
fi
