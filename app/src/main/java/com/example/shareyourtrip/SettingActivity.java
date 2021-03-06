package com.example.shareyourtrip;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SettingActivity extends AppCompatActivity {

    // Variables
    Button m_logoutbutton;
    Button m_contactbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);


        // Links this variable with the front end variable Logout_Button
        m_logoutbutton = (Button)findViewById(R.id.Logout_Button);

        // Function that checks the logout button
        m_logoutbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent login_Intent = new Intent(SettingActivity.this, LoginActivity.class);
                startActivity(login_Intent);
            }
        });

        // Links this variable with the front end variable Contact_Button
        m_contactbutton = (Button)findViewById(R.id.Contact_Button);

        // Function that checks the contact us button
        m_contactbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent contact_Intent = new Intent(SettingActivity.this, ContactActivity.class);
                startActivity(contact_Intent);
            }
        });


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);

        bottomNavigationView.setSelectedItemId(R.id.profile_nav);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {

                    case R.id.search_nav:
                        Intent search_Intent = new Intent(SettingActivity.this, SearchActivity.class);
                        startActivity(search_Intent);
                        break;

                    case R.id.post_nav:
                        Intent post_Intent = new Intent(SettingActivity.this, PostActivity.class);
                        startActivity(post_Intent);
                        break;

                    case R.id.favorite_nav:
                        Intent favorite_Intent = new Intent(SettingActivity.this, FavoriteActivity.class);
                        startActivity(favorite_Intent);
                        break;

                    case R.id.home_nav:
                        Intent home_Intent = new Intent(SettingActivity.this, HomePageActivity.class);
                        startActivity(home_Intent);
                        break;

                    case R.id.profile_nav:
                        Intent profile_Intent = new Intent(SettingActivity.this, ProfileActivity.class);
                        startActivity(profile_Intent);
                        break;
                }


                return false;
            }
        });
    }
}
