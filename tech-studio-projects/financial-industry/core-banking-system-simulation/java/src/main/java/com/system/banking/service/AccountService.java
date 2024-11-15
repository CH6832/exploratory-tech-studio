package com.system.banking.service;

import com.system.banking.model.Account;
import com.system.banking.repository.AccountRepository;
import com.system.banking.utils.JsonUtils;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Service class responsible for handling account-related operations in the banking system.
 * This includes creating accounts, managing withdrawals, deposits, and updating account information.
 */
public class AccountService {

    private AccountRepository accountRepository;  // Repository class for managing account data
    public static Scanner scanner;  // Scanner to handle user input for account-related operations

    /**
     * Constructor to initialize the AccountService with an AccountRepository.
     *
     * @param accountRepository The repository used to manage account data.
     */
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    /**
     * Creates a new account for a user.
     * The account is assigned a unique account number and initialized with a balance of 0.
     *
     * @param userId    The ID of the user creating the account.
     * @param accountType The type of account being created (e.g., "Checking", "Savings").
     * @return The newly created Account.
     */
    public Account createAccount(String userId, String accountType) {
        // Generate a unique account number
        String accountNumber = generateAccountNumber();

        // Create an Account object with the provided details
        Account account = new Account();
        account.setUserId(userId);
        account.setAccountNumber(accountNumber);
        account.setAccountType(accountType);
        account.setBalance(0.00);  // Initial balance is 0
        account.setCurrency("USD");  // Default currency is USD

        // Save the new account to the repository
        saveAccount(account);

        return account;
    }

    /**
     * Generates a unique account number. The account number is a combination of a static prefix
     * and a random 4-digit number.
     *
     * @return The generated account number as a String.
     */
    private String generateAccountNumber() {
        // Example format: "123-456-XXXX", where XXXX is a random number
        return "123-456-" + (int) (Math.random() * 10000);
    }

    /**
     * Saves the newly created account to the accounts JSON file.
     * This method reads the current list of accounts, adds the new account, and saves the updated list.
     *
     * @param account The account to be saved.
     */
    private void saveAccount(Account account) {
        // Read the current list of accounts from the JSON file
        List<Account> accounts = JsonUtils.readAccounts();

        // If no accounts exist yet, initialize a new list
        if (accounts == null) {
            accounts = new java.util.ArrayList<>();
        }

        // Add the new account to the list
        accounts.add(account);

        // Save the updated list of accounts back to the JSON file
        JsonUtils.saveAccounts(accounts);
    }

    /**
     * Retrieves a list of accounts associated with a specific user ID.
     * This method filters all accounts to only include those matching the userId.
     *
     * @param userId The ID of the user whose accounts are to be retrieved.
     * @return A list of Account objects belonging to the specified user.
     */
    public List<Account> getAccountsByUserId(String userId) {
        // Fetch all accounts from the repository
        List<Account> allAccounts = accountRepository.readAllAccounts();

        // Filter accounts that belong to the specified userId
        return allAccounts.stream()
                .filter(account -> account.getUserId().equals(userId))
                .collect(Collectors.toList());
    }

    /**
     * Allows a user to withdraw money from one of their accounts.
     * The user selects the account, and the withdrawal is processed if there are sufficient funds.
     *
     * @param email The email address of the user making the withdrawal.
     */
    public void withdrawMoney(String email) {
        // Get the user's accounts by their email address
        List<Account> accounts = getAccountsByUserId(email);

        // If the user has no accounts, prompt them to create one
        if (accounts.isEmpty()) {
            System.out.println("You don't have any accounts. Please create an account first.");
            return;
        }

        // Display available accounts to the user
        System.out.println("\nSelect an account to withdraw from:");
        for (int i = 0; i < accounts.size(); i++) {
            Account account = accounts.get(i);
            System.out.println(i + 1 + ". " + account.getAccountType() + " - " + account.getAccountNumber() + " (Balance: " + account.getBalance() + ")");
        }

        // Let the user select the account to withdraw from
        System.out.print("Select account number: ");
        int accountIndex = Integer.parseInt(scanner.nextLine()) - 1;
        Account selectedAccount = accounts.get(accountIndex);

        // Ask for the withdrawal amount
        System.out.print("Enter withdrawal amount: ");
        double amount = Double.parseDouble(scanner.nextLine());

        // Check if the account has sufficient funds
        if (amount <= selectedAccount.getBalance()) {
            selectedAccount.setBalance(selectedAccount.getBalance() - amount);  // Update account balance
            updateAccount(selectedAccount);  // Save the updated account
            System.out.println("Withdrawal successful! New balance: " + selectedAccount.getBalance());
        } else {
            System.out.println("Insufficient funds. Withdrawal failed.");
        }
    }

    /**
     * Allows a user to deposit money into one of their accounts.
     * The user selects the account, and the deposit is processed.
     *
     * @param email The email address of the user making the deposit.
     */
    public void depositMoney(String email) {
        // Get the user's accounts by their email address
        List<Account> accounts = getAccountsByUserId(email);

        // If the user has no accounts, prompt them to create one
        if (accounts.isEmpty()) {
            System.out.println("You don't have any accounts. Please create an account first.");
            return;
        }

        // Display available accounts to the user
        System.out.println("\nSelect an account to deposit into:");
        for (int i = 0; i < accounts.size(); i++) {
            Account account = accounts.get(i);
            System.out.println(i + 1 + ". " + account.getAccountType() + " - " + account.getAccountNumber() + " (Balance: " + account.getBalance() + ")");
        }

        // Let the user select the account to deposit into
        System.out.print("Select account number: ");
        int accountIndex = Integer.parseInt(scanner.nextLine()) - 1;
        Account selectedAccount = accounts.get(accountIndex);

        // Ask for the deposit amount
        System.out.print("Enter deposit amount: ");
        double amount = Double.parseDouble(scanner.nextLine());

        // Update account balance with the deposited amount
        selectedAccount.setBalance(selectedAccount.getBalance() + amount);
        updateAccount(selectedAccount);  // Save the updated account
        System.out.println("Deposit successful! New balance: " + selectedAccount.getBalance());
    }

    /**
     * Updates an account in the repository after a transaction (withdrawal or deposit).
     * The method searches for the account by its account number and saves the updated data.
     *
     * @param account The account to be updated.
     */
    public void updateAccount(Account account) {
        // Read the current list of accounts from the JSON file
        List<Account> accounts = JsonUtils.readAccounts();

        // Find the account in the list and update it
        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).getAccountNumber().equals(account.getAccountNumber())) {
                accounts.set(i, account);  // Replace the old account with the updated one
                break;
            }
        }

        // Save the updated list of accounts back to the JSON file
        JsonUtils.saveAccounts(accounts);
    }
}
