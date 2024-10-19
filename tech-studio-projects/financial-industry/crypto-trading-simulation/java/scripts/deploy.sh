#!/bin/bash

# Deploy the cryptocurrency market-making platform using Docker Compose
set -e

echo "Deploying the cryptocurrency market-making platform..."

# Build and deploy containers
docker-compose up -d --build

echo "Deployment completed successfully!"
