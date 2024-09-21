<#
.SYNOPSIS
    LogAnalysis.ps1 analyzes log files for errors.

.DESCRIPTION
    LogAnalysis.ps1 is a PowerShell script that analyzes log files for errors. It includes a function, Analyze-Logs, which takes two parameters: $logFilePath (the path of the log file to be analyzed) and $errorKeyword (the keyword indicating errors in the log file). The script uses the Select-String cmdlet to search for occurrences of the errorKeyword in the log file. It then counts the total number of errors and extracts recent error messages, displaying them to the user. Any errors encountered during the analysis process are caught and displayed.

.PARAMETER logFilePath
    Specifies the path of the log file to be analyzed.

.PARAMETER errorKeyword
    Specifies the keyword indicating errors in the log file.

.PARAMETER recentCount
    Specifies the number of recent error messages to display. Defaults to 5.

.EXAMPLE
    LogAnalysis.ps1 -logFilePath "C:\Logs\Application.log" -errorKeyword "ERROR"
    This command analyzes the log file "C:\Logs\Application.log" for errors indicated by the keyword "ERROR".

.NOTES
    Author: Christoph Hartleb
    Date: 2024
    Version: 1.1
#>

param (
    [string]$logFilePath,
    [string]$errorKeyword,
    [int]$recentCount = 5
)

# Function to analyze log files for errors
function Analyze-Logs {
    param (
        [string]$logFilePath,
        [string]$errorKeyword,
        [int]$recentCount
    )

    Write-Host "Analyzing log file: $logFilePath"
    
    if (-not (Test-Path $logFilePath)) {
        Write-Host "Error: The log file '$logFilePath' does not exist."
        return
    }
    
    try {
        # Count occurrences of errors
        $errorCount = (Select-String -Path $logFilePath -Pattern $errorKeyword).Count
        
        Write-Host "Total number of errors: $errorCount"
        
        # Extract and display recent error messages
        $recentErrors = Select-String -Path $logFilePath -Pattern $errorKeyword | 
                         Select-Object -Last $recentCount |
                         ForEach-Object { $_.Line }
        
        if ($recentErrors.Count -gt 0) {
            Write-Host "Recent error messages:"
            $recentErrors | ForEach-Object { Write-Host $_ }
        }
        else {
            Write-Host "No recent error messages found."
        }
    }
    catch {
        Write-Host "Error: $_"
    }
}

# Main function
function Main {
    Write-Host "Log Analysis Script"
    Write-Host "--------------------"
    
    # Call the Analyze-Logs function
    Analyze-Logs -logFilePath $logFilePath -errorKeyword $errorKeyword -recentCount $recentCount
}

# Call the Main function
Main
