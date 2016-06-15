package com.saurabhjn76.userapp;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.LocationManager;
import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;
import java.util.Locale;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private String address=null;
    private String salon_name=null;
    private ImageButton navigate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        if(getIntent().hasExtra("salon_address"))
        {
            Bundle bundle =getIntent().getExtras();
            address=bundle.getString("salon_address");
        }
        if(getIntent().hasExtra("salon_name"))
        {
            Bundle bundle =getIntent().getExtras();
            salon_name=bundle.getString("salon_name");
        }

       // mMap.getUiSettings().setZoomGesturesEnabled(true);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        /*navigate=(ImageButton)findViewById(R.id.action_navigate);
        navigate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri gmmIntentUri = Uri.parse("http://maps.google.com/maps?saddr=28.5888925,77.06163949999996&daddr=28.4551923, 77.28936060000001");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });*/

    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng gurgaon = new LatLng(28.481216, 77.019135);
        gurgaon=getLocationFromAddress(getApplicationContext(),address);
        Toast.makeText(getApplicationContext(),gurgaon.toString(),Toast.LENGTH_LONG).show();
        try {
            mMap.addMarker(new MarkerOptions().position(gurgaon).title(salon_name));
        }
        catch (Exception e)
        {
             gurgaon = new LatLng(28.481216, 77.019135);
            mMap.addMarker(new MarkerOptions().position(gurgaon).title(salon_name));
        }
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(gurgaon,15.0f));


    }
    public LatLng getLocationFromAddress(Context context, String strAddress) {

        Geocoder coder = new Geocoder(context);
        List<Address> address;
        LatLng p1 = null;

        try {
            address = coder.getFromLocationName(strAddress, 5);
            if (address == null) {
                return new LatLng(28.481216, 77.019135);
            }

            Address location = address.get(0);
            location.getLatitude();
            location.getLongitude();

            p1 = new LatLng(location.getLatitude(), location.getLongitude() );

        } catch (Exception ex) {

            ex.printStackTrace();
        }
        if(p1==null)
            return new LatLng(28.481216, 77.019135);
        else
        return p1;
    }

}
