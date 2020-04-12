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
    private String up;
    private String down;
    private boolean isFavorited;

    public Post() {
        this.id = null;
        this.city = null;
        this.state = null;
        this.category = null;
        this.title = null;
        this.description = null;
        this.user = null;
        this.date = null;
        this.up = null;
        this.down = null;
    }


    public Post(Post otherPost)
    {
        this.id = otherPost.id;
        this.city = otherPost.city;
        this.state = otherPost.state;
        this.category = otherPost.category;
        this.title = otherPost.title;
        this.description = otherPost.description;
        this.user = otherPost.user;
        this.date = otherPost.date;
        this.up = otherPost.up;
        this.down = otherPost.down;
        this.isFavorited = otherPost.isFavorited;
    }


    public Post(String id, String city,String state, String category, String title, String description, String user, String date, String up, String down, boolean isFavorited) {
        this(city,state, category, title, description, user, date, up, down, isFavorited);
        this.id = id;
    }

    public Post(String city, String state, String category, String title, String description, String user, String date, String up, String down, boolean isFavorited) {
        this.city = city;
        this.state = state;
        this.category = category;
        this.title = title;
        this.description = description;
        this.user = user;
        this.date = date;
        this.up = up;
        this.down = down;
        this.isFavorited = isFavorited;
    }

    //Getters
    public String getId() {
        return id;
    }
    public String getCity() {
        return city;
    }
    public String getState() {
        return state;
    }
    public String getTitle() {
        return title;
    }
    public String getCategory() { return category; }
    public String getDescription() { return description; }
    public String getUser() { return user; }
    public String getDate(){ return date;}
    public String getUp() { return up; }
    public String getDown() { return down; }
    public boolean getFavorited() { return isFavorited; }

    //Setters
    public void setId(String id) { this.id = id; }
    public void setCity(String city) {
        this.city = city;
    }
    public void setState(String state) {
        this.state = state;
    }
    public void setTitle(String title) { this.title = title; }
    public void setCategory(String category) { this.category = category; }
    public void setDescription(String description) { this.description = description; }
    public void setUser(String user) { this.user = user; }
    public void setDate(String date) { this.date = date; }
    public void setUp(String up) { this.up = up; }
    public void setDown(String down) { this.down = down; }
    public void setFavorited(boolean isFavorited) { this.isFavorited = isFavorited; }
}
