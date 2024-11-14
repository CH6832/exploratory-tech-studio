@echo off
echo Stopping Network Simulation...
REM Change directory to where the compiled executable is located
cd ./bin
REM Use taskkill to stop the simulation if it's running in a separate window
taskkill /IM netlab_simulation.exe /F
echo Network Simulation stopped successfully!
pause
