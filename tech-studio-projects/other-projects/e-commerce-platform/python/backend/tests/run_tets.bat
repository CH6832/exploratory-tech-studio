@echo off
echo Running all tests...

:: Activate the virtual environment if necessary
:: call venv\Scripts\activate

:: Run tests using pytest
pytest tests/

:: Check the exit status of the tests
if %errorlevel% neq 0 (
    echo Some tests failed.
    exit /b 1
) else (
    echo All tests passed!
)
