@echo off
REM Stop the cryptocurrency market-making platform

echo Stopping the cryptocurrency market-making platform...

REM Stop the Docker containers
docker-compose down

IF ERRORLEVEL 1 (
    echo Failed to stop the platform!
    exit /b 1
)

echo Platform stopped successfully!
