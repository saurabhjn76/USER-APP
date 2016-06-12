package com.saurabhjn76.userapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutCompat;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by saurabh on 7/6/16.
 */
public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.salon_info_page);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent =getIntent();
        LinearLayout layout = (LinearLayout) findViewById(R.id.linear);
        for (int i = 0; i <5; i++) {
            Toast.makeText(getApplicationContext(),"the...",Toast.LENGTH_SHORT).show();
            ImageView imageView = new ImageView(this);
            imageView.setId(i);
            imageView.setPadding(2, 2, 2, 2);
            imageView.setLayoutParams(
                    new LinearLayoutCompat.LayoutParams(LinearLayoutCompat.LayoutParams.MATCH_PARENT, LinearLayoutCompat.LayoutParams.MATCH_PARENT));
            //imageView.setLayoutParams;
            if(getIntent().hasExtra("byteArray")) {
                Bitmap b = BitmapFactory.decodeByteArray(
                        getIntent().getByteArrayExtra("byteArray"),0,getIntent().getByteArrayExtra("byteArray").length);
                imageView.setImageBitmap(b);
            }
            else
            imageView.setImageBitmap(BitmapFactory.decodeResource(
                    getResources(), R.drawable.salon_4_full));
            imageView.setAdjustViewBounds(true);
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            layout.addView(imageView);
        }
    }
}

