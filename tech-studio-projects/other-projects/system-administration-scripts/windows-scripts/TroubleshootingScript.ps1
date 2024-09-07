<#
.SYNOPSIS
    This PowerShell script performs troubleshooting and debugging tasks on a Windows 11 or Windows Server 2022 machine.

.DESCRIPTION
    The script includes functions to check system event logs for errors and warnings, check disk health using SMART attributes,
    and check system resources such as CPU, memory, and disk usage. It provides valuable insights into potential system errors,
    hardware failures, and resource utilization issues, aiding in system diagnostics and troubleshooting.

.PARAMETER None
    This script does not accept any parameters.

.EXAMPLE
    .\TroubleshootingScript.ps1
    This command executes the script to perform troubleshooting and debugging tasks on the system.

.NOTES
    Author: Christoph Hartleb
    Date: 2024
    Version: 1.1
#>

# Function to check system event logs for errors and warnings
function Check-EventLogs {
    Write-Host "Checking system event logs for errors and warnings..."
    
    try {
        # Get recent events from System and Application event logs
        $systemEvents = Get-WinEvent -LogName System -ErrorAction SilentlyContinue | Where-Object { $_.LevelDisplayName -match "Error|Warning" }
        $applicationEvents = Get-WinEvent -LogName Application -ErrorAction SilentlyContinue | Where-Object { $_.LevelDisplayName -match "Error|Warning" }
        
        # Display recent error and warning events
        Write-Host "Recent System Errors and Warnings:"
        $systemEvents | Select-Object TimeCreated, LevelDisplayName, Message -First 5 | Format-Table -AutoSize
        
        Write-Host "Recent Application Errors and Warnings:"
        $applicationEvents | Select-Object TimeCreated, LevelDisplayName, Message -First 5 | Format-Table -AutoSize
    }
    catch {
        Write-Host "Error checking event logs: $_"
    }
}

# Function to check disk health using SMART attributes
function Check-DiskHealth {
    Write-Host "Checking disk health using SMART attributes..."
    
    try {
        # Get disk information
        $diskInfo = Get-CimInstance Win32_DiskDrive
        
        foreach ($disk in $diskInfo) {
            # Get SMART attributes
            $smartAttributes = Get-CimInstance -Namespace "ROOT\WMI" -Class MSStorageDriver_FailurePredictStatus | Where-Object { $_.InstanceName -match $disk.DeviceID }
            
            Write-Host "Disk: $($disk.DeviceID)"
            
            # Check SMART attributes for any failure predictions
            if ($smartAttributes.PredictFailure) {
                Write-Host "SMART Predicted Failure: True"
            }
            else {
                Write-Host "SMART Predicted Failure: False"
            }
        }
    }
    catch {
        Write-Host "Error checking disk health: $_"
    }
}

# Function to check system resources such as CPU, memory, and disk usage
function Check-SystemResources {
    Write-Host "Checking system resources..."
    
    try {
        # Get CPU usage
        $cpuUsage = Get-CimInstance Win32_PerfFormattedData_PerfOS_Processor | Where-Object { $_.Name -eq "_Total" } | Select-Object -ExpandProperty PercentProcessorTime
        Write-Host "CPU Usage (%): $cpuUsage"
        
        # Get memory usage
        $memoryInfo = Get-CimInstance Win32_OperatingSystem
        $totalMemory = $memoryInfo.TotalVisibleMemorySize / 1MB
        $freeMemory = $memoryInfo.FreePhysicalMemory / 1MB
        $usedMemory = $totalMemory - $freeMemory
        Write-Host "Total Memory (MB): $totalMemory"
        Write-Host "Used Memory (MB): $usedMemory"
        Write-Host "Free Memory (MB): $freeMemory"
        
        # Get disk usage
        $diskInfo = Get-CimInstance Win32_LogicalDisk | Where-Object { $_.DriveType -eq 3 }
        foreach ($disk in $diskInfo) {
            $diskFreeSpace = $disk.FreeSpace / 1GB
            $diskSize = $disk.Size / 1GB
            $diskUsedSpace = $diskSize - $diskFreeSpace
            Write-Host "Disk $($disk.DeviceID) - Used Space (GB): $diskUsedSpace, Free Space (GB): $diskFreeSpace"
        }
    }
    catch {
        Write-Host "Error checking system resources: $_"
    }
}

# Main function
function Main {
    Write-Host "Troubleshooting and Debugging Script"
    Write-Host "-------------------------------------"
    
    # Call troubleshooting functions
    Check-EventLogs
    Check-DiskHealth
    Check-SystemResources
}

# Call the Main function
Main
