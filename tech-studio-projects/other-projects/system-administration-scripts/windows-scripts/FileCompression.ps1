<#
.SYNOPSIS
    This script compresses files or directories into a ZIP archive.

.DESCRIPTION
    This PowerShell script provides functionality to compress files or directories into a ZIP archive.
    It includes a function, Compress-Files, which takes two parameters: $sourcePath (the path of the
    files or directories to be compressed) and $destinationPath (the path where the compressed ZIP
    archive will be saved). The script utilizes the Compress-Archive cmdlet to perform the compression
    operation. Upon completion, it displays a message indicating the success of the compression process.
    Any errors encountered during the compression process are caught and displayed.

.PARAMETER sourcePath
    Specifies the path of the files or directories to be compressed.

.PARAMETER destinationPath
    Specifies the path where the compressed ZIP archive will be saved.

.EXAMPLE
    .\FileCompression.ps1 -sourcePath "C:\Data" -destinationPath "D:\Backup\compressed.zip"
    This command compresses the files or directories located at "C:\Data" and saves the compressed
    ZIP archive to "D:\Backup\compressed.zip".

.NOTES
    Author: Christoph Hartleb
    Date: 2024
    Version: 1.1
#>

# Function to compress files or directories
function Compress-Files {
    param (
        [string]$sourcePath,
        [string]$destinationPath
    )

    Write-Host "Compressing files or directories from '$sourcePath' to '$destinationPath'..."
    
    # Validate source path
    if (-not (Test-Path $sourcePath)) {
        Write-Host "Error: Source path '$sourcePath' does not exist."
        return
    }

    # Ensure destination directory exists
    $destinationDirectory = Split-Path $destinationPath -Parent
    if (-not (Test-Path $destinationDirectory)) {
        Write-Host "Destination directory does not exist. Creating directory..."
        New-Item -Path $destinationDirectory -ItemType Directory -Force
    }
    
    try {
        # Compress the source path to the destination path
        Compress-Archive -Path $sourcePath -DestinationPath $destinationPath -Force
        
        Write-Host "Compression completed successfully."
    }
    catch {
        Write-Host "Error during compression: $_"
    }
}

# Main function
function Main {
    param (
        [string]$sourcePath = "C:\Path\To\Source",
        [string]$destinationPath = "C:\Path\To\Destination\compressed.zip"
    )

    Write-Host "File Compression Script"
    Write-Host "-----------------------"
    
    # Call the Compress-Files function
    Compress-Files -sourcePath $sourcePath -destinationPath $destinationPath
}

# Parse command-line arguments
param (
    [string]$sourcePath = "C:\Path\To\Source",
    [string]$destinationPath = "C:\Path\To\Destination\compressed.zip"
)

# Call the Main function with provided arguments
Main -sourcePath $sourcePath -destinationPath $destinationPath
