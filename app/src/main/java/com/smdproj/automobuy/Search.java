package com.smdproj.automobuy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Search extends AppCompatActivity {

    ArrayList<Vehicles> ls;
    Vehicle_Adapter adapter;
    RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        rv = findViewById(R.id.search_recycle);
        ls = new ArrayList<>();
        RecyclerView.LayoutManager lm = new LinearLayoutManager(Search.this);
        rv.setLayoutManager(lm);
        getData();
    }

    private void getData() {

        String URL_VEHICLES = "http://192.168.1.7/automobuy/get_vehicle.php";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_VEHICLES,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject object = new JSONObject(response);
                            if (object.getInt("success") == 1) {

                                //converting the string to json array object
                                JSONArray array = object.getJSONArray("vehicle");

                                //traversing through all the object
                                for (int i = 0; i < array.length(); i++) {

                                    //getting product object from json array
                                    JSONObject vehicle = array.getJSONObject(i);

                                    //adding the product to product list
                                    ls.add(new Vehicles(
                                            vehicle.getString("id"),
                                            vehicle.getString("name"),
                                            vehicle.getString("company"),
                                            vehicle.getString("type"),
                                            vehicle.getString("model"),
                                            vehicle.getString("cost"),
                                            vehicle.getString("color"),
                                            vehicle.getString("available"),
                                            vehicle.getString("image")
                                    ));
                                }

                                //creating adapter object and setting it to recyclerview
                                adapter = new Vehicle_Adapter(Search.this, ls);
                                rv.setAdapter(adapter);
                                Toast.makeText(Search.this, ls.get(0).getImg(), Toast.LENGTH_SHORT).show();

                            }
                            else{
                                Toast.makeText(Search.this, "Failed to load data", Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Search.this, "NOT DONE", Toast.LENGTH_SHORT).show();
                    }
                });

        //adding our string request to queue
        Volley.newRequestQueue(this).add(stringRequest);

    }

}