package com.example.shareyourtrip;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;

import java.sql.SQLException;

public class PostActivity extends AppCompatActivity {

    // Variables
    FloatingActionButton button_add_post;
    EditText city = null;
    EditText state  = null;
    EditText title  = null;
    EditText description  = null;
    FloatingActionButton button_cancel;
    //String user;
    PostDAO postDAO;
    boolean flag = false;
    //private FirebaseAuth auth;

    // Array of search categories
    String CategoryArray[] = {"All categories", "Entertainment", "Museum", "Park", "Restaurant"};

    // Spinner variable
    Spinner spinner_category;

    // Array adapter for spiiner
    ArrayAdapter<String> arrayadapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
/*        city = (TextInputLayout)findViewById(R.id.city);
        state  = (TextInputLayout)findViewById(R.id.state);
        category  = (TextInputLayout)findViewById(R.id.category);
        title  = (TextInputLayout)findViewById(R.id.title);
        description  = (EditText)findViewById(R.id.description);*/
        button_cancel = (FloatingActionButton)findViewById(R.id.button_cancel);
        button_add_post = (FloatingActionButton)findViewById(R.id.button_add_post);
        postDAO = new PostDAO(this);
    //    auth = FirebaseAuth.getInstance();

        spinner_category=(Spinner)findViewById(R.id.Category_Spinner);
        arrayadapter= new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, CategoryArray);
        spinner_category.setAdapter(arrayadapter);

        spinner_category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                // check button //must update
                button_add_post.setOnClickListener(new View.OnClickListener() {

                    @Override

                    public void onClick(View v) {
                        //getting variables from user input
                        city = (EditText) findViewById(R.id.city);
                        state  = (EditText) findViewById(R.id.state);
                        spinner_category  = (Spinner)findViewById(R.id.Category_Spinner);
                        title  = (EditText) findViewById(R.id.title);
                        description  = (EditText)findViewById(R.id.description);
                        //user = FirebaseAuth.getInstance().getCurrentUser().getEmail();


                        try {
                            flag = postDAO.insert(city.getText().toString(),
                                    state.getText().toString(),
                                    spinner_category.toString(),
                                    title.getText().toString(),
                                    description.getText().toString(),
                                    FirebaseAuth.getInstance().getCurrentUser().getEmail());
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }

                        if(flag){
                            Intent post_page_intent = new Intent(PostActivity.this, ProfileActivity.class);
                            startActivity(post_page_intent);
                        }else{
                            //need to print error message to screen
                            Intent post_page_intent = new Intent(PostActivity.this, PostActivity.class);
                            startActivity(post_page_intent);
                        }

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

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}