package com.example.shareyourtrip;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import static com.example.shareyourtrip.RegistrationActivity.alertDisplay;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    // Variables
    EditText user_email;
    EditText user_password;
    Button m_loginbutton;
    Button m_registerbutton;
    TextView m_forgotpassword;

    public static void alertDisplay(Context context, String msg, boolean success){
        AlertDialog alertDialog = new AlertDialog.Builder(context).create();
        if(success)
            alertDialog.setTitle("Successful login");
        else
            alertDialog.setTitle("Unsuccessful login");
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
        setContentView(R.layout.activity_login);
        user_email = (EditText)findViewById(R.id.emailaddress);
        user_password = (EditText)findViewById(R.id.password);
        m_loginbutton = (Button)findViewById(R.id.Login_Button);
        m_registerbutton = (Button)findViewById(R.id.Register_Button);

        // Login button
        m_loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = user_email.getText().toString();
                String password = user_password.getText().toString();
                if(user_email.getText().toString().equals("") || user_password.getText().toString().equals("")){
                    alertDisplay(LoginActivity.this,"Some information is missing!",false);
                }
                else {
                    mAuth = FirebaseAuth.getInstance();
                    mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Intent homepage_Intent = new Intent(LoginActivity.this, HomePageActivity.class);
                                startActivity(homepage_Intent);
                                user_email.getText().clear();
                                user_password.getText().clear();
                            } else {
                                alertDisplay(LoginActivity.this, task.getException().getMessage(), false);
                            }
                        }
                    });
                }
            }
        });


        // Register account
        m_registerbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent register_Intent = new Intent(LoginActivity.this, RegistrationActivity.class);
                startActivity(register_Intent);
                user_email.getText().clear();
                user_password.getText().clear();
            }
        });
    }
}
