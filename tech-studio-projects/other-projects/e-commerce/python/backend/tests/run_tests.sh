#!/bin/bash

echo "Running all tests..."

# Activate the virtual environment if necessary
# source venv/bin/activate

# Run tests using pytest
pytest tests/

# Check the exit status of the tests
if [ $? -eq 0 ]; then
    echo "All tests passed!"
else
    echo "Some tests failed."
    exit 1
fi
