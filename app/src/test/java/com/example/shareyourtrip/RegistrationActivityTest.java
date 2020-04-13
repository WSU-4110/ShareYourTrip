package com.example.shareyourtrip;

import org.junit.Test;
import org.junit.jupiter.params.shadow.com.univocity.parsers.common.Context;
import org.junit.jupiter.params.shadow.com.univocity.parsers.common.DefaultContext;

import static org.junit.Assert.*;



public class RegistrationActivityTest {

    RegistrationActivity regAct = new RegistrationActivity();

    @Test
    public void isEmptyTest(){
        String emptyStr = "";
        String str = "some text";

        assertEquals(false, RegistrationActivity.isEmpty(str));

        assertEquals(true, RegistrationActivity.isEmpty(emptyStr));
    }

    @Test
    public void isValidEmailTest(){
        String sampleValidEmail = "email@email.edu";
        assertEquals(true,RegistrationActivity.isValidEmail(sampleValidEmail));

        String sampleInvalidEmail = "email@emailedu";
        assertEquals(false,RegistrationActivity.isValidEmail(sampleInvalidEmail));
    }

    @Test
    public void ckeckAlertTitleTest(){
        RegistrationActivity.alertTitle = "some title";

        //Context context = mock(Context.class);

        assertEquals(true, RegistrationActivity.ckeckAlertTitle("some title"));
    }

    @Test
    public void SetRegistrationPriorityTest(){

        assertEquals("early registration",RegistrationActivity.SetRegistrationPriority(7));
        assertEquals("mid-day registration",RegistrationActivity.SetRegistrationPriority(17));
        assertEquals("mid-day registration",RegistrationActivity.SetRegistrationPriority(23));

    }

    @Test
    public void isValidemailPatternTest(){
        String sampleValidEmail = "myEmail@emails.com";
        String sampleInvalidEmail = "myEmailemails.com";

        assertEquals(true,RegistrationActivity.isValidemailPattern(sampleValidEmail));

        assertEquals(false,RegistrationActivity.isValidemailPattern(sampleInvalidEmail));
    }

    @Test
    public void isPasswordSixCharTest(){
        String shortPass = "123";
        String longpass = "12345678";

        assertEquals(true, RegistrationActivity.isPasswordSixChar(longpass));
        assertEquals(false, RegistrationActivity.isPasswordSixChar(shortPass));
    }
}
