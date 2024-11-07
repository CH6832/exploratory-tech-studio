#!/bin/bash

# **************************************************
# Bash Script to Execute Multiple Python Scripts
# **************************************************

# This script will run multiple Python scripts sequentially.
# It provides error handling for each Python script execution,
# and if any script fails, it stops and gives an error message.

# **************************************************
# Optionally, Activate a Virtual Environment (if needed)
# Uncomment the following line if you are using a virtual environment:
# source /path/to/your/venv/bin/activate
# This will activate the virtual environment before running the scripts.
# **************************************************

# **************************************************
# Run the First Python Script: ml_model_validation.py
# **************************************************

echo "Running ml_model_validation.py..."
python ml_model_validation.py

# Check if the first Python script executed successfully
if [ $? -ne 0 ]; then
    echo "Error: ml_model_validation.py did not execute successfully!"
    echo "Exiting script due to error."
    exit 1
fi

# **************************************************
# Run the Second Python Script: ml_model_validation_v2.py
# **************************************************

echo "Running ml_model_validation_v2.py..."
python ml_model_validation_v2.py

# Check if the second Python script executed successfully
if [ $? -ne 0 ]; then
    echo "Error: ml_model_validation_v2.py did not execute successfully!"
    echo "Exiting script due to error."
    exit 1
fi

# **************************************************
# Run the Third Python Script: ml_model_validation_v3.py
# **************************************************

echo "Running ml_model_validation_v3.py..."
python ml_model_validation_v3.py

# Check if the third Python script executed successfully
if [ $? -ne 0 ]; then
    echo "Error: ml_model_validation_v3.py did not execute successfully!"
    echo "Exiting script due to error."
    exit 1
fi

# **************************************************
# Run the Fourth Python Script: ml_model_validation_v4.py
# **************************************************

echo "Running ml_model_validation_v4.py..."
python ml_model_validation_v4.py

# Check if the fourth Python script executed successfully
if [ $? -ne 0 ]; then
    echo "Error: ml_model_validation_v4.py did not execute successfully!"
    echo "Exiting script due to error."
    exit 1
fi

# **************************************************
# Add more Python scripts here if needed
# For each new Python script, simply follow the same format:
# 1. echo "Running [script_name].py"
# 2. python [script_name].py
# 3. Check for success using if [ $? -ne 0 ]
# **************************************************

# **************************************************
# Completion Message
# **************************************************

echo "All scripts have been executed successfully."
