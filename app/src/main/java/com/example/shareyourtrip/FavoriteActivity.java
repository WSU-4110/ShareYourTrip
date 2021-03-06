package com.example.shareyourtrip;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.MenuItem;

import android.widget.Toast;

import android.view.View;
import android.widget.ToggleButton;


import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;


/*
        This activity is to pull the users favorite posts
    and display them all in one Recycler view. The Recycler
    uses UserPostAdapter as a blueprint for each row.
 */

public class FavoriteActivity extends AppCompatActivity {

    //todo: Figure out how to reference users favList instead of using test list
    private List<Post> favList = new ArrayList<>();
    private RecyclerView recyclerView;
    private PostAdapter postAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        PostDAO postDAO = new PostDAO(this);

        //Assigning recycler view
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        postAdapter = new PostAdapter(favList, this);

        //Setting up our recycler view
        RecyclerView.LayoutManager postLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(postLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(postAdapter);

        //preparePostData();

        // Reading favorite posts from database
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("select * from post ");
        stringBuilder.append("inner join postFav on ");
        stringBuilder.append("post.id = postFav.postid and postFav.useremail = '");
        stringBuilder.append(FirebaseAuth.getInstance().getCurrentUser().getEmail());
        stringBuilder.append("';");

        try {
            List<Post> listPost = postDAO.listAllPost(stringBuilder.toString(), null);
            favList.addAll(listPost);
            for(Post favPost : favList){
                favPost.setFavorited(true);
            }
            postAdapter.notifyDataSetChanged();
        }
        catch (SQLiteException e){
            Toast.makeText(FavoriteActivity.this,"There is a database problem!"+e.getMessage(),Toast.LENGTH_LONG).show();;
        }

        //Bottom Navigation
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setSelectedItemId(R.id.favorite_nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {

                    case R.id.search_nav:
                        Intent search_Intent = new Intent(FavoriteActivity.this, SearchActivity.class);
                        startActivity(search_Intent);
                        break;

                    case R.id.post_nav:
                        Intent post_Intent = new Intent(FavoriteActivity.this, PostActivity.class);
                        startActivity(post_Intent);
                        break;

                    case R.id.home_nav:
                        Intent home_Intent = new Intent(FavoriteActivity.this, HomePageActivity.class);
                        startActivity(home_Intent);
                        break;

                    case R.id.profile_nav:
                        Intent profile_Intent = new Intent(FavoriteActivity.this, ProfileActivity.class);
                        startActivity(profile_Intent);
                        break;
                }


                return false;
            }
        });
    }

//UsedforTesting
    private void preparePostData(){

        Post post = new Post("Plymouth", "MI", "Park", "McClumpha Park is Great!", "This is a great mid sized park in the area - especially when there aren't too many others around. There are a few baseball fields in the back and a hill that's used for sledding in the winter. There is also a small pond with a floating dock that you can fish off of. I saw someone catch and release a rather large snapping turtle from there! There are also good sheltered areas for group picnics and little kids to play (a large play structure and maybe small water park).", "Jeff B.", "1/1/2020", "0", "0", true);
        favList.add(post);

        post = new Post("Detroit", "MI", "Museum", "The DIA", "Visited most recently on a busy Saturday during the holidays.  Great to see so many people enjoying a world class museum.  Have been coming here all my life and it is my base line for evaluating museums throughout the world.  Nowhere else can you set and enjoy the Rivera courtyard.  There are great special exhibitions and a marvelous collection that you can  spend days exploring.  The cafe, Kresge Courtyard and Museum shop are always worth a stop. I do miss the convenience of the underground parking but got lucky with street parking this visit.", "Sydney A.", "2/4/2020", "0", "0", false);
        favList.add(post);

        post = new Post("Chicago", "IL", "Museum", "Family Fun at Shedd's Aquarium", "The Shedd is leading the game in terms of accessibility! I was treated to a very lovely dolphin/wild life show that was sensory friendly. Wish more museums were on the same level. \n" +
                "\n" +
                "They also house a great variety of animals in this building with tons of info available if you're curious. The layout is very convenient and the staff is very helpful. Highly recommend if you're looking to explore one of Chicago's many museums.", "Sally H.", "12/1/2020", "0", "0",false);
        favList.add(post);

        post = new Post("City", "State", "Category", "Title", "Desc", "Author", "Date", "0", "0", false);
        favList.add(post);

        postAdapter.notifyDataSetChanged();
    }

}
