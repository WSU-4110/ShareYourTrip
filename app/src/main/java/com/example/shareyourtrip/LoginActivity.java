package com.example.shareyourtrip;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    // Variables
    EditText user_username;
    EditText user_password;
    Button m_loginbutton;
    Button m_registerbutton;
    TextView m_forgotpassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        user_username = (EditText)findViewById(R.id.username);
        user_password = (EditText)findViewById(R.id.password);
        m_loginbutton = (Button)findViewById(R.id.Login_Button);
        m_registerbutton = (Button)findViewById(R.id.Register_Button);



        // Register account
        m_registerbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent register_Intent = new Intent(LoginActivity.this, RegistrationActivity.class);
                startActivity(register_Intent);
                user_username.getText().clear();
                user_password.getText().clear();
            }
        });
    }
}
