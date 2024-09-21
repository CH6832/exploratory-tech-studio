#!/bin/bash
# Bash script to compile preprocessor.cpp into preprocessor.dylib

# Check if the compiler is installed
if ! command -v clang++ &> /dev/null
then
    echo "clang++ not found. Please install Xcode Command Line Tools."
    exit 1
fi

# Compile preprocessor.cpp into a dynamic library
clang++ -dynamiclib -o preprocessor.dylib preprocessor.cpp

# Check if compilation was successful
if [ $? -eq 0 ]; then
    echo "Compilation successful. Output: preprocessor.dylib"
else
    echo "Compilation failed."
fi
