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

public class DisplayMessageActivity extends AppCompatActivity implements OnMapReadyCallback {
    private String zip;
    private double lat = 0;
    private double lon = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        zip = intent.getStringExtra("zip");
        lat = intent.getDoubleExtra("lat", 0);
        lon = intent.getDoubleExtra("lon", 0);


        setContentView(R.layout.activity_display_message);

        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        // Add a marker in Sydney, Australia,
        // and move the map's camera to the same location.
        LatLng sydney = new LatLng(lat, lon);
        googleMap.addMarker(new MarkerOptions().position(sydney)
                .title("Marker in Sydney"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 6));
        TextView textView = (TextView) findViewById(R.id.text_view_id);
        textView.setTextSize(20);
        textView.setText("Zip Code: " + zip);
    }
}
