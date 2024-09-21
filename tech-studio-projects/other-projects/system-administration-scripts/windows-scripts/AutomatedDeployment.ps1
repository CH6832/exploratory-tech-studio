<#
.SYNOPSIS
    AutomatedDeployment.ps1 facilitates the automated deployment of applications.

.DESCRIPTION
    This PowerShell script automates the deployment process for an application. It pulls the
    latest code from a Git repository, builds the application if necessary, and restarts the
    relevant service. Error handling is implemented to gracefully manage any deployment failures.

.EXAMPLE
    ./AutomatedDeployment.ps1 -Branch 'develop' -ServiceName 'YourServiceName'

.PARAMETER Branch
    The branch of the Git repository to pull from.

.PARAMETER ServiceName
    The name of the service to restart after deployment.

.NOTES
    Author: Christoph Hartleb
    Date: 2024
    Version: 1.1
#>

param (
    [string]$Branch = "master",
    [string]$ServiceName
)

# Define the log file path
$LogFile = "DeploymentLog_$(Get-Date -Format 'yyyyMMdd_HHmmss').log"

# Function to log messages
function Log-Message {
    param (
        [string]$Message,
        [string]$Type = "INFO"
    )
    
    $Timestamp = Get-Date -Format 'yyyy-MM-dd HH:mm:ss'
    "$Timestamp [$Type] $Message" | Out-File -Append -FilePath $LogFile
}

# Function to deploy the application
function Deploy-Application {
    Log-Message "Starting deployment process..."
    
    try {
        # Pull the latest code from the Git repository
        Log-Message "Pulling latest code from branch '$Branch'..."
        git pull origin $Branch | Tee-Object -FilePath $LogFile

        # Build the application (if needed)
        Log-Message "Building the application..."
        # Replace this command with your build command
        # Example: dotnet build | Tee-Object -FilePath $LogFile
        
        # Restart the application service
        if ($ServiceName) {
            Log-Message "Restarting service '$ServiceName'..."
            Restart-Service -Name $ServiceName -Force
            Log-Message "Service '$ServiceName' restarted successfully."
        } else {
            Log-Message "No service name provided. Skipping service restart."
        }

        Log-Message "Deployment completed successfully."
    }
    catch {
        Log-Message "Error: $_" -Type "ERROR"
        exit 1
    }
}

# Main function
function Main {
    Log-Message "Automated Deployment Script started."
    
    # Validate parameters
    if (-not $ServiceName) {
        Log-Message "Error: ServiceName parameter is required." -Type "ERROR"
        exit 1
    }

    # Call the Deploy-Application function
    Deploy-Application

    Log-Message "Automated Deployment Script completed."
}

# Call the Main function
Main
