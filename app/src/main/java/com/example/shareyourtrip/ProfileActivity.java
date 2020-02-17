package com.example.shareyourtrip;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ProfileActivity extends AppCompatActivity {

    // Variables
    Button m_settingsbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);

        bottomNavigationView.setSelectedItemId(R.id.profile_nav);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {

                    case R.id.search_nav:
                        Intent search_Intent = new Intent(ProfileActivity.this, SearchActivity.class);
                        startActivity(search_Intent);
                        break;

                    case R.id.post_nav:
                        Intent post_Intent = new Intent(ProfileActivity.this, PostActivity.class);
                        startActivity(post_Intent);
                        break;

                    case R.id.favorite_nav:
                        Intent favorite_Intent = new Intent(ProfileActivity.this, FavoriteActivity.class);
                        startActivity(favorite_Intent);
                        break;

                    case R.id.home_nav:
                        Intent home_Intent = new Intent(ProfileActivity.this, HomePageActivity.class);
                        startActivity(home_Intent);
                        break;
                }


                return false;
            }
        });
    }
}
