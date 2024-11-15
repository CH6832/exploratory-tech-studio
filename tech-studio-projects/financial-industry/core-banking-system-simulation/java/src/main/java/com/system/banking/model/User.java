package com.system.banking.model;

import com.google.gson.annotations.SerializedName;

/**
 * Represents a User in the banking system.
 * This class is used to model user data and to facilitate
 * the conversion of data between JSON and Java objects using Gson annotations.
 */
public class User {

    // Field for the user ID, annotated for JSON serialization/deserialization
    @SerializedName("id")
    private String userId;

    // Field for the user's full name, annotated for JSON serialization/deserialization
    @SerializedName("name")
    private String username;

    // Field for the user's email address, annotated for JSON serialization/deserialization
    @SerializedName("email")
    private String email;

    // Field for the user's password, annotated for JSON serialization/deserialization
    @SerializedName("password")
    private String password;

    // Getter for the user ID
    public String getUserId() {
        return userId;
    }

    // Setter for the user ID
    public void setUserId(String userId) {
        this.userId = userId;
    }

    // Getter for the user's full name
    public String getFullName() {
        return username;
    }

    // Setter for the user's full name
    public void setFullName(String fullName) {
        this.username = fullName;
    }

    // Getter for the user's email address
    public String getEmailAddress() {
        return email;
    }

    // Setter for the user's email address
    public void setEmailAddress(String emailAddress) {
        this.email = emailAddress;
    }

    // Getter for the user's password
    public String getPassword() {
        return password;
    }

    // Setter for the user's password
    public void setPassword(String password) {
        this.password = password;
    }
}
