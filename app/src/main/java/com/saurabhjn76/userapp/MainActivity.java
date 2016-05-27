package com.saurabhjn76.userapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Firebase.setAndroidContext(this);
        writeData();

    }
    void writeData()
    {
        Firebase ref = new Firebase("https://salon-app-dad97.firebaseio.com/data");
        Salon[] s= new Salon[11];
        s[0]=new Salon("Affinity Salon","1st Floor, Global Foyer Mall","Golf Course Road","Unisex",4.2,true,true,4);
        s[1]=new Salon("Neu Salonz","4th Floor","South Point Mall","Unisex",4.1,true,true,8);
        s[2]=new Salon("Bella Madonna","123 1st Floor","South Point Mall","Unisex",4.2,true,true,0.6);
        s[3]=new Salon("Levo Saplon","Ibis Hotel","Golf Course Road","Unisex",5,false,true,0.3);
        s[4]=new Salon("")

        ref.push().setValue(s[0]);
        ref.push().setValue(s[1]);
        ref.push().setValue(s[2]);
        ref.push().setValue(s[3]);
       /* ref.setValue(s[0], new Firebase.CompletionListener() {
            @Override
            public void onComplete(FirebaseError firebaseError, Firebase firebase) {
                if (firebaseError != null) {
                    System.out.println("Data could not be saved. " + firebaseError.getMessage());
                } else {
                    System.out.println("Data saved successfully.");
                }
            }
        });*/




    }
}

