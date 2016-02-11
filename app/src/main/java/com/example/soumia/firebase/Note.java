package com.example.soumia.firebase;

/**
 * Created by soumia on 05/02/2016.
 */
public class Note  {

    private String text;
    private String User ;

    Note(String message,String user)
    {
        this.text = message;
        this.User = user;
    }
    public String getMessage() {
        return text;
    }

    public String getUser() {
        return User;
    }


}
