package com.example.shareyourtrip;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import android.view.View;
import android.widget.ImageButton;

import android.database.sqlite.SQLiteException;

import android.widget.Toast;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class HomePageActivity extends AppCompatActivity {


    private List<Post> favList = new ArrayList<>();
    private List<Post> postsList = new ArrayList<Post>();
    private RecyclerView recyclerView;
    private PostAdapter postAdapter;
    private ImageButton favButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        PostDAO postDAO = new PostDAO(this);


        //Assigning recycler view
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        postAdapter = new PostAdapter(postsList,this);

        //Setting up our recycler view
        RecyclerView.LayoutManager postLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(postLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(postAdapter);

        //preparePostData();

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("select * from post order by id desc;");

        try {
            List<Post> listPost = postDAO.listAllPost(stringBuilder.toString(), null);
            postsList.addAll(listPost);
            postAdapter.notifyDataSetChanged();
        } catch (SQLiteException e) {
            Toast.makeText(HomePageActivity.this, "There is a database problem!" + e.getMessage(), Toast.LENGTH_LONG).show();
            ;
        }


        //Bottom Navigation
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setSelectedItemId(R.id.home_nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {

                    case R.id.search_nav:
                        Intent search_Intent = new Intent(HomePageActivity.this, SearchActivity.class);
                        startActivity(search_Intent);
                        break;

                    case R.id.post_nav:
                        Intent post_Intent = new Intent(HomePageActivity.this, PostActivity.class);
                        startActivity(post_Intent);
                        break;

                    case R.id.favorite_nav:
                        Intent favorite_Intent = new Intent(HomePageActivity.this, FavoriteActivity.class);
                        startActivity(favorite_Intent);
                        break;

                    case R.id.profile_nav:
                        Intent profile_Intent = new Intent(HomePageActivity.this, ProfileActivity.class);
                        startActivity(profile_Intent);
                        break;
                }


                return false;
            }
        });
    }


    //Used for Testing
    private void preparePostData(){

        Post post = new Post("Plymouth", "MI", "Park", "McClumpha Park is Great!", "This is a great mid sized park in the area - especially when there aren't too many others around. There are a few baseball fields in the back and a hill that's used for sledding in the winter. There is also a small pond with a floating dock that you can fish off of. I saw someone catch and release a rather large snapping turtle from there! There are also good sheltered areas for group picnics and little kids to play (a large play structure and maybe small water park).", "Jeff B.", "1/1/2020", "0", "0");
        favList.add(post);

        post = new Post("Detroit", "MI", "Museum", "The DIA", "Visited most recently on a busy Saturday during the holidays.  Great to see so many people enjoying a world class museum.  Have been coming here all my life and it is my base line for evaluating museums throughout the world.  Nowhere else can you set and enjoy the Rivera courtyard.  There are great special exhibitions and a marvelous collection that you can  spend days exploring.  The cafe, Kresge Courtyard and Museum shop are always worth a stop. I do miss the convenience of the underground parking but got lucky with street parking this visit.", "Sydney A.", "2/4/2020", "0", "0");
        favList.add(post);

        post = new Post("Chicago", "IL", "Museum", "Family Fun at Shedd's Aquarium", "The Shedd is leading the game in terms of accessibility! I was treated to a very lovely dolphin/wild life show that was sensory friendly. Wish more museums were on the same level. \n" +
                "\n" +
                "They also house a great variety of animals in this building with tons of info available if you're curious. The layout is very convenient and the staff is very helpful. Highly recommend if you're looking to explore one of Chicago's many museums.", "Sally H.", "12/1/2020", "0", "0");
        favList.add(post);

        post = new Post("City", "State", "Category", "Title", "Desc", "Author", "Date", "0", "0");
        favList.add(post);

        postAdapter.notifyDataSetChanged();
    }
}

