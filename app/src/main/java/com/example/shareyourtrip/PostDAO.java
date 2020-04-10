package com.example.shareyourtrip;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class PostDAO extends SQLiteOpenHelper {
    private static final String DBNAME = "ShareYourTrip.db";
    private static final String DBTABLE = "post";
    private static final int DB_VERSION =15;
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

        initialize(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) throws SQLiteException {
        db.execSQL("drop table if exists "+ DBTABLE);
        onCreate(db);
    }

    public void initialize(SQLiteDatabase db) {
        String query = "insert into " +
                DBTABLE +
                " (city, state, category, title, description, user, date, up, down) values";

        String user = "lc@a.com";
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String str_date = date.format(new Date());

        String sql = query + "('Detroit', 'MI', 'food', 'Urban Ramen'," +
                "'Best ramen in metro detroit! Homemade noodles and house broth, " +
                "delicious and comforting for the cold michigan weather.', '" +
                user + "', '" + str_date + "', '0', '0');";
        db.execSQL(sql);

        sql = query + "('Detroit', 'MI', 'food', 'Tao & Mai'," +
                "'Tao & Mai is an adorable Boba shop in the heart of midtown right by Sy Thai. '" +
                "'Great variety of flavors, drink options and toppings. Highly recommend '"+
                "'Honeydew milk tea with extra pearls... you will not be dissapointed.', '" +
                user + "', '" + str_date + "', '0', '0');";
        db.execSQL(sql);

        sql = query + "('Detroit', 'MI', 'food', 'Wasabi'," +
                "'This place is okay... not the best asian food in " +
                "Detroit, but if you cannot choose between Korean food " +
                "and Sushi, this is the right place for you.', '" +
                user + "', '" + str_date + "', '0', '0');";
        db.execSQL(sql);

        sql = query + "('Detroit', 'MI', 'food', 'Ima'," +
                "'Ima is an Asian fusion restaurant with various locations " +
                "throughout Detroit. It is hip, the food is tasty (aside " +
                "from the curry, it is milder and not as spicy) and the " +
                "drinks menu offers a nice variety of Asian beers and Japanese sakes.', '" +
                user + "', '" + str_date + "', '0', '0');";
        db.execSQL(sql);

        sql = query + "('Detroit', 'MI', 'food', 'Izakaya Katsu'," +
                "'OMG this place... everything about it is amazing! " +
                "Also found as BASH Original Izakaya on google search " +
                "results, this place is a truly hidden gem in midtown " +
                "Detroit. With traditional floor seating, heated mats " +
                "and private wooden booth seating, it feels like a " +
                "little piece of Japan has been presented to Michigan " +
                "residents. Delicious small portions of food and an " +
                "extensive classic sake menu, this is the closest to " +
                "Japanese \"bar food\" one can get. 10/10 definitely " +
                "would recommend.', '" +
                user + "', '" + str_date + "', '0', '0');";
        db.execSQL(sql);
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

    public Post getPost(Cursor cursor) throws SQLiteException {
    //    public Post getPost(String query, String[] col) throws SQLiteException {
        /*SQLiteDatabase db = this.getReadableDatabase(); //connecting to the current database
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

        return post;*/

        Post post = new Post();
        if (cursor != null) {

            String id = cursor.getString(0); //need to start one after the id column. if 0->1; if 1->2
            String city = cursor.getString(1); //need to start one after the id column. if 0->1; if 1->2
            String state = cursor.getString(2);
            String category = cursor.getString(3);
            String title = cursor.getString(4);
            String description = cursor.getString(5);
            String user = cursor.getString(6);
            String date = cursor.getString(7);
            String up = cursor.getString(8);
            String down = cursor.getString(9);

            post.setId(id);
            post.setCity(city);
            post.setState(state);
            post.setCategory(category);
            post.setTitle(title);
            post.setDescription(description);
            post.setUser(user);
            post.setDate(date);
            post.setUp(up);
            post.setDown(down);

            /*post.setId(cursor.getString(0));
            post.setCity(cursor.getString(1));
            post.setState(cursor.getString(2));
            post.setCategory(cursor.getString(3));
            post.setTitle(cursor.getString(4));
            post.setDescription(cursor.getString(5));
            post.setUser(cursor.getString(6));
            post.setDate(cursor.getString(7));
            post.setUp(cursor.getString(8));
            post.setDown(cursor.getString(9));*/
            //cursor.close();
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
        String date;
        String up;
        String down;

        //Post post;
        int cc = cursor.getColumnCount();*/
        int count = cursor.getCount();
        if(count>0) {
            cursor.moveToFirst();
            do {
                /*id = cursor.getString(0); //need to start one after the id column. if 0->1; if 1->2
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
                listPost.add(post);*/
                listPost.add(getPost(cursor));

            } while (cursor.moveToNext()); //exit loop if the cursor is already past the last entry in the result set.
        }

        cursor.close();

        return listPost;
    }

    //to update a post in the database
    public boolean update(String[] col, String[] val, String clause, String[] args){
        SQLiteDatabase db = this.getWritableDatabase(); //connecting to the current database
        ContentValues content = new ContentValues();

        //loop to add onto the content set for update mapping
        if(col.length!= val.length)
            return false;

        for(int i=0; i <col.length; i++){
            content.put(col[i], val[i]);
        }

        if(0 < db.update(DBTABLE, content, clause, args)){ //checking that the number of rows deleted is greater than 0
            return true;
        }
        return false;
    }

    //to delete a post from the database
    public boolean delete(String clause, String[] args){
        SQLiteDatabase db = this.getWritableDatabase(); //connecting to the current database

        if(0 < db.delete(DBNAME, clause, args)){ //checking that the number of rows deleted is greater than 0
            return true;
        }
        return false;
    }

}
