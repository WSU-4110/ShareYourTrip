package com.example.shareyourtrip;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;

import java.sql.SQLException;
import java.util.*;
import java.text.*;

public class PostActivity extends AppCompatActivity {

    // Variables
    FloatingActionButton button_add_post;
    TextInputLayout city = null;
    TextInputLayout state  = null;
    TextInputLayout category  = null;
    TextInputLayout title  = null;
    EditText description  = null;
    FloatingActionButton button_cancel;
    //String user;
    PostDAO postDAO;
    boolean flag = false;
    //private FirebaseAuth auth;

    public static void alertDisplay(Context context, String msg, boolean success){
        AlertDialog alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Post Not Added");
        alertDialog.setMessage(msg);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alertDialog.show();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
/*        city = (TextInputLayout)findViewById(R.id.city);
        state  = (TextInputLayout)findViewById(R.id.state);
        category  = (TextInputLayout)findViewById(R.id.category);
        title  = (TextInputLayout)findViewById(R.id.title);
        description  = (EditText)findViewById(R.id.description);*/
        button_cancel = findViewById(R.id.button_cancel);
        button_add_post = findViewById(R.id.button_add_post);
        postDAO = new PostDAO(this);

        // check button //must update
        button_add_post.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {
                //getting variables from user input
                city = findViewById(R.id.city);
                state  = findViewById(R.id.state);
                category  = findViewById(R.id.category);
                title  = findViewById(R.id.title);
                description  = findViewById(R.id.description);
                SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String str_date = date.format(new Date());
                System.out.println(str_date);

                //System.out.print(date.format(new Date().toString()));
                flag = postDAO.insert(city.getEditText().getText().toString(),
                        state.getEditText().getText().toString(),
                        category.getEditText().getText().toString(),
                        title.getEditText().getText().toString(),
                        description.getText().toString(),
                        FirebaseAuth.getInstance().getCurrentUser().getEmail(),
                        str_date);// storing date in format YYYY-MM-DD HH:MM:SSl

                if(flag){
                    Intent post_page_intent = new Intent(PostActivity.this, ProfileActivity.class);
                    startActivity(post_page_intent);
                }else{
                    //need to print error message to screen
                    //Intent post_page_intent = new Intent(PostActivity.this, PostActivity.class);
                    //startActivity(post_page_intent);
                    alertDisplay(PostActivity.this, "Error! Missing information in one or more fields.",false);
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
}