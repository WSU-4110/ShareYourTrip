package com.example.shareyourtrip;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public abstract class PostFavDAO extends SQLiteOpenHelper implements DatabaseHelper{
    private static final String DBTABLE = "postFav";
    private String sql;


    public PostFavDAO(Context context) {
        super(context, DBNAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) throws SQLiteException  {
        sql = "create table if not exists " + DBTABLE +
                " (post integer not null, "+
                "user text not null, " +    //for storing user email
                "primary key(post, user), " +
                "foreign key (post) references post(id));";
        db.execSQL(sql);

        initialize();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) throws SQLiteException {
        db.execSQL("drop table if exists "+DBTABLE);
        onCreate(db);
    }

    public void initialize(){

    }
/*
    public boolean insert(PostFav postFav) throws SQLiteException  {
        SQLiteDatabase db = this.getWritableDatabase(); //connecting to the current database

        ContentValues cv = new ContentValues();

        /*
        cv.put("post", postFav.getCity());
        cv.put("user", postFav.getState());

        //long result = db.insert("postFav", null, cv);*//*
        //cannot simplify the following expression because this function returns a Boolean and not Long
        if(db.insert(DBTABLE, null, cv)!=-1){
            return true;
        }
        return false;
    }*/

    public boolean delete(String clause, String[] args){
        SQLiteDatabase db = this.getWritableDatabase(); //connecting to the current database

        if(0 < db.delete(DBNAME, clause, args)){ //checking that the number of rows deleted is greater than 0
            return true;
        }
        return false;
    }

    public boolean update(String post){
        SQLiteDatabase db = this.getWritableDatabase(); //connecting to the current database

        if(0 < db.delete(DBNAME, "post = ?", new String [] {post})){ //checking that the number of rows deleted is greater than 0
            return true;
        }
        return false;
    }

    /* TODO: check the function; see how this will be used

    public PostFav getPost(Cursor cursor) throws SQLiteException {

//        PostFav postFav = new PostFav();
        String user = FirebaseAuth.getInstance().getCurrentUser().getEmail();

        if (cursor != null && user.equals(cursor.getString(6))) {

//            postFav.setPost(cursor.getString(0));
//            postFav.setUser(cursor.getString(1));
            cursor.close();
        }
        return postFav;
    }



    public List<PostFav> listAllPost(String query, String[] col) throws SQLiteException {

        List<PostFav> listPost = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase(); //connecting to the current database
        final Cursor cursor = db.rawQuery(query, col);

        do{
            listPost.add(getPost(cursor));

        }while (!cursor.moveToNext()); //exit loop if the cursor is already past the last entry in the result set.

        cursor.close();

        return listPost;
    }
*/
}