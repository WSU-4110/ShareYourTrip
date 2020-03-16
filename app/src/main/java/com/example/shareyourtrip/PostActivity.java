package com.example.shareyourtrip;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.textfield.TextInputLayout;

public class PostActivity extends AppCompatActivity {

    // Variables
    FloatingActionButton button_add_post;
    TextInputLayout city = null;
    TextInputLayout state  = null;
    TextInputLayout category  = null;
    TextInputLayout title  = null;
    EditText description  = null;
    FloatingActionButton button_cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        city = (TextInputLayout)findViewById(R.id.city);
        state  = (TextInputLayout)findViewById(R.id.state);
        category  = (TextInputLayout)findViewById(R.id.category);
        title  = (TextInputLayout)findViewById(R.id.title);
        description  = (EditText)findViewById(R.id.description);
        button_cancel = findViewById(R.id.button_cancel);
        button_add_post = (FloatingActionButton)findViewById(R.id.button_add_post);

        // check button //must update
        button_add_post.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {
                //getting variables from user input
                city = (TextInputLayout)findViewById(R.id.city);
                state  = (TextInputLayout)findViewById(R.id.state);
                category  = (TextInputLayout)findViewById(R.id.category);
                title  = (TextInputLayout)findViewById(R.id.title);
                description  = (EditText)findViewById(R.id.description);

                // if()

                Intent post_page_intent = new Intent(PostActivity.this, PostActivity.class);
                startActivity(post_page_intent);
            }

        });


        // cancel button
        button_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent register_Intent = new Intent(PostActivity.this, HomePageActivity.class);
                startActivity(register_Intent);
            }
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);

        bottomNavigationView.setSelectedItemId(R.id.post_nav);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {

                    case R.id.search_nav:
                        Intent search_Intent = new Intent(PostActivity.this, SearchActivity.class);
                        startActivity(search_Intent);
                        break;

                    case R.id.home_nav:
                        Intent home_Intent = new Intent(PostActivity.this, HomePageActivity.class);
                        startActivity(home_Intent);
                        break;

                    case R.id.favorite_nav:
                        Intent favorite_Intent = new Intent(PostActivity.this, FavoriteActivity.class);
                        startActivity(favorite_Intent);
                        break;

                    case R.id.profile_nav:
                        Intent profile_Intent = new Intent(PostActivity.this, ProfileActivity.class);
                        startActivity(profile_Intent);
                        break;
                }


                return false;
            }
        });
    }
}