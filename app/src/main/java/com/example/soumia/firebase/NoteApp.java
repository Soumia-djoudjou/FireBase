package com.example.soumia.firebase;

import android.app.Application;

import com.firebase.client.Firebase;

/**
 * Created by soumia on 10/02/2016.
 */

//Includes one-time initialization of Firebase related code

public class NoteApp extends Application {

    public void onCreate() {
        super.onCreate();
        /* Initialize Firebase */
        Firebase.setAndroidContext(this);
        /* Enable disk persistence  */
        Firebase.getDefaultConfig().setPersistenceEnabled(true);
    }
}
