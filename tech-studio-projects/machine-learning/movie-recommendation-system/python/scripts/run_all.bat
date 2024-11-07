@echo off
REM **************************************************
REM Batch File to Execute Multiple Python Scripts
REM **************************************************

REM This script runs multiple Python scripts one by one.
REM It provides error handling for each Python script execution
REM and ensures that if any script fails, it will stop and give an error message.

REM **************************************************
REM Optionally, Activate a Virtual Environment (if needed)
REM If you are using a virtual environment, uncomment and modify the following line:
REM call C:\path\to\your\venv\Scripts\activate
REM This will activate the virtual environment before running the scripts.
REM **************************************************

REM **************************************************
REM Run the First Python Script: ml_model_validation.py
REM **************************************************

echo Running ml_model_validation.py...
python ml_model_validation.py

REM Check if the first Python script executed successfully
IF %ERRORLEVEL% NEQ 0 (
    echo Error: ml_model_validation.py did not execute successfully!
    echo Exiting script due to error.
    pause
    exit /b %ERRORLEVEL%
)

REM **************************************************
REM Run the Second Python Script: ml_model_validation_v2.py
REM **************************************************

echo Running ml_model_validation_v2.py...
python ml_model_validation_v2.py

REM Check if the second Python script executed successfully
IF %ERRORLEVEL% NEQ 0 (
    echo Error: ml_model_validation_v2.py did not execute successfully!
    echo Exiting script due to error.
    pause
    exit /b %ERRORLEVEL%
)

REM **************************************************
REM Run the Third Python Script: ml_model_validation_v3.py
REM **************************************************

echo Running ml_model_validation_v3.py...
python ml_model_validation_v3.py

REM Check if the third Python script executed successfully
IF %ERRORLEVEL% NEQ 0 (
    echo Error: ml_model_validation_v3.py did not execute successfully!
    echo Exiting script due to error.
    pause
    exit /b %ERRORLEVEL%
)

REM **************************************************
REM Run the Fourth Python Script: ml_model_validation_v4.py
REM **************************************************

echo Running ml_model_validation_v4.py...
python ml_model_validation_v4.py

REM Check if the fourth Python script executed successfully
IF %ERRORLEVEL% NEQ 0 (
    echo Error: ml_model_validation_v4.py did not execute successfully!
    echo Exiting script due to error.
    pause
    exit /b %ERRORLEVEL%
)

REM **************************************************
REM Add more Python scripts here if needed
REM For each new Python script, simply follow the same format:
REM 1. echo Running [script_name]...
REM 2. python [script_name].py
REM 3. Check for error level with IF %ERRORLEVEL% NEQ 0
REM **************************************************

REM **************************************************
REM Completion Message
REM **************************************************

echo All scripts have been executed successfully.
pause
