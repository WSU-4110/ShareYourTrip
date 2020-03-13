package com.example.shareyourtrip;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SearchActivity extends AppCompatActivity {

    // Array of search categories
    String CategoryArray[] = {"All categories", "Entertainment", "Museum", "Park", "Restaurant"};

    // Spinner variable
    Spinner spinner_category;

    // Array adapter for spiiner
    ArrayAdapter<String> arrayadapter;

    // Search button variable
    Button btn_searchbutton;

    // Edit text variables
    EditText txt_City;
    EditText txt_State;

    // Function to give alert
    public static void alertDisplay(Context context, String msg, boolean success){
        AlertDialog alertDialog = new AlertDialog.Builder(context).create();
        if(success)
            alertDialog.setTitle("Successful Search");
        else
            alertDialog.setTitle("Unsuccessful Search");
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
        setContentView(R.layout.activity_search);

        // Bottom navigation code
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setSelectedItemId(R.id.search_nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {

                    case R.id.home_nav:
                        Intent home_Intent = new Intent(SearchActivity.this, HomePageActivity.class);
                        startActivity(home_Intent);
                        break;

                    case R.id.post_nav:
                        Intent post_Intent = new Intent(SearchActivity.this, PostActivity.class);
                        startActivity(post_Intent);
                        break;

                    case R.id.favorite_nav:
                        Intent favorite_Intent = new Intent(SearchActivity.this, FavoriteActivity.class);
                        startActivity(favorite_Intent);
                        break;

                    case R.id.profile_nav:
                        Intent profile_Intent = new Intent(SearchActivity.this, ProfileActivity.class);
                        startActivity(profile_Intent);
                        break;
                }

                return false;
            }
        });

        // Category list code
        txt_City = (EditText)findViewById(R.id.City);
        txt_State = (EditText)findViewById(R.id.State);
        spinner_category=(Spinner)findViewById(R.id.Category_Spinner);
        arrayadapter= new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, CategoryArray);
        spinner_category.setAdapter(arrayadapter);
        btn_searchbutton= (Button)findViewById(R.id.Search_Button);

        spinner_category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, final int position, long id) {

                // Search button on selected listener
                btn_searchbutton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {



                        if(txt_City.getText().toString().equals("") || txt_State.getText().toString().equals("")){
                            alertDisplay(SearchActivity.this,"Some information is missing!",false);
                        }

                        else {
                            alertDisplay(SearchActivity.this, "Searhing for " + CategoryArray[position] + " in " + txt_City.getText().toString() + ", " + txt_State.getText().toString(), true);
                        }

                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
}
