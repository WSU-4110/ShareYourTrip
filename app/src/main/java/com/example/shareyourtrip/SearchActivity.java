package com.example.shareyourtrip;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

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

    private List<Post> postsList = new ArrayList<Post>();
    private RecyclerView recyclerView;
    private PostAdapter postAdapter;

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

        final PostDAO postDAO = new PostDAO(this);

        recyclerView = (RecyclerView) findViewById(R.id.search_rv);
        postAdapter = new PostAdapter(postsList);

        RecyclerView.LayoutManager postLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(postLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(postAdapter);

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

                            postsList.clear();

                            String category = spinner_category.getSelectedItem().toString();

                            StringBuilder stringBuilder = new StringBuilder();
                            stringBuilder.append("select * from post where ");
                            stringBuilder.append("city='");
                            stringBuilder.append(txt_City.getText().toString());
                            stringBuilder.append("' and state='");
                            stringBuilder.append(txt_State.getText().toString());
                            if(!category.equals("All categories"))
                            {
                                stringBuilder.append("' and category='");
                                stringBuilder.append(category);
                            }
                            stringBuilder.append("' ");
                            stringBuilder.append("order by id desc;");

                            String[] cols = {"city", "state"};

                            try {
                                List<Post> listPost = postDAO.listAllPost(stringBuilder.toString(), null);
                                if(listPost.size()==0){
                                    alertDisplay(SearchActivity.this, "There is no post for the given criteria!", false);
                                    //Toast.makeText(SearchActivity.this,"There are no posts with the given criteria!",Toast.LENGTH_LONG).show();;
                                }
                                postsList.addAll(listPost);
                                postAdapter.notifyDataSetChanged();
                            }
                            catch (SQLiteException e){
                                Toast.makeText(SearchActivity.this,"There is a database problem!"+e.getMessage(),Toast.LENGTH_LONG).show();;
                            }
                            //alertDisplay(SearchActivity.this, "Searhing for " + CategoryArray[position] + " in " + txt_City.getText().toString() + ", " + txt_State.getText().toString(), true);
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
