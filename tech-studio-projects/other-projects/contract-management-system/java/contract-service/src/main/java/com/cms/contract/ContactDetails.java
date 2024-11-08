package com.cms.contract;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ContactDetails {

    @JsonProperty("email")
    private String email;

    @JsonProperty("phone")
    private String phone;

    // Getters and setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
