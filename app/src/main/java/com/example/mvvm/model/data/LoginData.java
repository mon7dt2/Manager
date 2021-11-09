package com.example.mvvm.model.data;

/**
 * Created by Mon7 on 10/9/2021.
 */
public class LoginData{
    private String customerID;
    private String fullName;
    private String avatarUrl;
    private String email;

    public LoginData() {
    }

    public LoginData(String customerID, String fullName, String avatarUrl, String email) {
        this.customerID = customerID;
        this.fullName = fullName;
        this.avatarUrl = avatarUrl;
        this.email = email;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
