package com.saurabhjn76.userapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by saurabh on 7/6/16.
 */
public class ProfileActivity extends AppCompatActivity {

    private TextView salon_address;
    private TextView salonName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.salon_info_page);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent =getIntent();
        LinearLayout layout = (LinearLayout) findViewById(R.id.linear);
         salonName=(TextView) findViewById(R.id.salon_profile_name);
        TextView salon_distance=(TextView)findViewById(R.id.salon_profile_distance);
         salon_address=(TextView)findViewById(R.id.salon_profile_address);
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
        if(getIntent().hasExtra("salon_name"))
        {
            Bundle bundle =getIntent().getExtras();
            salonName.setText(bundle.getString("salon_name").toCharArray(),0,bundle.getString("salon_name").length());
        }
        if(getIntent().hasExtra("salon_distance"))
        {
            Bundle bundle =getIntent().getExtras();
            salon_distance.setText(bundle.getString("salon_distance").toCharArray(),0,bundle.getString("salon_distance").length());
        }
        if(getIntent().hasExtra("salon_address"))
        {
            Bundle bundle =getIntent().getExtras();
            salon_address.setText(bundle.getString("salon_address").toCharArray(),0,bundle.getString("salon_address").length());
        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.salon_profile_menu, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        // Take appropriate action for each action item click
        switch (item.getItemId()) {
            case R.id.action_map:
                // search action
                Intent intent = new Intent(ProfileActivity.this, MapsActivity.class);
                intent.putExtra("salon_address",salon_address.getText());
                intent.putExtra("salon_name",salonName.getText());
                ProfileActivity.this.startActivity(intent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

