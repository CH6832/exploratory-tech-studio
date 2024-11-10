package com.system.banking;

import com.system.banking.model.User;
import com.system.banking.repository.UserRepository;
import com.system.banking.service.AccountService;
import com.system.banking.service.UserService;
import java.util.Scanner;
import java.io.IOException;

public class App {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        // Create an instance of UserRepository
        UserRepository userRepository = new UserRepository();
        // Initialize UserService
        UserService userService = new UserService(userRepository);
        AccountService accountService = new AccountService();

        // Sample user data interaction
        User user = userService.getUserById("1");
        System.out.println("User Details: " + user);

        // Registration example
        System.out.println("Welcome to the Banking System!");
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        userService.registerUser(name, email, password);
        System.out.println("User registered successfully!");

        // Login example
        System.out.print("Enter email to login: ");
        String loginEmail = scanner.nextLine();
        System.out.print("Enter password to login: ");
        String loginPassword = scanner.nextLine();

        if (userService.loginUser(loginEmail, loginPassword)) {
            System.out.println("Login successful!");
            System.out.print("Enter account type to create (checking/savings): ");
            String accountType = scanner.nextLine();

            // Create account
            String userId = "51";  // Example userId, replace with actual logic
            accountService.createAccount(userId, accountType);
            System.out.println("Account created successfully!");
        } else {
            System.out.println("Invalid credentials, please try again.");
        }

        scanner.close();
    }
}
