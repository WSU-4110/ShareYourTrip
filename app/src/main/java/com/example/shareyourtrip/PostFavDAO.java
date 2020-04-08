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
                " (id integer not null, "+
                "user text not null, " +    //for storing user email
                "primary key(id, user))";
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

    public boolean insert(Post post) throws SQLiteException  {
        SQLiteDatabase db = this.getWritableDatabase(); //connecting to the current database

        ContentValues cv = new ContentValues();
        cv.put("city", post.getCity());
        cv.put("state", post.getState());
        cv.put("category", post.getCategory());
        cv.put("title", post.getTitle());
        cv.put("description", post.getDescription());
        cv.put("user", post.getUser());

        //long result = db.insert("post", null, cv);

        //cannot simplify the following expression because this function returns a Boolean and not Long
        if(db.insert(DBTABLE, null, cv)!=-1){
            return true;
        }
        return false;
    }

    public boolean delete(String clause, String[] args){
        SQLiteDatabase db = this.getWritableDatabase(); //connecting to the current database

        if(0 < db.delete(DBNAME, clause, args)){ //checking that the number of rows deleted is greater than 0
            return true;
        }
        return false;
    }

    public boolean update(String id){
        SQLiteDatabase db = this.getWritableDatabase(); //connecting to the current database

        if(0 < db.delete(DBNAME, "id = ?", new String [] {id})){ //checking that the number of rows deleted is greater than 0
            return true;
        }
        return false;
    }

    public Post getPost(Cursor cursor) throws SQLiteException {
        /*SQLiteDatabase db = this.getReadableDatabase(); //connecting to the current database
        Post post = new Post();
        final Cursor cursor = db.rawQuery(query, col);
*/
        Post post = new Post();
        String user = FirebaseAuth.getInstance().getCurrentUser().getEmail();

        if (cursor != null && user.equals(cursor.getString(6))) {

            post.setId(cursor.getString(0));
            post.setCity(cursor.getString(1));
            post.setState(cursor.getString(2));
            post.setCategory(cursor.getString(3));
            post.setTitle(cursor.getString(4));
            post.setDescription(cursor.getString(5));
            post.setUser(cursor.getString(6));
            post.setUp(cursor.getString(7));
            post.setDown(cursor.getString(8));
            cursor.close();
        }
        return post;
    }

    public List<Post> listAllPost(String query, String[] col) throws SQLiteException {

        List<Post> listPost = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase(); //connecting to the current database
        final Cursor cursor = db.rawQuery(query, col);
        /*String id;
        String city;
        String state;
        String category;
        String title;
        String description;
        String user;
        String up;
        String down;

        do {
            id = cursor.getString(0);
            city = cursor.getString(1); //need to start one after the id column. if 0->1; if 1->2
            state = cursor.getString(2);
            category = cursor.getString(3);
            title = cursor.getString(4);
            description = cursor.getString(5);
            user = cursor.getString(6);
            up = cursor.getString(7);
            down = cursor.getString(8);

            Post post = new Post(id, city, state, category, title, description, user, up, down);*/

        do{
            listPost.add(getPost(cursor));

        }while (!cursor.moveToNext()); //exit loop if the cursor is already past the last entry in the result set.

        cursor.close();

        return listPost;
    }

}
