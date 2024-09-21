#!/bin/bash

################################################################################
# Script Name:       Ubuntu Automation Script
# Description:       This script automates the implementation of security best practices and hardening measures on your Ubuntu system:
#                    - configure_firewall: Configures the firewall using UFW to allow necessary incoming connections (e.g., SSH, HTTP, HTTPS).
#                    - setup_sudo_access: Sets up sudo access for a specified user, granting administrative privileges.
#                    - secure_ssh: Secures the SSH configuration by disabling root login, password authentication, and enabling key-based authentication.
#                    - update_system_packages: Updates system packages to ensure the latest security patches are installed.
#                    Save the script to a file (e.g., system_hardening.sh), give it execute permissions (chmod +x system_hardening.sh), and then execute it (./system_hardening.sh). The script will automate the implementation of security best practices and hardening measures on your Ubuntu system.
# Author:            Christoph Hartleb
# Date:              July 21, 2024
# Version:           1.1
# 
# Usage:             ./system_hardening.sh
# 
# Notes:             
#   - Make sure to customize the script according to your specific requirements.
#   - Some tasks may require root privileges. Ensure proper permissions.
#   - Test the script thoroughly in a safe environment before production use.
# https://google.github.io/styleguide/shellguide.html
################################################################################

# Function to configure firewall using UFW
configure_firewall() {
    echo "Configuring firewall using UFW..."
    
    # Enable UFW
    sudo ufw --force enable
    
    # Allow SSH connections
    sudo ufw allow OpenSSH
    
    # Allow HTTP and HTTPS connections (if needed)
    sudo ufw allow http
    sudo ufw allow https
    
    # Check UFW status
    sudo ufw status verbose
    
    echo "Firewall configured successfully."
}

# Function to set up sudo access for a user
setup_sudo_access() {
    echo "Setting up sudo access for a user..."
    
    # Prompt for username
    read -p "Enter username to grant sudo access: " username
    
    # Check if user exists
    if id "$username" &>/dev/null; then
        # Add user to sudo group
        sudo usermod -aG sudo "$username"
        echo "Sudo access granted to user '$username'."
    else
        echo "Error: User '$username' does not exist."
    fi
}

# Function to secure SSH configuration
secure_ssh() {
    echo "Securing SSH configuration..."
    
    # Backup SSH configuration file
    sudo cp /etc/ssh/sshd_config /etc/ssh/sshd_config_backup
    
    # Disable root login
    sudo sed -i 's/#PermitRootLogin prohibit-password/PermitRootLogin no/' /etc/ssh/sshd_config
    
    # Disable password authentication (use SSH keys)
    sudo sed -i 's/#PasswordAuthentication yes/PasswordAuthentication no/' /etc/ssh/sshd_config
    
    # Restart SSH service
    sudo systemctl restart ssh
    
    echo "SSH configuration secured."
}

# Function to update system packages
update_system_packages() {
    echo "Updating system packages..."
    
    # Update package lists
    sudo apt-get update
    
    # Upgrade installed packages
    sudo apt-get upgrade -y
    
    # Optionally, upgrade distribution
    # sudo apt-get dist-upgrade -y
    
    echo "System packages updated."
}

# Main function
main() {
    echo "System Hardening Script"
    echo "-----------------------"
    
    # Configure firewall
    configure_firewall
    
    # Set up sudo access
    setup_sudo_access
    
    # Secure SSH configuration
    secure_ssh
    
    # Update system packages
    update_system_packages
    
    echo "System hardening completed."
}

# Call the main function
main
