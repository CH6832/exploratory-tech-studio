<#
.SYNOPSIS
    LogRotation.ps1 rotates log files periodically.

.DESCRIPTION
    LogRotation.ps1 is a PowerShell script that rotates log files periodically. It includes a function, Rotate-Logs, which performs the log rotation operation. The script allows users to specify the source directory, file extension, and retention period for the rotation process. It uses these parameters to rotate the log files by renaming and compressing old logs. Upon completion, it displays a message indicating the success of the log rotation process. Any errors encountered during the rotation process are caught and displayed.

.PARAMETER SourceDirectory
    Specifies the directory where log files are located.

.PARAMETER FileExtension
    Specifies the file extension of the log files to be rotated.

.PARAMETER RetentionDays
    Specifies the number of days to retain old log files before deletion.

.EXAMPLE
    .\LogRotation.ps1 -SourceDirectory "C:\Logs" -FileExtension ".log" -RetentionDays 7
    This command executes the script to rotate log files with the ".log" extension in "C:\Logs" and retains files for 7 days.

.NOTES
    Author: Christoph Hartleb
    Date: 2024
    Version: 1.1
#>

param (
    [string]$SourceDirectory = "C:\Logs",
    [string]$FileExtension = ".log",
    [int]$RetentionDays = 7
)

# Function to rotate log files periodically
function Rotate-Logs {
    param (
        [string]$SourceDirectory,
        [string]$FileExtension,
        [int]$RetentionDays
    )
    
    Write-Host "Rotating log files in directory: $SourceDirectory"
    
    if (-not (Test-Path $SourceDirectory)) {
        Write-Host "Error: The directory '$SourceDirectory' does not exist."
        return
    }

    try {
        # Get current date and time
        $currentDate = Get-Date
        
        # Get log files to rotate
        $logFiles = Get-ChildItem -Path $SourceDirectory -Filter "*$FileExtension" -File
        
        foreach ($logFile in $logFiles) {
            $fileName = $logFile.Name
            $filePath = $logFile.FullName
            $fileDate = $logFile.LastWriteTime
            $fileAge = ($currentDate - $fileDate).Days
            
            # Check if the file is older than the retention period
            if ($fileAge -ge $RetentionDays) {
                # Create a new filename with a timestamp
                $timestamp = $fileDate.ToString("yyyyMMdd-HHmmss")
                $newFileName = "$($fileName)-$timestamp$FileExtension"
                $newFilePath = Join-Path -Path $SourceDirectory -ChildPath $newFileName
                
                # Rename (rotate) the log file
                Rename-Item -Path $filePath -NewName $newFileName
                
                Write-Host "Rotated log file: $fileName to $newFileName"
                
                # Optionally, compress the rotated file (uncomment if needed)
                # Compress-Archive -Path $newFilePath -DestinationPath "$newFilePath.zip"
                # Remove-Item -Path $newFilePath -Force
            }
        }
        
        Write-Host "Log rotation completed successfully."
    }
    catch {
        Write-Host "Error: $_"
    }
}

# Main function
function Main {
    Write-Host "Log Rotation Script"
    Write-Host "-------------------"
    
    # Call the Rotate-Logs function
    Rotate-Logs -SourceDirectory $SourceDirectory -FileExtension $FileExtension -RetentionDays $RetentionDays
}

# Call the Main function
Main
