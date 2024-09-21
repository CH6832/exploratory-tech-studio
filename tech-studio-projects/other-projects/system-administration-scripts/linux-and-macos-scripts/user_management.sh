#!/bin/bash

################################################################################
# Script Name:       User Management Script
# Description:       A script to manage users in a Linux environment, including
#                    creating, deleting users, and managing user group memberships.
# Author:            Christoph Hartleb
# Date:              July 21, 2024
# Version:           1.1
# 
# Usage:             ./user_management.sh
# 
# Notes:             
#   - Customize the script according to your specific requirements.
#   - Some tasks may require root privileges. Ensure proper permissions.
#   - Test the script thoroughly in a safe environment before production use.
# https://google.github.io/styleguide/shellguide.html
################################################################################

# Function to create a new user
create_user() {
    echo "Creating a new user..."
    # Prompt for username
    read -p "Enter username for the new user: " username
    
    # Check if the username already exists
    if id "$username" &>/dev/null; then
        echo "Error: User '$username' already exists."
    else
        # Prompt for password
        read -sp "Enter password for the new user: " password
        echo ""
        
        # Create the user with the specified password
        sudo useradd -m -s /bin/bash "$username"
        echo "$username:$password" | sudo chpasswd
        
        # Display success message
        echo "User '$username' created successfully."
    fi
}

# Function to delete an existing user
delete_user() {
    echo "Deleting an existing user..."
    # Prompt for username
    read -p "Enter username to delete: " username
    
    # Check if the username exists
    if id "$username" &>/dev/null; then
        # Prompt for confirmation
        read -p "Are you sure you want to delete user '$username'? This will remove the user's home directory (y/n): " confirm
        if [[ "$confirm" =~ ^[Yy]$ ]]; then
            # Delete the user
            sudo userdel -r "$username"
            echo "User '$username' deleted successfully."
        else
            echo "User deletion canceled."
        fi
    else
        echo "Error: User '$username' does not exist."
    fi
}

# Function to assign rights to a user
assign_rights() {
    echo "Assigning rights to a user..."
    # Prompt for username
    read -p "Enter username to assign rights to: " username
    
    # Check if the username exists
    if id "$username" &>/dev/null; then
        # Prompt for rights
        read -p "Enter rights to assign (comma-separated list of groups): " rights
        
        # Convert comma-separated groups into an array
        IFS=',' read -r -a groups <<< "$rights"
        
        # Assign the specified rights (groups) to the user
        for group in "${groups[@]}"; do
            sudo usermod -aG "$group" "$username"
        done
        echo "Rights ('$rights') assigned to user '$username' successfully."
    else
        echo "Error: User '$username' does not exist."
    fi
}

# Function to revoke rights from a user
revoke_rights() {
    echo "Revoking rights from a user..."
    # Prompt for username
    read -p "Enter username to revoke rights from: " username
    
    # Check if the username exists
    if id "$username" &>/dev/null; then
        # Prompt for rights
        read -p "Enter rights to revoke (comma-separated list of groups): " rights
        
        # Convert comma-separated groups into an array
        IFS=',' read -r -a groups <<< "$rights"
        
        # Revoke the specified rights (groups) from the user
        for group in "${groups[@]}"; do
            sudo deluser "$username" "$group"
        done
        echo "Rights ('$rights') revoked from user '$username' successfully."
    else
        echo "Error: User '$username' does not exist."
    fi
}

# Main function
main() {
    while true; do
        # Display menu options
        echo "User Management Script"
        echo "----------------------"
        echo "1. Create User"
        echo "2. Delete User"
        echo "3. Assign Rights to User"
        echo "4. Revoke Rights from User"
        echo "5. Exit"
        
        # Prompt for user choice
        read -p "Enter your choice: " choice
        
        # Process user choice
        case "$choice" in
            1) create_user ;;
            2) delete_user ;;
            3) assign_rights ;;
            4) revoke_rights ;;
            5) echo "Exiting." ; exit 0 ;;
            *) echo "Invalid choice. Please enter a number between 1 and 5." ;;
        esac
    done
}

# Call the main function
main
