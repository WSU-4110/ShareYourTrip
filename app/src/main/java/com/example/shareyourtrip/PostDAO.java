package com.example.shareyourtrip;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import com.google.firebase.FirebaseApiNotAvailableException;
import com.google.firebase.auth.FirebaseAuth;

import java.sql.Connection;
/*import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
*/
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PostDAO extends SQLiteOpenHelper {
   /* private static final long serialVersionUID = 1L;
    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
*/
    private static final String dbname = "ShareYourTrip.db";
    private FirebaseAuth auth;


    /*public PostDAO(Context context) {
        PostDAO(context, dbname, factory, version)
    }*/

    public PostDAO(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, dbname, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        auth = FirebaseAuth.getInstance();
        String sql = "create table if not exists post" +
                "(id integer primary key autoincrement,"+
                "city text NOT NULL,"+
                "state text NOT NULL,"+
                "category text NOT NULL,"+
                "title text NOT NULL,"+
                "description text not null,"+
                "user text not null);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists post");
        onCreate(db);
    }

    public boolean insert(Post post) throws SQLException {
        SQLiteDatabase db = this.getWritableDatabase(); //connecting to the current database

        ContentValues cv = new ContentValues();
        cv.put("city", post.getCity());
        cv.put("state", post.getState());
        cv.put("category", post.getCategory());
        cv.put("title", post.getTitle());
        cv.put("description", post.getDescription());
        cv.put("user", auth.getCurrentUser().getEmail());

        long result = db.insert("post", null, cv);

        if(result==-1){
            return false;
        }else{
            return true;
        }
/*
        String sql = "insert into  post(city,state,category,title,description) values (?, ?, ?, ?, ?)";
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, post.getCity());
        preparedStatement.setString(2, post.getState());
        preparedStatement.setString(3, post.getCategory());
        preparedStatement.setString(4, post.getTitle());
        preparedStatement.setString(5, post.getDescription());

//		preparedStatement.executeUpdate();

        boolean rowInserted = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
//        disconnect();
        return rowInserted;
    }

    public boolean delete(int id) throws SQLException {
        String sql = "DELETE FROM post WHERE id = ?";
        connect_func();

        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setInt(1, id);

        boolean rowDeleted = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
//        disconnect();
        return rowDeleted;*/
    }

    public Post getPost(String query, String[] col) throws SQLiteException {
        SQLiteDatabase db = this.getReadableDatabase(); //connecting to the current database
        Post post = null;
        final Cursor cursor = db.rawQuery(query, col);

        if (cursor != null) {
            int id = cursor.getInt(0);
            String city = cursor.getString(1);
            String state = cursor.getString(2);
            String category = cursor.getString(3);
            String title = cursor.getString(4);
            String description = cursor.getString(5);
            String user = cursor.getString(6);

            post = new Post(city, state, category, title, description);
        }

        return post;

        /*
        connect_func();

        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, val1);
        preparedStatement.setString(2, val2);

        ResultSet resultSet = preparedStatement.executeQuery();
*/
    }

    public List<Post> listAllPost(String query, String[] col) throws SQLiteException {

        List<Post> listPost = new ArrayList<Post>();
        SQLiteDatabase db = this.getReadableDatabase(); //connecting to the current database
        final Cursor cursor = db.rawQuery(query, col);
        int id = -1;
        String city = null;
        String state = null;
        String category = null;
        String title = null;
        String description = null;
        String user=null;

        Post post = null;

        do {
            id = cursor.getInt(0);
            city = cursor.getString(1);
            state = cursor.getString(2);
            category = cursor.getString(3);
            title = cursor.getString(4);
            description = cursor.getString(5);
            user = cursor.getString(6);

            post = new Post(city, state, category, title, description);
            listPost.add(post);

        }while (!cursor.moveToNext()); //exit loop if the cursor is already past the last entry in the result set.

        return listPost;/*

        connect_func();
        statement =  (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            int id = cursor.getInt(0);
            String city = cursor.getString(1);
            String state = cursor.getString(2);
            String category = cursor.getString(3);
            String title = cursor.getString(4);
            String description = cursor.getString(5);
            String user = cursor.getString(6);

            Post post = new Post(city, state, category, title, description);
            listPost.add(post);
        }
        resultSet.close();
        statement.close();
        disconnect();*/
    }

   /* protected void disconnect() throws SQLException {
        if (connect != null && !connect.isClosed()) {
            connect.close();
        }
    }


      @see HttpServlet#HttpServlet()

   protected void connect_func() throws SQLException {
        if (connect == null || connect.isClosed()) {
            try {
                Class.forName("com.mysql.jdbc.Driver"); //<------------------------------------- need to change to our database driver
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            connect = (Connection) DriverManager
                    .getConnection("jdbc:mysql://127.0.0.1:3306/projectdb?"  //<------------------------------------- need to change to our database connector
                            + "user=john&password=pass1234");
            System.out.println(connect);
        }
    }*/



    /*
     @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }*/



    //this is moved to onCreate
    /*public void initialize() throws SQLException {

        connect_func();

        String sql = "drop database projectdb";

        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.executeUpdate();
        preparedStatement.close();

        sql = "create database projectdb";
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.executeUpdate();
        preparedStatement.close();

        String sql = "use projectdb"; //<----------------------use database name here
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.executeUpdate();
        preparedStatement.close();

        sql = "create table post" +
                "(id integer not null auto_increment,"+
                "city varchar(20) NOT NULL,"+
                "state varchar(20) NOT NULL,"+
                "category varchar(20) NOT NULL,"+
                "title varchar(20) NOT NULL,"+
                "description varchar(150),"+
                "r_user varchar(20),"+

                "r_user reference User (username),"+
                "primary key (id))";
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.executeUpdate();
        preparedStatement.close();


        Post newPost = new Post("Detroit", "Michigan", "Food", "Urban Ramen" ,"Best ramen place in all of Detroit!");
        insert(newPost);

        //boolean rowUpdated = preparedStatement.executeUpdate() > 0;

    }*/

}
