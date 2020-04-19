package com.example.shareyourtrip;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class TestPostDAO {

    Context context = ApplicationProvider.getApplicationContext();
    private final SimpleDateFormat date1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private final String date = date1.format(new Date());
    private PostDAO postDAO = new PostDAO(context);
    private Post post = new Post("Detroit", "MI", "Ima", "Food",
            "Very good food", "lc@a.com", date, "1", "1", false); //all entries in post are null
    private static final boolean exp = false;
    private String[] col = {"city", "state", "category", "title", "description", "user", "date", "up", "down"};
    private String[] val = {"Torino", "Piemonte", "Museum", "Museo Egizio", "Molto affascinante",
            "l@a.com", "1999-09-19 22:22:22", "1", "1"};

   /* @Before
    public void TestPostDAO(){

        Context context = ApplicationProvider.getApplicationContext();
        postDAO = new PostDAO(context);

        //initialize(db);
    }*/

    @Test
    public void testInsertPost(){
        //checking with empty post
        assertFalse("Not inserted post",
                postDAO.insertPostByValues("","","","","","","", false));

        assertTrue("Inserted post",
                postDAO.insertPostByValues(val[0], val[1], val[2], val[3], val[4], val[5], val[6], false));
    }

    @Test
    public void testInsertPost_withAnId(){
        //checking with adding an id
        Post newPost = new Post("1", val[0], val[1], val[2], val[3], val[4], val[5], val[6], val[7], val[8], false);
        assertTrue("Can insert post with an ID because it is not added into the database", postDAO.insertPost(post));
    }

    //2
    @Test
    public void testListAllPost(){
        String query = "select * from post where city=?";
        String[] col2 = {"Miami"};
        String value = val[0];
        val[0] = col2[0];

        postDAO.insertPostByValues(val[0], val[1], val[2], val[3], val[4], val[5], val[6], false);
        assertEquals(1, postDAO.listAllPost(query, col2).size());
        val[0] = value;
    }

    //3
    @Test
    public void testUpdatePost(){
        assertTrue("Able to update", postDAO.updatePost(col, val, "id=?", new String[] {"2"}));
    }

    //4
    @Test
    public void testDeletePost(){
        String badClause = "city=?";
        assertTrue("Only one city miami", postDAO.deletePost(badClause, new String[] {"Miami"}));
    }

    //5
    @Test
    public void testInsertFavPost(){
        Post newPost = new Post("1", val[0], val[1], val[2], val[3], val[4], val[5], val[6], val[7], val[8], false);
        assertFalse("There doesn't exists a post with id = 1", postDAO.insertFavPost(post));
    }

    //6
    @Test
    public void testDeleteFavPost() {


        String goodClause = "postid=? and useremail=?";
        String[] args = {"1", "lc@a.com"};
        assertEquals(exp, postDAO.deleteFavPost(goodClause, null));
    }
}
