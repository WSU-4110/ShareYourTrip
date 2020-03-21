package com.example.shareyourtrip;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import com.google.android.material.bottomnavigation.BottomNavigationView;

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

        //Assigning recycler view
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        postAdapter = new PostAdapter(favList);



        //Setting up our recycler view
        RecyclerView.LayoutManager postLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(postLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(postAdapter);

        preparePostData();

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

    //Used for Testing
    private void preparePostData(){

        Post post = new Post("Plymouth", "MI", "Park", "McClumpha", "Lit", "Jeff");
        favList.add(post);

        post = new Post("City", "State", "Category", "Title", "Desc", "Author");
        favList.add(post);

        post = new Post("City", "State", "Category", "Title", "Desc", "Author");
        favList.add(post);

        post = new Post("City", "State", "Category", "Title", "Desc", "Author");
        favList.add(post);

        postAdapter.notifyDataSetChanged();
    }

}
