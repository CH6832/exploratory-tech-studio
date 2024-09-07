<#
.SYNOPSIS
    Empty the Recycle Bin.

.DESCRIPTION
    The script empties the Recycle Bin and logs the action. 
    It prompts the user for confirmation before proceeding.

.EXAMPLE
    .\EmptyRecycleBin.ps1

.NOTES
    Author: Christoph Hartleb
    Date: 2024
    Version: 1.1
#>

# Define log file path
$LogFile = "EmptyRecycleBinLog_" + (Get-Date -Format 'yyyyMMdd_HHmmss') + ".log"

# Function to log messages
function Log-Message {
    param (
        [string]$Message,
        [string]$Type = "INFO"
    )
    
    $Timestamp = Get-Date -Format 'yyyy-MM-dd HH:mm:ss'
    "$Timestamp [$Type] $Message" | Out-File -Append -FilePath $LogFile
}

# Function to empty the Recycle Bin
function Empty-RecycleBin {
    Write-Host "Emptying the Recycle Bin..."
    Log-Message "Attempting to empty the Recycle Bin."
    
    try {
        Clear-RecycleBin -Confirm:$false -Force
        Write-Host "Recycle Bin emptied successfully."
        Log-Message "Recycle Bin emptied successfully."
    }
    catch {
        Write-Host "Error emptying the Recycle Bin: $_"
        Log-Message "Error emptying the Recycle Bin: $_" -Type "ERROR"
    }
}

# Main function
function Main {
    # Prompt user for confirmation
    $confirmation = Read-Host "Are you sure you want to empty the Recycle Bin? (Y/N)"
    
    if ($confirmation -eq 'Y' -or $confirmation -eq 'y') {
        Empty-RecycleBin
    }
    else {
        Write-Host "Operation canceled."
        Log-Message "Operation canceled by user." -Type "INFO"
    }
}

# Call the Main function
Main
