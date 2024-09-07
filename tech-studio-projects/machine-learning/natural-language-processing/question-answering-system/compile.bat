@echo off
REM Batch script to compile preprocessor.cpp into preprocessor.dll

REM Check if the compiler is installed
where cl >nul 2>nul
if errorlevel 1 (
    echo Microsoft Visual C++ compiler not found. Please install Visual Studio or the Build Tools.
    exit /b 1
)

REM Compile preprocessor.cpp into a DLL
cl /LD preprocessor.cpp /link /OUT:preprocessor.dll

REM Check if compilation was successful
if errorlevel 0 (
    echo Compilation successful. Output: preprocessor.dll
) else (
    echo Compilation failed.
)

pause
