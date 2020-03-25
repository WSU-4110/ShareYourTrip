package com.example.shareyourtrip;

public class WayneStatePostBuilder extends PostBuilder {

    public void buildCity() {
        post.setCity("Detroit");
    }

    public void buildState() {
        post.setState("MI");
    }

    public void buildCategory() {
        post.setCategory("School");
    }

    public void buildTitle() {
        post.setTitle("Wasyne State");
    }

    public void buildDescription() {
        post.setDescription("Wayne state is: ");
    }
}
