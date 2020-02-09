package com.example.shareyourtrip;

import android.content.Context;
import android.content.DialogInterface;
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

    public static void alertDisplay(Context context, String msg){
        AlertDialog alertDialog = new AlertDialog.Builder(context).create();
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
                if(!txt_Password.getText().toString().equals(txt_ConfirmPassword.getText().toString())){
                    alertDisplay(RegistrationActivity.this,"Entered password and confrim password do not match!");
                }
                if(txt_Email.getText().toString().equals("") || txt_Username.getText().toString().equals("") || txt_Password.getText().toString().equals("") || txt_FirstName.getText().toString().equals("")|| txt_LastName.getText().toString().equals("")){
                    alertDisplay(RegistrationActivity.this,"Some information is missing!");
                }
                if(!isValidEmail(txt_Email.getText())){
                    alertDisplay(RegistrationActivity.this,"Invalid Email address!");
                }
                else{
                    //new AlertDialog.Builder(getApplicationContext()).setTitle("Unsuccessful Registration").setMessage("Unmatched password and confrim password!").show();

                }
            }
        });

    }
}
