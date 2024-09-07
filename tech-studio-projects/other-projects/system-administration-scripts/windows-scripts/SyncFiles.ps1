<#
.SYNOPSIS
    SyncFiles.ps1 synchronizes files or directories from a source to a destination.

.DESCRIPTION
    SyncFiles.ps1 is a PowerShell script that provides functionality to synchronize files or directories
    from a source path to a destination path. It includes a function, Sync-Files, which takes two parameters:
    $sourcePath (the path of the files or directories to be synchronized) and $destinationPath (the path where
    the files or directories will be synchronized). The script uses the Copy-Item cmdlet with the -Recurse and
    -Force parameters to perform the synchronization operation. It also includes parameter validation, logging,
    and an optional dry run mode. Upon completion, it displays a message indicating the success of the synchronization
    process. Any errors encountered during the synchronization process are caught and displayed.

.PARAMETER sourcePath
    Specifies the path of the files or directories to be synchronized.

.PARAMETER destinationPath
    Specifies the path where the files or directories will be synchronized.

.PARAMETER dryRun
    Specifies whether to perform a dry run (only display actions without executing them).

.EXAMPLE
    .\SyncFiles.ps1 -sourcePath "C:\Data" -destinationPath "D:\Backup" -dryRun
    This command performs a dry run to show what would be synchronized from "C:\Data" to "D:\Backup" without
    actually copying any files.

.NOTES
    Author: Christoph Hartleb
    Date: 2024
    Version: 1.1
#>

param (
    [string]$sourcePath = "C:\Path\To\Source",
    [string]$destinationPath = "C:\Path\To\Destination",
    [switch]$dryRun
)

# Function to synchronize files or directories
function Sync-Files {
    param (
        [string]$sourcePath,
        [string]$destinationPath,
        [switch]$dryRun
    )

    Write-Host "Synchronizing files from '$sourcePath' to '$destinationPath'..."
    
    if (-not (Test-Path $sourcePath)) {
        Write-Host "Error: The source path '$sourcePath' does not exist."
        return
    }

    if (-not (Test-Path $destinationPath)) {
        Write-Host "Error: The destination path '$destinationPath' does not exist. Creating directory..."
        try {
            if (-not $dryRun) {
                New-Item -Path $destinationPath -ItemType Directory -Force
            }
        }
        catch {
            Write-Host "Error: $_"
            return
        }
    }

    try {
        $itemsToSync = Get-ChildItem -Path $sourcePath -Recurse

        foreach ($item in $itemsToSync) {
            $destinationItemPath = $item.FullName.Replace($sourcePath, $destinationPath)
            
            if ($item.PSIsContainer) {
                if (-not $dryRun) {
                    if (-not (Test-Path $destinationItemPath)) {
                        New-Item -Path $destinationItemPath -ItemType Directory -Force
                    }
                }
                Write-Host "Directory created: $destinationItemPath"
            }
            else {
                if ($dryRun) {
                    Write-Host "File to copy: $($item.FullName) to $destinationItemPath"
                }
                else {
                    Copy-Item -Path $item.FullName -Destination $destinationItemPath -Force
                    Write-Host "File copied: $($item.FullName) to $destinationItemPath"
                }
            }
        }
        
        Write-Host "Files synchronized successfully."
    }
    catch {
        Write-Host "Error: $_"
    }
}

# Main function
function Main {
    Write-Host "File Synchronization Script"
    Write-Host "---------------------------"
    
    # Call the Sync-Files function
    Sync-Files -sourcePath $sourcePath -destinationPath $destinationPath -dryRun $dryRun
}

# Call the Main function
Main
