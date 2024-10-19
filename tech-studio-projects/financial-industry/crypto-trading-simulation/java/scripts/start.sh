#!/bin/bash

# Start the cryptocurrency market-making platform
set -e

echo "Starting the cryptocurrency market-making platform..."

# Run the main executable (assumes it's built and available in ./build)
if [[ -f "./build/market_maker" ]]; then
    ./build/market_maker
else
    echo "Error: Market Maker executable not found. Please build the project first."
    exit 1
fi
