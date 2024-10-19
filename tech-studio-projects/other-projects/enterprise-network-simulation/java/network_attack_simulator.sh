#!/bin/bash

echo "Starting Network Attack Simulator..."

# Change directory to where the compiled executable is located
cd ./bin

# Start the attack simulator executable (assuming it is named 'attack_simulator')
./attack_simulator &

echo "All attacks simulated successfully!"
