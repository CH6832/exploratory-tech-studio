#!/bin/bash

echo "Stopping Network Simulation..."

# Change directory to where the compiled executable is located
cd ./bin

# Use pkill to stop the simulation if it's running in a separate process
pkill -f netlab_simulation

echo "Network Simulation stopped successfully!"
