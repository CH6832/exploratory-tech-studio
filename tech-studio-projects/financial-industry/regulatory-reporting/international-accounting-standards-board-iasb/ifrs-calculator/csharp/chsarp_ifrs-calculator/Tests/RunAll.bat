@echo off
REM This batch file runs all tests in the solution.

echo Running all tests...

REM Navigate to the directory where your solution or project file is located
cd /d

REM Run the tests for the entire solution (use the path to your solution file)
dotnet test "..\\csharp_ifrs-calculator.sln"

REM OR if you want to run tests for a specific project, you can use this instead:
REM dotnet test "path\to\your\project\YourProject.csproj"

echo All tests completed.
pause
