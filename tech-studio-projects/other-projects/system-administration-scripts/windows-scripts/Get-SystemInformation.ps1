<#
.SYNOPSIS
    Get-SystemInformation.ps1 retrieves comprehensive details about the hardware and software
    configuration of a Windows system.

.DESCRIPTION
    This PowerShell script gathers various system information such as computer name, operating system
    specifics, CPU details (including cores and speed), memory capacity, disk size and free space,
    network adapter speed, system uptime, and current date and time. It utilizes PowerShell cmdlets
    like Get-CimInstance and Get-NetAdapter to gather this information, providing a convenient way
    to access crucial system data in a single execution.

.PARAMETER OutputFile
    Optionally specify a file path to export the system information. If not provided, the information
    will be displayed on the console.

.EXAMPLE
    .\Get-SystemInformation.ps1 -OutputFile "C:\Path\To\Output\system_info.txt"
    This command retrieves system information and saves it to "C:\Path\To\Output\system_info.txt".

.NOTES
    Author: Christoph Hartleb
    Date: 2024
    Version: 1.1
#>

param (
    [string]$OutputFile
)

# Function to gather system information
function Get-SystemInformation {
    $info = ""

    $info += "System Information`n"
    $info += "------------------`n"

    # Get computer name
    $computerName = $env:COMPUTERNAME
    $info += "Computer Name: $computerName`n"

    # Get operating system information
    $osInfo = Get-CimInstance Win32_OperatingSystem
    $osName = $osInfo.Caption
    $osVersion = $osInfo.Version
    $info += "Operating System: $osName`n"
    $info += "Version: $osVersion`n"

    # Get CPU information
    $cpuInfo = Get-CimInstance Win32_Processor
    foreach ($cpu in $cpuInfo) {
        $cpuName = $cpu.Name
        $cpuCores = $cpu.NumberOfCores
        $cpuSpeed = $cpu.MaxClockSpeed
        $info += "CPU: $cpuName`n"
        $info += "Cores: $cpuCores`n"
        $info += "Speed (MHz): $cpuSpeed`n"
    }

    # Get memory information
    $memoryInfo = Get-CimInstance Win32_PhysicalMemory | Measure-Object -Property Capacity -Sum
    $totalMemory = [math]::Round($memoryInfo.Sum / 1GB, 2)
    $info += "Total Memory (GB): $totalMemory`n"

    # Get disk information
    $diskInfo = Get-CimInstance Win32_LogicalDisk | Where-Object { $_.DriveType -eq 3 }
    foreach ($disk in $diskInfo) {
        $diskName = $disk.DeviceID
        $diskSize = [math]::Round($disk.Size / 1GB, 2)
        $diskFreeSpace = [math]::Round($disk.FreeSpace / 1GB, 2)
        $info += "Disk $diskName - Size (GB): $diskSize, Free Space (GB): $diskFreeSpace`n"
    }

    # Get network information
    $networkInfo = Get-NetAdapter | Where-Object { $_.Status -eq "Up" }
    foreach ($network in $networkInfo) {
        $networkName = $network.Name
        $networkSpeed = [math]::Round($network.LinkSpeed / 1MB, 2)
        $info += "Network Adapter $networkName - Speed (MB/s): $networkSpeed`n"
    }

    # Get system uptime
    $uptime = (Get-Date) - $osInfo.LastBootUpTime
    $info += "System Uptime: $uptime`n"

    # Get current date and time
    $currentTime = Get-Date
    $info += "Current Date and Time: $currentTime`n"

    return $info
}

# Main function
function Main {
    $systemInfo = Get-SystemInformation

    if ($PSCmdlet.MyInvocation.BoundParameters["OutputFile"]) {
        # Export to file
        $OutputFile = $PSCmdlet.MyInvocation.BoundParameters["OutputFile"]
        try {
            $systemInfo | Out-File -FilePath $OutputFile -Force
            Write-Host "System information exported successfully to $OutputFile"
        }
        catch {
            Write-Host "Error exporting system information: $_"
        }
    }
    else {
        # Display on console
        Write-Host $systemInfo
    }
}

# Call the Main function
Main
