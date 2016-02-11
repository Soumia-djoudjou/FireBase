package com.example.soumia.firebase;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;

/**
 * Created by soumia on 04/02/2016.
 */
public class check extends Activity {


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Firebase ref = new Firebase(MainActivity.reference);
        AuthData authData = ref.getAuth();
        if (authData != null) {

            Intent intent = new Intent(
                    check.this,
                    MainActivity.class);
            startActivity(intent);

        } else {
            Intent intent = new Intent(
                    check.this,
                    LoginActivity.class);
            startActivity(intent);
        }



    }
}