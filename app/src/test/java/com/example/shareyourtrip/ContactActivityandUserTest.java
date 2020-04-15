package com.example.shareyourtrip;

import org.junit.Test;

import static org.junit.Assert.*;

public class ContactActivityandUserTest {

    // Create user
    User user1 = new User("Aaron", "Sherman", "adsherman98@yahoo.com",  "adsherman", "ga1452", "ga1452", true);

    @Test
    public void getFirstName_Test() {

        // Set variables
        String expected = "Aaron";
        String actual = user1.getFirstName();

        // Test getFirstName
        assertEquals(expected, actual);
    }

    @Test
    public void getLastName_Test() {

        // Set variables
        String expected = "Sherman";
        String actual = user1.getLastName();

        // Test getLastName
        assertEquals(expected, actual);
    }

    @Test
    public void getEmailAddress_Test() {

        // Set variables
        String expected = "adsherman98@yahoo.com";
        String actual = user1.getEmailAddress();

        // Test getEmailAddress
        assertEquals(expected, actual);
    }

    @Test
    public void getUsename_Test() {

        // Set variables
        String expected = "adsherman";
        String actual = user1.getUsename();

        // Test getUsername
        assertEquals(expected, actual);
    }

    @Test
    public void getPassword_Test() {

        // Set variables
        String expected = "ga1452";
        String actual = user1.getPassword();

        // Test getpassword
        assertEquals(expected, actual);
    }

    @Test
    public void isBanned_Test() {

        // Set variables
        boolean expected = true;
        boolean actual = user1.isBanned();

        // Test isBanned
        assertEquals(expected, actual);
    }

    @Test
    public void isValidEmail_Test() {

        //Creating a ContactActivity object
        ContactActivity newcontact = new ContactActivity();

        // Set variables
        String email = "adsherman98@yahoo.com";
        boolean expected = true;
        boolean actual = newcontact.isValidEmail(email);

        //Test if isValidEmail works
        assertEquals(expected, actual);
    }
}