package com.example.shareyourtrip;


public class Post {
    private String city;
    private String state;
    private String category;
    private String title;
    private String description;
    private String user;

    public Post() {
        this.city = "";
        this.state = "";
        this.category = "";
        this.title = "";
        this.description = "";
        this.user = "";
    }

    public Post(String city, String state, String category, String title, String description, String user) {
        this.city = city;
        this.state = state;
        this.category = category;
        this.title = title;
        this.description = description;
        this.user = user;
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

    public String getUser() {
        return user;
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

    public void setUser(String user) {
        this.description = user;
    }
}
