# Command-Line Banking System

This Command-Line Banking System is a simple, interactive banking application built in Java. The system allows users to register, log in, and manage their accounts. Users can perform basic banking actions such as creating accounts, checking balances, depositing, and withdrawing funds. This project serves as an educational tool to understand Java programming concepts, file handling, and object-oriented programming.

## Table of Contents
- [Project Overview](#project-overview)
- [Setup and Requirements](#setup-and-requirements)
- [Features](#features)
- [Usage and Workflow](#usage-and-workflow)
- [Examples](#examples)

## Project Overview

The system is a console-based banking application. It consists of several Java classes, each responsible for specific functionality:
- `App`: Manages the main application flow.
- `Account`, `User`, `Transaction`: Represent data models for bank accounts, users, and transactions.
- `UserService` and `AccountService`: Handle business logic for user and account operations.
- `UserRepository` and `AccountRepository`: Interact with the file system for data storage and retrieval.

Data is stored in JSON files (`users.json` and `accounts.json`), making the system lightweight and portable.

## Setup and Requirements

### Prerequisites
- Java Development Kit (JDK) 8 or higher
- A Java IDE or text editor (e.g., IntelliJ IDEA, Eclipse, or Visual Studio Code)

### Steps to Set Up
1. Clone this repository:
   ```bash
   git clone https://github.com/yourusername/command-line-banking-system.git
   cd command-line-banking-system
   ```
2. Compile the project:
   ```bash
   javac -d bin src/com/system/banking/*.java src/com/system/banking/model/*.java src/com/system/banking/repository/*.java src/com/system/banking/service/*.java
   ```
3. Run the application:
   ```bash
   java -cp bin com.system.banking.App
   ```

## Features

### User Management
- **Register**: Create a new user by providing a name, email, and password.
- **Login**: Log in with an email and password.

### Account Management
- **Create Account**: Create a new checking or savings account.
- **View Details**: View user profile information and a summary of all accounts, including account numbers, types, balances, and total balance across accounts.

### Transaction Management
- **Deposit Money**: Deposit funds into a specified account.
- **Withdraw Money**: Withdraw funds from a specified account.

## Usage and Workflow

### Main Menu
When you run the application, the following options are presented in the main menu:
```
Main Menu:
1. Register
2. Login
3. Exit
```

1. **Register** - Select this option to create a new user account by providing basic details.
2. **Login** - Log in with email and password.
3. **Exit** - Exit the application.

### User Menu (After Login)
Once logged in, users see the following options in the User Menu:
```
User Menu:
1. View User Details
2. Create Account
3. Withdraw Money
4. Deposit Money
5. Logout
```

1. **View User Details**: Displays the user's profile and a summary of all their accounts.
2. **Create Account**: Allows the user to create a new checking or savings account.
3. **Withdraw Money**: Withdraws funds from a selected account.
4. **Deposit Money**: Deposits funds into a selected account.
5. **Logout**: Logs the user out and returns to the Main Menu.

## Examples

Below are example interactions to help guide users through the application.

### Register and Login

```
Main Menu:
1. Register
2. Login
3. Exit
Select an option: 1

-- Registration --
Enter name: John Doe
Enter email: john@example.com
Enter password: F1a2B3c4D5e6
User registered successfully!

Main Menu:
1. Register
2. Login
3. Exit
Select an option: 2

-- Login --
Enter email: john@example.com
Enter password: F1a2B3c4D5e6
Login successful!
```

### Creating and Viewing Accounts

```
User Menu:
1. View User Details
2. Create Account
3. Withdraw Money
4. Deposit Money
5. Logout
Select an option: 2

Enter account type (checking/savings): savings
Account created successfully!

Select an option: 1
User Details:
Name: John Doe
Email: john@example.com

Accounts:
Account Number: 123-456-7890
Account Type: checking
Balance: 1500.0 USD
--------------------
Account Number: 123-456-7891
Account Type: savings
Balance: 5000.0 USD
--------------------
Total Balance Across All Accounts: 6500.0 USD
```

### Deposit and Withdraw Funds

1. **Deposit Funds**: Select option 4 from the User Menu to deposit funds into an account.
2. **Withdraw Funds**: Select option 3 from the User Menu to withdraw funds from an account.

### Logging Out

When the user selects "Logout," they return to the main menu, where they can either log in with different credentials, register a new user, or exit.

### Error Handling

If an invalid option is selected or if there are issues with account data, the application displays appropriate error messages and prompts the user to take corrective action.

---
