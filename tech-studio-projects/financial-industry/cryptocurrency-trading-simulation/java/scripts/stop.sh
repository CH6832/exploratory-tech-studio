#!/bin/bash

# Stop the cryptocurrency market-making platform
set -e

echo "Stopping the cryptocurrency market-making platform..."

# Stop the Docker containers
docker-compose down

echo "Platform stopped successfully!"
