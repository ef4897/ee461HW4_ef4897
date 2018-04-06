package com.example.bdoob.myfirstapp;

import android.app.Activity;
import android.content.Intent;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

/**
 * Created by bdoob on 4/4/2018.
 */

public class JSONcontroller extends JsonObjectRequest{
    public JSONcontroller(String url, final MainActivity activity){
        super(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                try {


                    String zipCode = response.getJSONArray("results").getJSONObject(0).getJSONArray("address_components").getJSONObject(7).getString("long_name");
                    double lat = response.getJSONArray("results").getJSONObject(0).getJSONObject("geometry").getJSONObject("location").getDouble("lat");
                    double lon = response.getJSONArray("results").getJSONObject(0).getJSONObject("geometry").getJSONObject("location").getDouble("lng");
                    String resp = response.toString();
                    activity.sendResponse(lat, lon, zipCode);
                }
                catch(Exception e){activity.sendResponse(0, 0,"Error: " + e.getMessage());}
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                activity.sendResponse(0, 0,"--ERROR--: " + error.getMessage());

            }
        });
    }
}
