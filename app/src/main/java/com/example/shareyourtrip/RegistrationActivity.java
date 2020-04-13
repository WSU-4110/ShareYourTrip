package com.example.shareyourtrip;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteCantOpenDatabaseException;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.style.RelativeSizeSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.util.Patterns;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.io.File;

public class RegistrationActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    static String alertTitle;

    EditText txt_FirstName;
    EditText txt_LastName;
    EditText txt_Email;
    EditText txt_Username;
    EditText txt_Password;
    EditText txt_ConfirmPassword;
    Button btn_Register;

    public static boolean isValidEmail(CharSequence target) {
        return ( !isEmpty(target.toString()) && isValidemailPattern(target.toString()));
    }

    public static boolean isValidemailPattern(String pattern){
        if(pattern.contains("@") && pattern.contains("."))
            return true;
        else
            return false;
    }

    public static boolean isEmpty(String string){
        return string.equals("");
    }

    public static String SetRegistrationPriority(int priority){
        if(priority < 10)
            return "early registration";
        else if(priority >= 10 && priority<20)
            return "mid-day registration";
        else
            return "mid-day registration";
    }

    public static boolean ckeckAlertTitle(String title){
        if(alertTitle.equals(title))
            return true;
        else
            return false;
    }

    public static boolean isPasswordSixChar(String password){
        if(password.length()>=6)
            return true;
        else
            return false;
    }

    public static String alertDisplay(Context context, String msg, boolean success){
        AlertDialog alertDialog = new AlertDialog.Builder(context).create();
        String title;
        if(success) {
            title = "Successful Registration";
        }
        else {
            title = "Unsuccessful Registration";
        }
        ckeckAlertTitle(title);
        alertDialog.setTitle(title);
        alertDialog.setMessage(msg);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alertDialog.show();

        return title;
    }

    public boolean initializeFields()
    {
        SetRegistrationPriority(10);

        txt_FirstName = (EditText)findViewById(R.id.txtFirstName);
        txt_LastName = (EditText)findViewById(R.id.txtLasttName);
        txt_Password = (EditText)findViewById(R.id.txtPassword);
        txt_Username = (EditText)findViewById(R.id.txtUsername);
        txt_Email = (EditText)findViewById(R.id.txtEmail);
        txt_ConfirmPassword = (EditText)findViewById(R.id.txtConfirmPassword);
        btn_Register = (Button) findViewById(R.id.btnRegister);

        if(txt_FirstName!=null && txt_LastName!=null && txt_Password!=null && txt_Username!=null && txt_Email!=null && txt_ConfirmPassword!=null && btn_Register!=null)
            return true;
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeFields();

        if(!isPasswordSixChar(txt_Password.getText().toString()))
            finish();

        mAuth = FirebaseAuth.getInstance();

        if(mAuth.getCurrentUser() != null && mAuth.getCurrentUser().getEmail().equals(txt_Email.getText().toString()) ){
            Intent login_intent = new Intent(RegistrationActivity.this, LoginActivity.class);
            startActivity(login_intent);
            finish();
        }

        btn_Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User newUser = new User(txt_FirstName.getText().toString(),
                        txt_LastName.getText().toString(),
                        txt_Email.getText().toString(),
                        txt_Username.getText().toString(),
                        txt_Password.getText().toString(),
                        txt_ConfirmPassword.getText().toString(),
                        false);


                if(!txt_Password.getText().toString().equals(txt_ConfirmPassword.getText().toString())){
                    alertDisplay(RegistrationActivity.this,"Entered password and confrim password do not match!",false);
                }
                else if(txt_Email.getText().toString().equals("") || txt_Username.getText().toString().equals("") || txt_Password.getText().toString().equals("") || txt_FirstName.getText().toString().equals("")|| txt_LastName.getText().toString().equals("")){
                    alertDisplay(RegistrationActivity.this,"Some information is missing!",false);
                }
                else if(!isValidEmail(txt_Email.getText())){
                    alertDisplay(RegistrationActivity.this,"Invalid Email address!",false);
                }
               else{
                    mAuth.createUserWithEmailAndPassword(newUser.getEmailAddress(),newUser.getPassword()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                alertDisplay(RegistrationActivity.this,"Congratulations! You are officially a ShareYourTrip user!",true);
                                Intent login_intent = new Intent(RegistrationActivity.this, LoginActivity.class);
                                startActivity(login_intent);
                            }
                            else{
                                alertDisplay(RegistrationActivity.this,task.getException().getMessage(),false);
                            }
                        }
                    });

                }
            }
        });

    }
}
