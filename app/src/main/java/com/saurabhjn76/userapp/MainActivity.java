package com.saurabhjn76.userapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.firebase.client.Firebase;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Firebase.setAndroidContext(this);

    }
    void wrtieData()
    {
        Firebase ref = new Firebase("https://salon-app-dad97.firebaseio.com/data");

    }
}

