#!/bin/bash
# Deploy the cryptocurrency market-making platform using Docker Compose

echo "Deploying the cryptocurrency market-making platform..."

# Build and deploy containers
docker-compose up -d --build

# Check if the last command was successful
if [ $? -ne 0 ]; then
    echo "Deployment failed!"
    exit 1
fi

echo "Deployment completed successfully!"
