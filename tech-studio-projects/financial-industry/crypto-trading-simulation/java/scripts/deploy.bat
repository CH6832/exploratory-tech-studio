@echo off
REM Deploy the cryptocurrency market-making platform using Docker Compose

echo Deploying the cryptocurrency market-making platform...

REM Build and deploy containers
docker-compose up -d --build

IF ERRORLEVEL 1 (
    echo Deployment failed!
    exit /b 1
)

echo Deployment completed successfully!
