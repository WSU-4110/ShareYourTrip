package com.example.shareyourtrip;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

public class ProfileActivity extends AppCompatActivity {

    // Variables
    Button m_settingsbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        PostDAO postDAO = new PostDAO(this);

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("select * from post where ");
        stringBuilder.append("user='");
        stringBuilder.append(FirebaseAuth.getInstance().getCurrentUser().getEmail());
        stringBuilder.append("'");

        try {
            List<Post> listPost = postDAO.listAllPost(stringBuilder.toString(), null);
        }
        catch (SQLiteException e){
            Toast.makeText(ProfileActivity.this,"There is a database problem!"+e.getMessage(),Toast.LENGTH_LONG).show();;
        }

        m_settingsbutton = (Button)findViewById(R.id.Settings_Button);

        // Login button
        m_settingsbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent settings_Intent = new Intent(ProfileActivity.this, SettingActivity.class);
                startActivity(settings_Intent);
            }
        });

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
