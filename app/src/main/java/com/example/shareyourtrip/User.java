package com.example.shareyourtrip;

import java.util.ArrayList;
import java.util.List;

public class User {

    //Member Variables
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String usename;
    private String password;
    private String confirmPassword;
    private boolean isBanned;

    //A list of the users favorite posts.
    private List<UserPost> favList = new ArrayList<UserPost>();

    public User(String firstName, String lastName, String emailAddress, String usename, String password, String confirmPassword, boolean isBanned) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.usename = usename;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.isBanned = isBanned;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getUsename() {
        return usename;
    }

    public void setUsename(String usename) {
        this.usename = usename;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) { this.confirmPassword = confirmPassword; }

    public boolean isBanned() {
        return isBanned;
    }

    public void setBanned(boolean banned) {
        isBanned = banned;
    }

    public List<UserPost> getFavList() { return favList; }

    public void setFavList(List<UserPost> favList) { this.favList = favList; }
}
