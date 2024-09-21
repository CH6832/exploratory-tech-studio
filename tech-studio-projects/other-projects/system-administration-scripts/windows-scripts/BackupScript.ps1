<#
.SYNOPSIS
    BackupScript.ps1 creates backups for a specified source directory.

.DESCRIPTION
    BackupScript.ps1 automates the process of creating backups for a specified source directory by compressing
    its contents into a ZIP archive and storing it in a designated destination directory with a timestamped filename.

.EXAMPLE
    .\BackupScript.ps1 -SourceDirectory "C:\Path\To\Source" -DestinationDirectory "D:\Backup"

.PARAMETER SourceDirectory
    The path to the source directory that will be backed up.

.PARAMETER DestinationDirectory
    The path to the destination directory where backups will be stored.

.NOTES
    Author: Christoph Hartleb
    Date: 2024
    Version: 1.1
#>

param (
    [Parameter(Mandatory=$true)]
    [string]$SourceDirectory,

    [Parameter(Mandatory=$true)]
    [string]$DestinationDirectory
)

# Define the backup filename with timestamp
$BackupFileName = "Backup_" + (Get-Date -Format "yyyyMMdd_HHmmss") + ".zip"

# Combine the destination directory path with the backup filename
$BackupFilePath = Join-Path -Path $DestinationDirectory -ChildPath $BackupFileName

# Define the log file path
$LogFile = "BackupLog_" + (Get-Date -Format 'yyyyMMdd_HHmmss') + ".log"

# Function to log messages
function Log-Message {
    param (
        [string]$Message,
        [string]$Type = "INFO"
    )
    
    $Timestamp = Get-Date -Format 'yyyy-MM-dd HH:mm:ss'
    "$Timestamp [$Type] $Message" | Out-File -Append -FilePath $LogFile
}

# Create the destination directory if it does not exist
if (-not (Test-Path $DestinationDirectory)) {
    try {
        New-Item -Path $DestinationDirectory -ItemType Directory -Force
        Log-Message "Created destination directory: $DestinationDirectory"
    }
    catch {
        Log-Message "Error creating destination directory: $_" -Type "ERROR"
        exit 1
    }
}

# Validate that the source directory exists
if (-not (Test-Path $SourceDirectory)) {
    Log-Message "Source directory does not exist: $SourceDirectory" -Type "ERROR"
    exit 1
}

# Compress and backup the source directory to the destination directory
try {
    Log-Message "Creating backup..."
    Compress-Archive -Path $SourceDirectory -DestinationPath $BackupFilePath -Force
    Log-Message "Backup created successfully: $BackupFilePath"
}
catch {
    Log-Message "Error creating backup: $_" -Type "ERROR"
}
