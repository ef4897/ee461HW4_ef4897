package com.example.bdoob.myfirstapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    public final static String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void sendMessage(View view){
        Intent intent = new Intent(this,DisplayMessageActivity.class);


        String baseUrl = "https://maps.googleapis.com/maps/api/geocode/json?";
        String apiKey = "AIzaSyCrmehXbbYBqtPh-BslcekSQ-7ZV_g-Maw";
        String url = "";

        EditText editText =(EditText) findViewById(R.id.edit_message_a);
        String message1 = editText.getText().toString();
        editText =(EditText) findViewById(R.id.edit_message_c);
        String message2 = editText.getText().toString();
        editText =(EditText) findViewById(R.id.edit_message_s);
        String message3 = editText.getText().toString();

        url= baseUrl + "address=" + message1.replaceAll(" ", "+") + ",+" + message2.replaceAll(" ", "+") + "," + message3.toUpperCase() + "&key=" + apiKey;
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


    }

    public void sendResponse(double lat, double lon, String zip){
        Log.i("Values", "lat = " + lat + " lon =  " + lon + " zip = " + zip);
        Intent intent = new Intent(this,DisplayMessageActivity.class);
        intent.putExtra("lat", lat);
        intent.putExtra("lon", lon);
        intent.putExtra("zip", zip);
        startActivity(intent);
    }
}
