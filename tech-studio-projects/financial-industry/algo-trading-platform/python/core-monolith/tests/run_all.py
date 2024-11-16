import subprocess
import sys

def run_tests():
    """
    Function to run all test scripts in the 'tests/' directory using pytest.
    """
    try:
        # Run pytest on the tests directory and capture the result
        result = subprocess.run(['pytest', 'tests/'], stdout=subprocess.PIPE, stderr=subprocess.PIPE, text=True)
        
        # Print the output of the pytest command
        print(result.stdout)
        
        # If there is an error in the pytest run, print the error
        if result.stderr:
            print("Error occurred while running tests:")
            print(result.stderr)
        
        # Return the exit code from pytest (0 means success, non-zero means failure)
        return result.returncode
    
    except Exception as e:
        print(f"An error occurred while running tests: {e}")
        return 1  # Return non-zero exit code to indicate failure

if __name__ == "__main__":
    exit_code = run_tests()
    sys.exit(exit_code)
