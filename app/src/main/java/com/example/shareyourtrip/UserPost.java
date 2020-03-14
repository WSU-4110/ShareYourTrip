package com.example.shareyourtrip;

public class UserPost {
    private String city, state, category, title, description;

    public UserPost() {
        this.city = "";
        this.state = "";
        this.category = "";
        this.title = "";
        this.description = "";
    }

    public UserPost(String city, String state, String category, String title, String description) {
        this.city = city;
        this.state = state;
        this.category = category;
        this.title = title;
        this.description = description;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getCategory() {
        return category;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
