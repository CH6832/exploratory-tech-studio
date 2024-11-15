package com.system.banking.utils;

import com.google.gson.reflect.TypeToken;
import com.system.banking.model.Account;
import com.system.banking.model.User;

import java.io.*;
import java.lang.reflect.Type;
import java.util.List;
import com.google.gson.Gson;

/**
 * Utility class for handling JSON serialization and deserialization.
 * Provides methods for reading from and writing to JSON files,
 * with specific paths for user and account data files.
 */
public class JsonUtils {

    /**
     * Gson instance used for JSON parsing and formatting.
     */
    private static final Gson gson = new Gson();

    /**
     * File path for storing account data in JSON format.
     */
    public static final String ACCOUNTS_FILE_PATH = "src/main/resources/databases/accounts.json";

    /**
     * File path for storing user data in JSON format.
     */
    public static final String USERS_FILE_PATH = "src/main/resources/databases/users.json";

    /**
     * Serializes an object to JSON and writes it to the specified file.
     * If the file's directory does not exist, it creates the necessary directories.
     *
     * @param object   The object to serialize to JSON.
     * @param filePath The file path where the JSON will be written.
     */
    public static void writeJson(Object object, String filePath) {
        try {
            File file = new File(filePath);

            // Check if the parent directory exists; create it if not.
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }

            // Serialize object to JSON and write to file
            try (FileWriter writer = new FileWriter(filePath)) {
                gson.toJson(object, writer);
            }
        } catch (IOException e) {
            System.err.println("Error writing JSON to file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Reads JSON from the specified file and deserializes it into a List of the specified type.
     * This is a generic method, allowing it to be used for lists of various types (e.g., List<User>, List<Account>).
     *
     * @param filePath The path to the JSON file.
     * @param type     The class type of list elements (User or Account).
     * @param <T>      The type parameter for the list elements.
     * @return A List of the specified type, or null if file reading fails.
     */
    public static <T> List<T> readJson(String filePath, Class<T> type) {
        try (FileReader reader = new FileReader(filePath)) {
            // Use TypeToken to specify the type of list elements for deserialization
            Type listType = TypeToken.getParameterized(List.class, type).getType();
            return gson.fromJson(reader, listType);
        } catch (IOException e) {
            // Handle any exceptions that occur during file reading
            e.printStackTrace();
            return null;
        }
    }

    // Previous commented-out method for reading User JSON is omitted for clarity.

    /**
     * Serializes and saves a list of Account objects to the JSON file specified in ACCOUNTS_FILE_PATH.
     * Overwrites any existing data in the file.
     *
     * @param accounts List of Account objects to save.
     */
    public static void saveAccounts(List<Account> accounts) {
        try (Writer writer = new FileWriter(ACCOUNTS_FILE_PATH)) {
            gson.toJson(accounts, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Reads and deserializes a list of Account objects from the JSON file at ACCOUNTS_FILE_PATH.
     *
     * @return A List of Account objects, or null if file reading fails.
     */
    public static List<Account> readAccounts() {
        try (Reader reader = new FileReader(ACCOUNTS_FILE_PATH)) {
            // Specify the type for List<Account> deserialization using TypeToken
            Type accountListType = new TypeToken<List<Account>>() {}.getType();
            return gson.fromJson(reader, accountListType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
