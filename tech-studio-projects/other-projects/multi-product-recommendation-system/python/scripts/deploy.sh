#!/bin/bash
# ─────────────────────────────────────────────────────────────────────
# deploy.sh
# CI/CD deployment script to deploy the microservices to a production or staging environment.
# This script handles the deployment of services by pulling the latest Docker images,
# running migrations, and restarting services.
# ─────────────────────────────────────────────────────────────────────

# -*- coding: utf-8 -*-

# Set environment (defaults to production)
ENVIRONMENT="${1:-production}"

# Print which environment is being deployed
echo "Deploying services to the $ENVIRONMENT environment..."

# Pull the latest images from the registry (Docker Hub, private registry, etc.)
echo "Pulling latest Docker images..."
docker-compose -f docker-compose.$ENVIRONMENT.yml pull

# Run migrations if applicable (for example, Django or Flask)
# echo "Running migrations..."
# docker-compose -f docker-compose.$ENVIRONMENT.yml run --rm your_service_name python manage.py migrate

# Restart the services to apply new changes
echo "Restarting services..."
docker-compose -f docker-compose.$ENVIRONMENT.yml down
docker-compose -f docker-compose.$ENVIRONMENT.yml up -d

# Check the deployment status
if [ $? -eq 0 ]; then
    echo "Deployment completed successfully!"
else
    echo "Deployment failed."
    exit 1
fi
