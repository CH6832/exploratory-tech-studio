package com.system.banking;

import com.system.banking.model.Account;
import com.system.banking.model.User;
import com.system.banking.repository.AccountRepository;
import com.system.banking.repository.UserRepository;
import com.system.banking.service.AccountService;
import com.system.banking.service.UserService;

import java.util.List;
import java.util.Scanner;
import java.io.IOException;

/**
 * The main entry point for the Command-Line Banking System.
 * Provides a user interface to register, login, and manage accounts and transactions.
 */
public class App {
    private static UserService userService;
    private static AccountService accountService;
    public static Scanner scanner;

    /**
     * The main method initializes services, repositories, and displays the main menu.
     *
     * @param args Command-line arguments (not used in this application)
     * @throws IOException if there is an issue with file handling in repositories
     */
    public static void main(String[] args) throws IOException {
        scanner = new Scanner(System.in);
        UserRepository userRepository = new UserRepository();
        userService = new UserService(userRepository);
        AccountRepository accountRepository = new AccountRepository();
        accountService = new AccountService(accountRepository);

        System.out.println("Welcome to the Command-Line Banking System!");

        boolean running = true;
        while (running) {
            showMainMenu();
            System.out.print("\nSelect an option: ");
            String choice = scanner.nextLine();

            // Handle user input for main menu choices
            switch (choice) {
                case "1":
                    registerUser();
                    break;
                case "2":
                    loginUser();
                    break;
                case "3":
                    running = false;
                    System.out.println("Thank you for using the banking system. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Please select a valid option.");
            }
        }
        scanner.close();
    }

    /**
     * Displays the main menu with options to register, login, or exit the application.
     */
    private static void showMainMenu() {
        System.out.println("\nMain Menu:");
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.println("3. Exit");
    }

    /**
     * Prompts the user for registration details and registers a new user.
     *
     * @throws IOException if there is an error during user data storage
     */
    private static void registerUser() throws IOException {
        System.out.println("\n-- Registration --");
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        userService.registerUser(name, email, password);
        System.out.println("User registered successfully!");
    }

    /**
     * Prompts the user for login credentials and initiates a session upon successful login.
     */
    private static void loginUser() {
        System.out.println("\n-- Login --");
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        if (userService.loginUser(email, password)) {
            System.out.println("Login successful!");
            loggedInMenu(email);  // Transition to logged-in user menu
        } else {
            System.out.println("Invalid credentials, please try again.");
        }
    }

    /**
     * Displays the user menu after successful login, allowing the user to manage accounts and transactions.
     *
     * @param email The email of the logged-in user
     */
    private static void loggedInMenu(String email) {
        boolean loggedIn = true;
        User user = userService.findUserByEmail(email);
        while (loggedIn) {
            System.out.println("\nUser Menu:");
            System.out.println("1. View User Details");
            System.out.println("2. Create Account");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Deposit Money");
            System.out.println("5. Logout");

            System.out.print("Select an option: ");
            String choice = scanner.nextLine();

            // Handle user input for logged-in menu choices
            switch (choice) {
                case "1":
                    viewUserDetails(email);
                    break;
                case "2":
                    accountService.createAccount(user.getUserId(), choice);
                    break;
                case "3":
                    accountService.withdrawMoney(email);
                    break;
                case "4":
                    accountService.depositMoney(email);
                    break;
                case "5":
                    loggedIn = false;
                    System.out.println("Logging out...");
                    break;
                default:
                    System.out.println("Invalid option. Please select a valid option.");
            }
        }
    }

    /**
     * Displays the user's personal information and account details.
     *
     * @param email The email address of the user to view details for
     */
    private static void viewUserDetails(String email) {
        User user = userService.findUserByEmail(email);

        if (user != null) {
            System.out.println("\nUser Details:");
            System.out.println("Name: " + user.getFullName());
            System.out.println("Email: " + user.getEmailAddress());

            // Retrieve and display user's account information
            List<Account> userAccounts = accountService.getAccountsByUserId(user.getUserId());

            if (userAccounts != null && !userAccounts.isEmpty()) {
                double totalBalance = 0;
                System.out.println("\nAccounts:");

                for (Account account : userAccounts) {
                    System.out.println("Account Number: " + account.getAccountNumber());
                    System.out.println("Account Type: " + account.getAccountType());
                    System.out.println("Balance: " + account.getBalance() + " " + account.getCurrency());
                    totalBalance += account.getBalance();
                    System.out.println("--------------------");
                }

                System.out.println("Total Balance Across All Accounts: " + totalBalance + " USD");
            } else {
                System.out.println("No accounts found for this user.");
            }
        } else {
            System.out.println("User not found.");
        }
    }
}
