package com.example.shareyourtrip;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteCantOpenDatabaseException;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class SYTDatabaseHandler extends SQLiteOpenHelper {

    private static SYTDatabaseHandler instance = null;
    private Context context;
    private static final String DATABASE_NAME="shareyourtripdb";
    private ArrayList<String> tables = new ArrayList<String>();

    // making the consteructor private to implement singleton design pattern
   private SYTDatabaseHandler(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, version);
        this.context = context;
    }

    //adding get instance method to return one instance of this class in the singleton design pattern
    public static SYTDatabaseHandler getInstance(Context context){
        if(instance==null){
            instance = new SYTDatabaseHandler(context,null,null,1);
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        if(this.context.getDatabasePath(DATABASE_NAME) != null && this.context.getDatabasePath(DATABASE_NAME).toString().equals(db.toString().substring(16))) return;
        try {
            String create_table = "CREATE TABLE IF NOT EXISTS USERS(\n" +
                    "firstname VARCHAR (50) NOT NULL,\n" +
                    "lastname VARCHAR (50) NOT NULL,\n" +
                    "email VARCHAR (50) UNIQUE NOT NULL,\n" +
                    "username VARCHAR (50) UNIQUE NOT NULL,\n" +
                    "user_password VARCHAR (50) NOT NULL,\n" +
                    "isbanned INTEGER,\n" +
                    "PRIMARY KEY (username)\n" +
                    ");";
            db.execSQL(create_table);
        }
        catch (SQLiteCantOpenDatabaseException ex){
            throw ex;
        }
        catch (SQLiteException ex){
            throw ex;
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertUser(User user){
        onCreate(this.getWritableDatabase());
        try {
            String insert_user="INSERT INTO USERS VALUES('" + user.getFirstName() + "','" +
                user.getLastName() + "','" +
                user.getEmailAddress() + "','" +
                user.getUsename() + "','" +
                user.getPassword() + "','" +
                user.isBanned()+"');";
            SQLiteDatabase db = this.getWritableDatabase();
            db.execSQL(insert_user);

        }
        catch (SQLiteConstraintException ex){
            throw ex;
        }
        catch (SQLiteException ex){
            throw ex;
        }
    }
}
