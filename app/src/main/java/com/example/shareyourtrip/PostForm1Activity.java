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

public class PostForm1Activity extends AppCompatActivity {

    // Variables
    EditText city;
    EditText state;
    EditText category;
    FloatingActionButton button_check;
    FloatingActionButton button_cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_form1);
        city = (EditText)findViewById(R.id.city);
        state = (EditText)findViewById(R.id.state);
        category = (EditText)findViewById(R.id.category);
        button_cancel = findViewById(R.id.button_cancel);
        button_check = findViewById(R.id.button_check);

        // check button //must update
        button_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent homepage_Intent = new Intent(PostForm1Activity.this, PostForm2.class);
                startActivity(homepage_Intent);
            }
        });


        // cancel button
        button_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent register_Intent = new Intent(PostForm1Activity.this, HomePageActivity.class);
                startActivity(register_Intent);
                city.getText().clear();
                state.getText().clear();
                category.getText().clear();
            }
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);

        bottomNavigationView.setSelectedItemId(R.id.post_nav);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {

                    case R.id.search_nav:
                        Intent search_Intent = new Intent(PostForm1Activity.this, SearchActivity.class);
                        startActivity(search_Intent);
                        break;

                    case R.id.home_nav:
                        Intent home_Intent = new Intent(PostForm1Activity.this, HomePageActivity.class);
                        startActivity(home_Intent);
                        break;

                    case R.id.favorite_nav:
                        Intent favorite_Intent = new Intent(PostForm1Activity.this, FavoriteActivity.class);
                        startActivity(favorite_Intent);
                        break;

                    case R.id.profile_nav:
                        Intent profile_Intent = new Intent(PostForm1Activity.this, ProfileActivity.class);
                        startActivity(profile_Intent);
                        break;
                }


                return false;
            }
        });
    }
}
