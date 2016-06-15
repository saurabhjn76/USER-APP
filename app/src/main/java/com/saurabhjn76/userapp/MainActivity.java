package com.saurabhjn76.userapp;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private RecyclerView rv;
    private Toolbar toolbar;
    private ImageView imageView;
    private ImageView imageViewSort;
    private ImageView imageViewMap;
    final ArrayList<Salon> salons = new ArrayList<>();
    private ValueEventListener valueEventListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        imageView = (ImageView) findViewById(R.id.action_calendar);
        imageViewSort=(ImageView)findViewById(R.id.action_sort);
        imageViewMap=(ImageView)findViewById(R.id.action_map);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"calendar",Toast.LENGTH_SHORT).show();
                openBottomSheet();

            }
        });
        imageViewSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Sorting",Toast.LENGTH_SHORT).show();
                openSortBottomSheet();

            }
        });
        imageViewMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Map..",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(v.getContext(), Maps_toolbar_activity.class);
                intent.putExtra("size",salons.size());
                for(int i=0;i< salons.size();i++)
                {
                    intent.putExtra("Salon_address"+i,salons.get(i).getAddressLine1()+" " +salons.get(i).getAddressLine2());
                }
                v.getContext().startActivity(intent);

            }
        });



        //writeData();
        //readData();
        rv = (RecyclerView)findViewById(R.id.rv);
        rv.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
        rv.setLayoutManager(llm);
        initializeAdapter();

    }
    public void openBottomSheet() {
        View view = getLayoutInflater().inflate(R.layout.bottom_sheet_filter, null);
        ImageView saveIcon = (ImageView)view.findViewById(R.id.save);
        ImageView editIcon = (ImageView)view.findViewById(R.id.edit);
        ImageView printIcon = (ImageView)view.findViewById(R.id.print);
        ImageView shareIcon = (ImageView)view.findViewById(R.id.print);
        final Dialog mBottomSheetDialog = new Dialog(MainActivity.this, R.style.MaterialDialogSheet);
        mBottomSheetDialog.setContentView(view);
        mBottomSheetDialog.setCancelable(true);
        mBottomSheetDialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        mBottomSheetDialog.getWindow().setGravity(Gravity.BOTTOM);
        mBottomSheetDialog.show();
        saveIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Saving...", Toast.LENGTH_LONG).show();
                mBottomSheetDialog.dismiss();
            }
        });
        editIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Editing...", Toast.LENGTH_LONG).show();
                mBottomSheetDialog.dismiss();
            }
        });
        printIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Printing...", Toast.LENGTH_LONG).show();
                mBottomSheetDialog.dismiss();
            }
        });
        shareIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Sharing...", Toast.LENGTH_LONG).show();
                mBottomSheetDialog.dismiss();
            }
        });
    }
    public void openSortBottomSheet() {
        View view = getLayoutInflater().inflate(R.layout.bottom_sheet_sort, null);
        ImageView saveIcon = (ImageView)view.findViewById(R.id.save);
        ImageView editIcon = (ImageView)view.findViewById(R.id.edit);
        ImageView printIcon = (ImageView)view.findViewById(R.id.print);
        final Dialog mBottomSheetDialog = new Dialog(MainActivity.this, R.style.MaterialDialogSheet);
        mBottomSheetDialog.setContentView(view);
        mBottomSheetDialog.setCancelable(true);
        mBottomSheetDialog.getWindow().setLayout(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        mBottomSheetDialog.getWindow().setGravity(Gravity.BOTTOM);
        mBottomSheetDialog.show();
        saveIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Saving...", Toast.LENGTH_LONG).show();
                mBottomSheetDialog.dismiss();
            }
        });
        editIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Editing...", Toast.LENGTH_LONG).show();
                mBottomSheetDialog.dismiss();
            }
        });
        printIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Printing...", Toast.LENGTH_LONG).show();
                mBottomSheetDialog.dismiss();
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

   /* @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id==R.id.action_divider1)
            Toast.makeText(getApplicationContext(),"divider",Toast.LENGTH_SHORT);
        return super.onOptionsItemSelected(item);
    }*/
    private void initializeAdapter(){
        //Log.e("dataa",salons.size()+"");
        RVAdapter adapter = new RVAdapter();
        rv.setAdapter(adapter);
        readData(adapter);
    }
    void writeData()
    {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("salon-app-dad97/data");
      //  Firebase ref = new Firebase("https://salon-app-dad97.firebaseio.com/data");
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

    ArrayList<Salon> readData(final RVAdapter rv) {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("salon-app-dad97/data");

        // Attach an listener to read the data at our posts reference
        ref.addValueEventListener( new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                //  Log.i(this, "Called getTestData()");
                System.out.println("There are " + snapshot.getChildrenCount() + " salons");
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    Salon salon = postSnapshot.getValue(Salon.class);
                    // System.out.println(salon.getSalonName());
                    Log.e("data", "" + salon);
                    salons.add(salon);
                    rv.add(salon);

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                    System.out.println("The read failed: "+ databaseError.getMessage());
            }
        });
        return salons;
    }
}

