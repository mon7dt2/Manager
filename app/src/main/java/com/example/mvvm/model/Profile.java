package com.example.mvvm.model;

/**
 * Created by Mon7 on 10/22/2021.
 */
public class Profile{
    private String id;
    private String fullName;
    private String phone;
    private String address;
    private String identityCard;
    private String description;
    private String avatarUrl;
    private int gender;
    private String birthday;
    private String email;

    public Profile() {
    }

    public Profile(String id, String fullName, String phone, String address, String identityCard, String description, String avatarUrl, int gender, String birthday, String email) {
        this.id = id;
        this.fullName = fullName;
        this.phone = phone;
        this.address = address;
        this.identityCard = identityCard;
        this.description = description;
        this.avatarUrl = avatarUrl;
        this.gender = gender;
        this.birthday = birthday;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIdentityCard() {
        return identityCard;
    }

    public void setIdentityCard(String identityCard) {
        this.identityCard = identityCard;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
