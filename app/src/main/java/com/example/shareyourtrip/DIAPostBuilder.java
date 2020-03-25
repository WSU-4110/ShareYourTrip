package com.example.shareyourtrip;

public class DIAPostBuilder extends PostBuilder{

    public void buildCity() {
        post.setCity("Detroit");
    }

    public void buildState() {
        post.setState("MI");
    }

    public void buildCategory() {
        post.setCategory("Museum");
    }

    public void buildTitle() {
        post.setTitle("Detroit Institute of Arts");
    }

    public void buildDescription() {
        post.setDescription("The DIA is: ");
    }
}
