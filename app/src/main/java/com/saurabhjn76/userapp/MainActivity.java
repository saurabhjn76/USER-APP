package com.saurabhjn76.userapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Firebase.setAndroidContext(this);
       // writeData();
        readData();


    }
    void writeData()
    {
        Firebase ref = new Firebase("https://salon-app-dad97.firebaseio.com/data");
        Salon[] s= new Salon[10];
        s[0]=new Salon("Affinity Salon","1st Floor, Global Foyer Mall","Golf Course Road","Unisex",4.2,true,true,4);
        s[1]=new Salon("Neu Salonz","4th Floor","South Point Mall","Unisex",4.1,true,true,8);
        s[2]=new Salon("Bella Madonna","123 1st Floor","South Point Mall","Unisex",4.2,true,true,0.6);
        s[3]=new Salon("Levo Saplon","Ibis Hotel","Golf Course Road","Unisex",5,false,true,0.3);
        s[4]=new Salon("ISAAC Wellness","319, 3rd Floor","South Point Mall","Unisex",-1,true,true,1.4);
        s[5]=new Salon("Kosh Salon","DSF Phase 2 , Behind Supermart-2","Supermart 2","Unisex",-1,true,false,3.6);
        s[6]=new Salon("Makeup by Vidya Tikari","GF, Peagasu One Ibis Hotel DLF","Golf Course Road","Women",-1,false,true,4.8);
        s[7]=new Salon("Cut & Style","Shop No A-241","Supermart 1","Unisex",4.4,false,false,0.39);
        s[8]=new Salon("Tangles Salon","6005, Opposite Supermart 1","DLF Phase 4","Unisex",4,true,true,5);
        s[9]=new Salon("Amaaya Spa","6302 , Opp. Supemart 1","DLF Phase 4","Unisex",-1,true,true,3.8);

        for(int i=0;i<10;i++)
            ref.push().setValue(s[i]);

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

    void readData(){
        Firebase ref = new Firebase("https://salon-app-dad97.firebaseio.com/data");
        // Attach an listener to read the data at our posts reference
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                System.out.println("There are " + snapshot.getChildrenCount() + " salons");
                for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                    Salon salon = postSnapshot.getValue(Salon.class);
                    System.out.println(salon.getSalonName());
                }
            }
            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("The read failed: " + firebaseError.getMessage());
            }
        });
    }
}

