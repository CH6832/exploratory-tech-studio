package com.system.banking.service;

import com.google.gson.Gson;
import com.system.banking.model.Account;
import com.system.banking.utils.JsonUtils;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class AccountService {

    public Account createAccount(String userId, String accountType) {
        // Generate a unique account number
        String accountNumber = generateAccountNumber();

        // Create an account with a balance of 0 initially
        Account account = new Account();
        account.setUserId(userId);
        account.setAccountNumber(accountNumber);
        account.setAccountType(accountType);
        account.setBalance(0.00);
        account.setCurrency("USD");

        // Save account to database or repository
        saveAccount(account);

        return account;
    }

    private String generateAccountNumber() {
        // Generate a random account number or use a sequence generator
        return "123-456-" + (int) (Math.random() * 10000);
    }

    // Save the new account to the accounts.json file
    private void saveAccount(Account account) {
        // Get the current list of accounts
        List<Account> accounts = JsonUtils.readAccounts();

        // If there are no accounts yet, create a new list
        if (accounts == null) {
            accounts = new java.util.ArrayList<>();
        }

        // Add the new account to the list
        accounts.add(account);

        // Save the updated list of accounts back to the JSON file
        JsonUtils.saveAccounts(accounts);
    }
}
