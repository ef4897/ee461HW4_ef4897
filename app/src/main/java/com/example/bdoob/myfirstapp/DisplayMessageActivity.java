package com.example.bdoob.myfirstapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

public class DisplayMessageActivity extends AppCompatActivity implements OnMapReadyCallback {
    private String zip;
    private double lat;
    private double lon;
    private String zip1;
    private double lat1;
    private double lon1;
    private double distance;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        zip = intent.getStringExtra("zip");
        lat = intent.getDoubleExtra("lat", 0);
        lon = intent.getDoubleExtra("lon", 0);
        zip1 = intent.getStringExtra("zip1");
        lat1 = intent.getDoubleExtra("lat1", 0);
        lon1 = intent.getDoubleExtra("lon1", 0);
        distance = intent.getDoubleExtra("distance", 0);



        setContentView(R.layout.activity_display_message);

        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        LatLng city1 = new LatLng(lat, lon);
        googleMap.addMarker(new MarkerOptions().position(city1)
                .title("First City"));

        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(city1, 6));
        TextView textView = (TextView) findViewById(R.id.text_view_zip1);
        textView.setTextSize(20);
        textView.setText("Zip Code1: " + zip);
        if(lat1 != 0){
            LatLng city2 = new LatLng(lat1, lon1);
            googleMap.addMarker(new MarkerOptions().position(city2)
                    .title("Second City"));
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(city2, 6));
            googleMap.addPolyline(new PolylineOptions().add(city1, city2).width(5));
            TextView textView2 = (TextView) findViewById(R.id.text_view_zip2);
            textView2.setTextSize(20);
            textView2.setText("Zip Code2: " + zip1);
            TextView textView3 = (TextView) findViewById(R.id.text_view_distance);
            textView3.setTextSize(20);
            textView3.setText("Distance: " + distance + "km");
        }
    }
}
