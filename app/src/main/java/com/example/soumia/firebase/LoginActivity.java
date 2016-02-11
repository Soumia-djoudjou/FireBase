package com.example.soumia.firebase;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by soumia on 04/02/2016.
 */
public class LoginActivity  extends AppCompatActivity{


    Button loginbutton;
    Button signup;
    String usernametxt;
    String passwordtxt;
    EditText password;
    EditText username;
    Boolean Network = false;
    String userID;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        loginbutton = (Button) findViewById(R.id.login);
        signup = (Button) findViewById(R.id.signup);
        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //check internet connectivity


                // Retrieve the text entered from the EditText
                usernametxt = username.getText().toString();

                passwordtxt = password.getText().toString();

                final Firebase ref = new Firebase("https://blazing-inferno-4657.firebaseio.com");
                ref.authWithPassword(usernametxt, passwordtxt,
                        new Firebase.AuthResultHandler() {
                            @Override
                            public void onAuthenticated(AuthData authData) {
                                // Authentication just completed successfully :)
                                Map<String, String> map = new HashMap<String, String>();
                                map.put("provider", authData.getProvider());
                                if (authData.getProviderData().containsKey("displayName")) {
                                    map.put("displayName", authData.getProviderData().get("displayName").toString());


                                }
                                ref.child("users").child(authData.getUid()).setValue(map);
                               AuthData auth = ref.getAuth();
                               userID =  auth.getUid();
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                intent.putExtra("uid",userID);
                                startActivity(intent);
                                Toast.makeText(getApplicationContext(), "Successfully Logged in as : "+userID, Toast.LENGTH_LONG).show();
                                finish();



                            }

                            @Override
                            public void onAuthenticationError(FirebaseError error) {
                                // Something went wrong :(
                                Toast.makeText(
                                        getApplicationContext(),
                                        "No such user exist, please signup" +error,
                                        Toast.LENGTH_LONG).show();
                            }
                        });



            }
        });


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usernametxt = username.getText().toString();
                passwordtxt = password.getText().toString();
                final Firebase ref = new Firebase("https://blazing-inferno-4657.firebaseio.com");
                if (usernametxt.equals("") && passwordtxt.equals("")) {
                    Toast.makeText(getApplicationContext(),
                            "Please complete the sign up form",
                            Toast.LENGTH_LONG).show();

                } else {

                    ref.createUser(usernametxt, passwordtxt, new Firebase.ValueResultHandler<Map<String, Object>>() {
                        @Override
                        public void onSuccess(Map<String, Object> result) {
                            Toast.makeText(getApplicationContext(),
                                    "Successfully created user account with uid :" +result.get("uid"),
                                    Toast.LENGTH_LONG).show();
                        }
                        @Override
                        public void onError(FirebaseError firebaseError) {
                            Toast.makeText(getApplicationContext(),
                                    "Could not create User :" +firebaseError,
                                    Toast.LENGTH_LONG).show();
                        }
                    });

                }
            }});



            }

    }

