package com.example.bdoob.myfirstapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;

public class MainActivity extends AppCompatActivity {
    public final static String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    double lat;
    double lon;
    String zipCode;
    boolean both = false;

    public void sendMessage(View view) {
        Intent intent = new Intent(this, DisplayMessageActivity.class);


        String baseUrl = "https://maps.googleapis.com/maps/api/geocode/json?";
        String apiKey = "AIzaSyCrmehXbbYBqtPh-BslcekSQ-7ZV_g-Maw";
        String url = "";

        EditText editText = (EditText) findViewById(R.id.edit_message_a);
        String message1 = editText.getText().toString();
        editText = (EditText) findViewById(R.id.edit_message_c);
        String message2 = editText.getText().toString();
        editText = (EditText) findViewById(R.id.edit_message_s);
        String message3 = editText.getText().toString();
        editText = (EditText) findViewById(R.id.edit_message_a1);
        String message4 = editText.getText().toString();
        editText = (EditText) findViewById(R.id.edit_message_c1);
        String message5 = editText.getText().toString();
        editText = (EditText) findViewById(R.id.edit_message_s1);
        String message6 = editText.getText().toString();


        url = baseUrl + "address=" + message1.replaceAll(" ", "+") + ",+" + message2.replaceAll(" ", "+") + "," + message3.toUpperCase() + "&key=" + apiKey;
        Log.i("url", url);
        RequestQueue mRequestQueue;
        // Instantiate the cache
        Cache cache = new DiskBasedCache(getCacheDir(), 1024 * 1024); // 1MB cap

// Set up the network to use HttpURLConnection as the HTTP client.
        Network network = new BasicNetwork(new HurlStack());

// Instantiate the RequestQueue with the cache and network.
        mRequestQueue = new RequestQueue(cache, network);

// Start the queue
        mRequestQueue.start();

        JSONcontroller jsonObjectRequest = new JSONcontroller(url, this);

// Access the RequestQueue through your singleton class.
        mRequestQueue.add(jsonObjectRequest);


        if ((message6.length()<1) && (message5.length()<1) && (message4.length()<1)) {
            both = false;
        }
        else{
            both = true;
        }

        if (both) {
            String url1 = baseUrl + "address=" + message4.replaceAll(" ", "+") + ",+" + message5.replaceAll(" ", "+") + "," + message6.toUpperCase() + "&key=" + apiKey;
            Log.i("url1", url1);
            RequestQueue mRequestQueue1;
            // Instantiate the cache
            Cache cache1 = new DiskBasedCache(getCacheDir(), 1024 * 1024); // 1MB cap

// Set up the network to use HttpURLConnection as the HTTP client.
            Network network1 = new BasicNetwork(new HurlStack());

// Instantiate the RequestQueue with the cache and network.
            mRequestQueue1 = new RequestQueue(cache1, network1);

// Start the queue
            mRequestQueue1.start();

            JSONcontroller jsonObjectRequest1 = new JSONcontroller(url1, this, "second");

// Access the RequestQueue through your singleton class.
            mRequestQueue.add(jsonObjectRequest1);
        }


    }
    public void sendResponse(double lat, double lon, String zip){
        Log.i("Values", "lat = " + lat + " lon =  " + lon + " zip = " + zip);
        Intent intent = new Intent(this,DisplayMessageActivity.class);
        intent.putExtra("lat", lat);
        intent.putExtra("lon", lon);
        intent.putExtra("zip", zip);
        startActivity(intent);
    }

    public void sendResponse(double lat, double lon, String zip, double lat1, double lon1, String zip1){
        Log.i("Values", "lat = " + lat + " lon =  " + lon + " zip = " + zip + "lat1 = " + lat1 + " lon1 =  " + lon1 + " zip1 = " + zip1);
        Intent intent = new Intent(this,DisplayMessageActivity.class);
        intent.putExtra("lat", lat);
        intent.putExtra("lon", lon);
        intent.putExtra("zip", zip);
        intent.putExtra("lat1", lat1);
        intent.putExtra("lon1", lon1);
        intent.putExtra("zip1", zip1);
        intent.putExtra("distance", getDistanceFromLatLonInKm(lat, lon, lat1, lon1));
        startActivity(intent);
    }

    double getDistanceFromLatLonInKm(double lat1,double lon1,double lat2,double lon2) {
        int R = 6371; // Radius of the earth in km
        double latDiff = degree2radians(lat2 - lat1);  // deg2rad below
        double lonDiff = degree2radians(lon2-lon1);
        double a =
                Math.sin(latDiff/2) * Math.sin(latDiff/2) +
                        Math.cos(degree2radians(lat1)) * Math.cos(degree2radians(lat2)) *
                                Math.sin(lonDiff/2) * Math.sin(lonDiff/2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        double d = R * c; // Distance in km
        return d;
    }

    double degree2radians(double degree) {
        return degree * (Math.PI/180);
    }
}
