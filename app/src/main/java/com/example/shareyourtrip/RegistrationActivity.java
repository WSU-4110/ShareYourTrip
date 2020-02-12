package com.example.shareyourtrip;

import android.content.Context;
import android.content.DialogInterface;
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

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class RegistrationActivity extends AppCompatActivity {

    EditText txt_FirstName;
    EditText txt_LastName;
    EditText txt_Email;
    EditText txt_Username;
    EditText txt_Password;
    EditText txt_ConfirmPassword;
    Button btn_Register;

    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }

    public static void alertDisplay(Context context, String msg, boolean success){
        AlertDialog alertDialog = new AlertDialog.Builder(context).create();
        if(success)
            alertDialog.setTitle("Successful Registration");
        else
            alertDialog.setTitle("Unsuccessful Registration");
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
        setContentView(R.layout.activity_main);

        txt_FirstName = (EditText)findViewById(R.id.txtFirstName);
        txt_LastName = (EditText)findViewById(R.id.txtLasttName);
        txt_Password = (EditText)findViewById(R.id.txtPassword);
        txt_Username = (EditText)findViewById(R.id.txtUsername);
        txt_Email = (EditText)findViewById(R.id.txtEmail);
        txt_ConfirmPassword = (EditText)findViewById(R.id.txtConfirmPassword);
        btn_Register = (Button) findViewById(R.id.btnRegister);

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
                if(txt_Email.getText().toString().equals("") || txt_Username.getText().toString().equals("") || txt_Password.getText().toString().equals("") || txt_FirstName.getText().toString().equals("")|| txt_LastName.getText().toString().equals("")){
                    alertDisplay(RegistrationActivity.this,"Some information is missing!",false);
                }
                if(!isValidEmail(txt_Email.getText())){
                    alertDisplay(RegistrationActivity.this,"Invalid Email address!",false);
                }
                else{
                    try {
                        /*
                        SQLiteDatabase sytdatabase = openOrCreateDatabase("shareyourtripdb", MODE_PRIVATE, null);
                        sytdatabase.execSQL("CREATE TABLE IF NOT EXISTS USERS(\n" +
                                "firstname VARCHAR (50) NOT NULL,\n" +
                                "lastname VARCHAR (50) NOT NULL,\n" +
                                "email VARCHAR (50) UNIQUE NOT NULL,\n" +
                                "username VARCHAR (50) UNIQUE NOT NULL,\n" +
                                "user_password VARCHAR (50) NOT NULL,\n" +
                                "isbanned INTEGER,\n" +
                                "PRIMARY KEY (username)\n" +
                                ");");
                        sytdatabase.execSQL("INSERT INTO USERS VALUES('" + txt_FirstName.getText() + "','" + txt_LastName.getText() + "','" + txt_Email.getText() + "','" + txt_Username.getText() + "','" + txt_Password.getText() + "','0')");
                         */
                        SYTDatabaseHandler dbHander = SYTDatabaseHandler.getInstance(getApplicationContext());
                        dbHander.insertUser(newUser);
                        alertDisplay(RegistrationActivity.this,"Congratulations! You are officially a ShareYourTrip user!",true);
                    }
                    catch (SQLiteCantOpenDatabaseException ex){
                        alertDisplay(RegistrationActivity.this,"Cannot open database!",false);
                    }
                    catch (SQLiteConstraintException ex){
                        if(ex.getMessage().contains("username"))
                            alertDisplay(RegistrationActivity.this,"Entered username is already existing!",false);
                        if(ex.getMessage().contains("email"))
                            alertDisplay(RegistrationActivity.this,"Entered email is already existing!",false);
                    }
                    catch (SQLiteException ex){
                        alertDisplay(RegistrationActivity.this,"A database exception happened! Try again later!",false);
                    }
                }
            }
        });

    }
}
