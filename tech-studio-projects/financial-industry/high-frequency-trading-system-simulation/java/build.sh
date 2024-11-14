#!/bin/bash
echo "Building HFT System..."
mvn clean install
docker-compose build
