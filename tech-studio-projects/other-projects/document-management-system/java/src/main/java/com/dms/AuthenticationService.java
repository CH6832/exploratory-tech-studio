package com.dms;

public class AuthenticationService {
    private UserManager userManager;

    public AuthenticationService() {
        this.userManager = new UserManager();
    }

    public boolean authenticate(String username, String password) {
        User user = userManager.getUserByUsername(username);
        return user != null && user.getPassword().equals(password);
    }
}
