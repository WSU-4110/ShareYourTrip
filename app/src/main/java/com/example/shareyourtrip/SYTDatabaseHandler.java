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

    private static final String DATABASE_NAME="shareyourtripdb";
    private ArrayList<String> tables = new ArrayList<String>();

    private SYTDatabaseHandler(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public static SYTDatabaseHandler getInstance(Context context){
        if(instance==null){
            instance = new SYTDatabaseHandler(context,null,null,1);
        }
        return instance;
    }

    public void addTable(String table_name){
        tables.add(table_name);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
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
            tables.add("Users");
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
        try {
            String insert_user="INSERT INTO USERS VALUES('" + user.getFirstName() + "','" +
                user.getLastName() + "','" +
                user.getEmailAddress() + "','" +
                user.getUsename() + "','" +
                user.getPassword() + "','" +
                user.isBanned()+"');";
            SQLiteDatabase db = this.getWritableDatabase();
            db.execSQL(insert_user);

            /*
            ContentValues values = new ContentValues();
            values.put("firstname", user.getFirstName());
            values.put("lastname", user.getLastName());
            values.put("email", user.getEmailAddress());
            values.put("username", user.getUsename());
            values.put("user_password", user.getPassword());
            values.put("isbanned", 0);
            db.insert("USERS", null, values);

             */
        }
        catch (SQLiteConstraintException ex){
            throw ex;
        }
        catch (SQLiteException ex){
            throw ex;
        }
    }
}
