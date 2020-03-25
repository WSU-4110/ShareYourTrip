package com.example.shareyourtrip;

class AddPost {

    public Post constructPost(PostBuilder builder) {
        builder.buildCity();
        builder.buildState();
        builder.buildCategory();
        builder.buildTitle();
        builder.buildDescription();
        return builder.getPost();
    }
}
