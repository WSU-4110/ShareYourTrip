package com.example.shareyourtrip;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

public class PostDAO extends SQLiteOpenHelper implements DatabaseHelper{
    private static final String DBTABLE = "post";
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
                "user text not null, " +    //for storing user email
                "up integer default 0," +             //for storing positive/up rating
                "down integer default 0);";           //for storing negative/down rating
        db.execSQL(sql);

        initialize();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) throws SQLiteException {
        db.execSQL("drop table if exists "+DBTABLE);
        onCreate(db);
    }


    public void initialize(){
        Post post;
        post = new Post("Detroit", "MI", "food", "Urban Ramen",
                "Best ramen in metro detroit! Homemade noodles and house broth, " +
                        "delicious and comforting for the cold michigan weather.",
                "", "0","0");
        insert(post);

        post = new Post("Detroit", "MI", "beverage", "Tao & Mai",
                "Tao & Mai is an adorable Boba shop in the heart of midtown right by Sy Thai. " +
                        "Great variety of flavors, drink options and toppings. Highly recommend " +
                        "Honeydew milk tea with extra pearls... you will not be dissapointed",
                "", "0","0");
        insert(post);


        post = new Post("Detroit", "MI", "food", "Wasabi",
                "This place is okay... not the best asian food in Detroit, but if you " +
                        "can't choose between Korean food and Sushi, this is the right place for you.",
                "", "0","0");
        insert(post);

        post = new Post("Detroit", "MI", "food", "Ima",
                "Ima is an Asian fusion restaurant with various locations throughout Detroit. " +
                        "It's hip, the food is tasty (aside from the curry, it is milder and not as spicy) " +
                        "and the drinks menu offers a nice variety of Asian beers and Japanese sakes.",
                "", "0","0");
        insert(post);

        post = new Post("Detroit", "MI", "food", "Izakaya Katsu",
                "OMG this place... everything about it is amazing! Also found as " +
                        "BASH Original Izakaya on google search results, this place is a truly " +
                        "hidden gem in midtown Detroit. With traditional floor seating, heated mats " +
                        "and private wooden booth seating, it feels like a little piece of Japan has " +
                        "been presented to Michigan residents. Delicious small portions of food and " +
                        "an extensive classic sake menu, this is the closest to Japanese 'bar food' " +
                        "one can get. 10/10 definitely would recommend.",
                "", "0","0");
        insert(post);
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
        cv.put("up", post.getUp());
        cv.put("down", post.getDown());

        //long result = db.insert(DBTABLE, null, cv);

        //cannot simplify the following expression because this function returns a Boolean and not Long
        if(db.insert(DBTABLE, null, cv)!=-1){
            return true;
        }
        return false;
//        return db.insert(DBTABLE, null, cv);
    }

    public boolean insert(String city, String state, String category, String title, String description, String user, String up, String down) {
        return this.insert(new Post(city, state, category, title, description, user, up, down));
    }

    public boolean delete(String clause, String[] args){
        SQLiteDatabase db = this.getWritableDatabase(); //connecting to the current database

        if(0 < db.delete(DBNAME, clause, args)){ //checking that the number of rows deleted is greater than 0
            return true;
        }
        return false;
    }

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

    public Post getPost(Cursor cursor) throws SQLiteException {
        /*SQLiteDatabase db = this.getReadableDatabase(); //connecting to the current database
        Post post = new Post();
        final Cursor cursor = db.rawQuery(query, col);
*/
        Post post = new Post();
        if (cursor != null) {

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
