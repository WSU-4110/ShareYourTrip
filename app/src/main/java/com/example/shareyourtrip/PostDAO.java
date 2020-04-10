package com.example.shareyourtrip;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class PostDAO extends SQLiteOpenHelper {
    private static final String DBNAME = "ShareYourTrip.db";
    private static final String DBTABLE = "post";
    private static final int DB_VERSION =12;
    private String sql;


    public PostDAO(Context context) {
        super(context, DBNAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) throws SQLiteException  {
        sql = "create table if not exists " + DBTABLE +
                " (id integer primary key autoincrement, "+
                "city text NOT NULL, "+
                "state text NOT NULL, "+
                "category text NOT NULL, "+
                "title text NOT NULL, "+
                "description text not null, "+
                "user text not null," +
                "date text not null, " +
                "up integer default 0, " +
                "down integer default 0);"; //<-------------------------------------------- NEED TO ADD TABLE ATTRIBUTE FOR TIME
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) throws SQLiteException {
        db.execSQL("drop table if exists "+ DBTABLE);
        onCreate(db);
    }

    public boolean insert(Post post) throws SQLiteException  {
        SQLiteDatabase db = this.getWritableDatabase(); //connecting to the current database

        ContentValues cv = new ContentValues();
        cv.put("city", post.getCity());
        cv.put("state", post.getState());
        cv.put("category", post.getCategory());
        cv.put("title", post.getTitle());
        cv.put("description", post.getDescription());
        cv.put("user", post.getUser());
        cv.put("date", post.getDate());

        long result = db.insert(DBTABLE, null, cv);

        if(result<=0){
            return false;
        }else{
            return true;
        }
    }

    public boolean insert(String city, String state, String category, String title, String description, String user, String date) {
        Post post = new Post(city, state, category, title, description, user, date, "", "");
        if(post.getCategory().replaceAll("\\s", "").equals("Allcategories")||
        post.getCity().replaceAll("\\s", "").isEmpty()|| post.getDate().replaceAll("\\s", "").isEmpty()||
        post.getDescription().replaceAll("\\s", "").isEmpty()|| post.getState().replaceAll("\\s", "").isEmpty()||
        post.getTitle().replaceAll("\\s", "").isEmpty()|| post.getUser().replaceAll("\\s", "").isEmpty()) {
            return false;
        }else{ return this.insert(post);}//<--------------------- NEED TO INSERT DATE TO STRING
    }

    public Post getPost(String query, String[] col) throws SQLiteException {
        SQLiteDatabase db = this.getReadableDatabase(); //connecting to the current database
        Post post = new Post();
        final Cursor cursor = db.rawQuery(query, col);

        if (cursor != null) {

            post.setCity(cursor.getString(1));
            post.setState(cursor.getString(2));
            post.setCategory(cursor.getString(3));
            post.setTitle(cursor.getString(4));
            post.setDescription(cursor.getString(5));
            post.setUser(cursor.getString(6));
            post.setDate(cursor.getString(7));//<------------------------------------- NEED TO GET DATE FROM COLUMN AND CONVERT TO STRING
            post.setUp(cursor.getString(8));
            post.setDown(cursor.getString(9));
        }

        cursor.close();

        return post;
    }

    public List<Post> listAllPost(String query, String[] col) throws SQLiteException {

        List<Post> listPost = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase(); //connecting to the current database
        final Cursor cursor = db.rawQuery(query, col);
        String id;
        String city;
        String state;
        String category;
        String title;
        String description;
        String user;
        String date;
        String up;
        String down;

        //Post post;
        int cc = cursor.getColumnCount();
        int count = cursor.getCount();
        if(count>0) {
            cursor.moveToFirst();
            do {
                id = cursor.getString(0); //need to start one after the id column. if 0->1; if 1->2
                city = cursor.getString(1); //need to start one after the id column. if 0->1; if 1->2
                state = cursor.getString(2);
                category = cursor.getString(3);
                title = cursor.getString(4);
                description = cursor.getString(5);
                user = cursor.getString(6);
                date = cursor.getString(7);
                up = cursor.getString(8);
                down = cursor.getString(9);

                Post post = new Post(id, city, state, category, title, description, user, date, up, down);
                listPost.add(post);

            } while (cursor.moveToNext()); //exit loop if the cursor is already past the last entry in the result set.
        }

        cursor.close();

        return listPost;
    }

}
