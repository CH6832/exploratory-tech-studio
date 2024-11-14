#!/bin/bash

echo "Initializing Network Simulation..."

# Change directory to where the compiled executable is located
cd ./bin

# Start the simulation executable (assuming it is named 'netlab_simulation')
./netlab_simulation &

echo "Network Simulation started successfully!"
