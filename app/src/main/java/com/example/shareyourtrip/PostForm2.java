package com.example.shareyourtrip;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class PostForm2 extends AppCompatActivity {

    // Variables
    EditText title;
    EditText description;
    Button button_check;
    Button button_cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        title = (EditText)findViewById(R.id.title);
        description = (EditText)findViewById(R.id.description);
        button_cancel = (Button)findViewById(R.id.button_cancel);
        button_check = (Button)findViewById(R.id.button_check);

        // Login button
        m_loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent homepage_Intent = new Intent(PostForm2.this, HomePageActivity.class);
                startActivity(homepage_Intent);
                user_username.getText().clear();
                user_password.getText().clear();
            }
        });


        // Register account
        m_registerbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent register_Intent = new Intent(PostForm2.this, RegistrationActivity.class);
                startActivity(register_Intent);
                user_username.getText().clear();
                user_password.getText().clear();
            }
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);

        bottomNavigationView.setSelectedItemId(R.id.post_nav);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {

                    case R.id.search_nav:
                        Intent search_Intent = new Intent(PostForm2.this, SearchActivity.class);
                        startActivity(search_Intent);
                        break;

                    case R.id.home_nav:
                        Intent home_Intent = new Intent(PostForm2.this, HomePageActivity.class);
                        startActivity(home_Intent);
                        break;

                    case R.id.favorite_nav:
                        Intent favorite_Intent = new Intent(PostForm2.this, FavoriteActivity.class);
                        startActivity(favorite_Intent);
                        break;

                    case R.id.profile_nav:
                        Intent profile_Intent = new Intent(PostForm2.this, ProfileActivity.class);
                        startActivity(profile_Intent);
                        break;
                }


                return false;
            }
        });
    }
}
