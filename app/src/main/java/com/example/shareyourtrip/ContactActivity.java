package com.example.shareyourtrip;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ContactActivity extends AppCompatActivity {

    private Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        final EditText your_subject     = (EditText) findViewById(R.id.subject);
        final EditText your_message     = (EditText) findViewById(R.id.message);

        Button email = (Button) findViewById(R.id.Send_Button);

        //Bottom Navigation
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setSelectedItemId(R.id.favorite_nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {

                    case R.id.search_nav:
                        Intent search_Intent = new Intent(ContactActivity.this, SearchActivity.class);
                        startActivity(search_Intent);
                        break;

                    case R.id.post_nav:
                        Intent post_Intent = new Intent(ContactActivity.this, PostActivity.class);
                        startActivity(post_Intent);
                        break;

                    case R.id.home_nav:
                        Intent home_Intent = new Intent(ContactActivity.this, HomePageActivity.class);
                        startActivity(home_Intent);
                        break;

                    case R.id.profile_nav:
                        Intent profile_Intent = new Intent(ContactActivity.this, ProfileActivity.class);
                        startActivity(profile_Intent);
                        break;
                }


                return false;
            }
        });


        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String name      = your_name.getText().toString();
                //String email     = your_email.getText().toString();
                String subject   = your_subject.getText().toString();
                String message   = your_message.getText().toString();

                //Requiring subject
                if (TextUtils.isEmpty(subject)){
                    your_subject.setError("Please enter a subject.");
                    your_subject.requestFocus();
                    return;
                }

                //Requiring message
                if (TextUtils.isEmpty(message)){
                    your_message.setError("Please enter a message.");
                    your_message.requestFocus();
                    return;
                }

                Intent sendEmail = new Intent(android.content.Intent.ACTION_SEND);

                //Initializing info with user's input
                sendEmail.setType("plain/text");
                sendEmail.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{"adsherman98@gmail.com"});
                sendEmail.putExtra(android.content.Intent.EXTRA_SUBJECT, subject);
                sendEmail.putExtra(android.content.Intent.EXTRA_TEXT,
                        "Message:"+'\n'+message);

                //Sends email
                startActivity(Intent.createChooser(sendEmail, "Send mail..."));
            }
        });
    }


    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    // validating email id
    private boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

}//End ContactActivity
