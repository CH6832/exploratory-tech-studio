#!/bin/bash

# Create build directory if it doesn't exist
mkdir -p build

# Navigate to the build directory
cd build

# Run CMake to generate build files
cmake ..

# Build the executable
make

# Optionally, move the executable to a different directory
# mv execution_simulation /path/to/destination

# Display completion message
echo "Build completed successfully"
