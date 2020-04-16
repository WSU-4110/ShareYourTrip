package com.example.shareyourtrip;

import android.widget.FrameLayout;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class PostAdapterTest {

    //Some common testing variables
    private static List<Post> emptyPostList;
    private static List<Post> lightPostList;

    private static PostAdapter emptyPostAdapter;
    private static PostAdapter lightPostAdapter;

    @BeforeClass
    public static void setUp(){

        //Initializing various post lists to put in our post adapter.
        List<Post> emptyPostList = new ArrayList<>();

        List<Post> lightPostList = new ArrayList<>();
        lightPostList.add(new Post());
        lightPostList.add(new Post());
        lightPostList.add(new Post());

        emptyPostAdapter = new ProfilePostAdapter(emptyPostList);
        lightPostAdapter = new ProfilePostAdapter(lightPostList);

    }

    @Test
    public void onCreateViewHolder() {
        //Testing in Fav activity
        lightPostAdapter.onCreateViewHolder(new FrameLayout(new FavoriteActivity().getApplicationContext()),0);
        emptyPostAdapter.onCreateViewHolder(new FrameLayout(new FavoriteActivity().getApplicationContext()),0);

        //Testing in Profile activity
        lightPostAdapter.onCreateViewHolder(new FrameLayout(new ProfileActivity().getApplicationContext()),0);
        emptyPostAdapter.onCreateViewHolder(new FrameLayout(new ProfileActivity().getApplicationContext()),0);

        //Testing in home activity
        lightPostAdapter.onCreateViewHolder(new FrameLayout(new HomePageActivity().getApplicationContext()),0);
        emptyPostAdapter.onCreateViewHolder(new FrameLayout(new HomePageActivity().getApplicationContext()),0);

    }

    @Test
    public void onBindViewHolder() {

        PostAdapter.MyViewHolder emptyHolder = new PostAdapter(emptyPostList).new MyViewHolder();
        PostAdapter.MyViewHolder lightHolder = new PostAdapter(lightPostList).new MyViewHolder();

        lightPostAdapter.onBindViewHolder(emptyHolder, 0);
        emptyPostAdapter.onBindViewHolder(lightHolder,0);
    }

    @Test
    public void getItemCount() {

        int lightCount = lightPostAdapter.getItemCount();
        int emptyCount = emptyPostAdapter.getItemCount();

        assert (lightCount == 3);
        assert (emptyCount == 0);
        assert (lightCount >= 0);
        assert (emptyCount >= 0);
    }
}