package com.example.shareyourtrip;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity {
    //Used for Testing
    public PageActivity selectPageActivity(String page){
        if(page == "Favorite"){
            return new FavoriteActivity();
        }else if(page == "Home"){
            return new HomePageActivity();
        }else{
            return null;
        }
    }
}
