package com.system.banking.utils;

import com.google.gson.reflect.TypeToken;
import com.system.banking.model.Account;
import com.system.banking.model.User;

import java.io.*;
import java.lang.reflect.Type;
import java.util.List;
import com.google.gson.Gson;

public class JsonUtils {

    private static final Gson gson = new Gson();
    private static final String ACCOUNTS_FILE_PATH = "src/main/resources/databases/accounts.json";

    // Serialize object to JSON
    public static void writeJson(Object object, String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            gson.toJson(object, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // Deserialize JSON into a List of Users
    public static List<User> readJson(String filePath) {
        try (FileReader reader = new FileReader(filePath)) {
            Type userListType = new TypeToken<List<User>>() {}.getType(); // Use TypeToken for generic List<User>
            return gson.fromJson(reader, userListType);  // Deserialize as List<User>
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Save a list of accounts to the JSON file
    public static void saveAccounts(List<Account> accounts) {
        Gson gson = new Gson();
        try (Writer writer = new FileWriter(ACCOUNTS_FILE_PATH)) {
            gson.toJson(accounts, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Read a list of accounts from the JSON file
    public static List<Account> readAccounts() {
        Gson gson = new Gson();
        try (Reader reader = new FileReader(ACCOUNTS_FILE_PATH)) {
            Type accountListType = new TypeToken<List<Account>>() {}.getType();
            return gson.fromJson(reader, accountListType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}


