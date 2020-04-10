package com.example.shareyourtrip;


public class  Post {

    private String id;
    private String city;
    private String state;
    private String category;
    private String title;
    private String description;
    private String user;
    private String date;

    public Post() {
        this.id = null;
        this.city = null;
        this.state = null;
        this.category = null;
        this.title = null;
        this.description = null;
        this.user = null;
        this.date = null;
    }

    public Post(String id, String city,String state, String category, String title, String description, String user, String date) {
        this(city,state, category, title, description, user, date);
        this.id = id;
    }

    public Post(String city, String state, String category, String title, String description, String user, String date) {
        this.city = city;
        this.state = state;
        this.category = category;
        this.title = title;
        this.description = description;
        this.user = user;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
