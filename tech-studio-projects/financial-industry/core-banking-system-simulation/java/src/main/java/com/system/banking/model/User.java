package com.system.banking.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class User {
    @SerializedName("id")
    private String userId;
    @SerializedName("name")
    private String username;
    @SerializedName("email")
    private String email;
    @SerializedName("password")
    private String password;

    // Getters and setters
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getFullName() { return username; }
    public void setFullName(String fullName) { this.username = fullName; }

    public String getEmailAddress() { return email; }
    public void setEmailAddress(String emailAddress) { this.email = emailAddress; }

    public String getPassword() { return password;}
    public void setPassword(String password) { this.password = password; }
}

