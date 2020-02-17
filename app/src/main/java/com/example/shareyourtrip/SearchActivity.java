package com.example.shareyourtrip;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);

        bottomNavigationView.setSelectedItemId(R.id.search_nav);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {

                    case R.id.home_nav:
                        Intent home_Intent = new Intent(SearchActivity.this, HomePageActivity.class);
                        startActivity(home_Intent);
                        break;

                    case R.id.post_nav:
                        Intent post_Intent = new Intent(SearchActivity.this, PostActivity.class);
                        startActivity(post_Intent);
                        break;

                    case R.id.favorite_nav:
                        Intent favorite_Intent = new Intent(SearchActivity.this, FavoriteActivity.class);
                        startActivity(favorite_Intent);
                        break;

                    case R.id.profile_nav:
                        Intent profile_Intent = new Intent(SearchActivity.this, ProfileActivity.class);
                        startActivity(profile_Intent);
                        break;
                }


                return false;
            }
        });
    }
}
