@echo off
REM Start the cryptocurrency market-making platform

echo Starting the cryptocurrency market-making platform...

REM Check if the market_maker executable exists
IF EXIST ".\build\market_maker.exe" (
    .\build\market_maker.exe
) ELSE (
    echo Error: Market Maker executable not found. Please build the project first.
    exit /b 1
)

echo Market-making platform started.
