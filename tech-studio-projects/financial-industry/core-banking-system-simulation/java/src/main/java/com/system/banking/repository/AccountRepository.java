package com.system.banking.repository;

import java.util.List;
import com.google.gson.Gson;
import com.system.banking.model.Account;
import com.system.banking.utils.ExceptionUtils;
import com.system.banking.utils.JsonUtils;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Repository class for managing Account data.
 * It provides methods for reading account information from a JSON file
 * and allows retrieval of specific account details based on account number.
 */
public class AccountRepository {

    // Constant for the path to the accounts file (JSON format)
    protected static final String ACCOUNTS_FILE_PATH = "src/main/resources/databases/accounts.json";

    /**
     * Reads all accounts from the JSON file and returns them as a List of Account objects.
     * If the file cannot be read or is empty, an empty list is returned.
     *
     * @return A List of Account objects read from the JSON file.
     */
    public List<Account> readAllAccounts() {
        // This method reads account data from a JSON file
        try (FileReader reader = new FileReader(ACCOUNTS_FILE_PATH)) {
            Gson gson = new Gson();
            // Convert the JSON file content into an array of Account objects
            Account[] accountsArray = gson.fromJson(reader, Account[].class);
            List<Account> accounts = new ArrayList<>();

            // If accounts are found in the file, add them to the list
            if (accountsArray != null) {
                for (Account account : accountsArray) {
                    accounts.add(account);  // Add each account to the list
                }
            }
            return accounts;  // Return the list of accounts
        } catch (IOException e) {
            e.printStackTrace();  // Log any IOExceptions
            return new ArrayList<>();  // Return an empty list if an error occurs
        }
    }

    /**
     * Retrieves an account from the JSON file based on the provided account number.
     *
     * @param accountNumber The account number to search for.
     * @return The Account object with the specified account number, or null if not found.
     * @throws ExceptionUtils.FileMissingException If the file containing the accounts doesn't exist or is empty.
     */
    public Account getAccountByNumber(String accountNumber) {
        // Read all accounts from the JSON file using a utility method
        List<Account> accounts = JsonUtils.readJson(ACCOUNTS_FILE_PATH, Account.class);

        // Check if accounts are available in the file
        if (accounts == null) {
            // If accounts are missing or empty, throw a custom exception
            throw new ExceptionUtils.FileMissingException("Accounts file is missing or empty.");
        }

        // Loop through the list of accounts to find the one matching the account number
        for (Account account : accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;  // Return the account if the account number matches
            }
        }

        // Return null if no account with the given account number is found
        return null;
    }
}
