package com.example.shareyourtrip;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import java.sql.SQLException;
import java.util.List;

public interface DatabaseHelper{
    String DBNAME = "ShareYourTrip.db";
    int DB_VERSION =9;

    void initialize(SQLiteDatabase db);

    boolean insert(Post post) throws SQLiteException;

    // boolean insert(String city, String state, String category, String title, String description, String user, String up, String down) throws SQLException;

    boolean delete(String clause, String[] args);

    boolean update(String[] col, String[] val, String clause, String[] args);

    Post getPost(Cursor cursor) throws SQLiteException;

    List<Post> listAllPost(String query, String[] col) throws SQLiteException;
}