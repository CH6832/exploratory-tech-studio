package com.system.banking.repository;

import com.system.banking.model.User;
import com.system.banking.utils.JsonUtils;

import java.io.IOException;
import java.util.Collections;
import java.util.List;


public class UserRepository {

    private static final String USERS_FILE_PATH = "src/main/resources/databases/users.json";

    public List<User> readUsers() {
        // Use JsonUtils to deserialize the JSON into a List of User objects
        return JsonUtils.readJson(USERS_FILE_PATH);
    }

    // Fetch user by ID
    public User getUserById(String userId) {
        List<User> users = readUsers();
        for (User user : users) {
            if (user.getUserId().equals(userId)) {
                return user;
            }
        }
        return null; // Or handle user not found
    }

    public void writeUsers(List<User> users) throws IOException {
        JsonUtils.writeJson(USERS_FILE_PATH, users.toString());
    }
}