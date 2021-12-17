package com.smdproj.automobuy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Search extends AppCompatActivity {

    ArrayList<Vehicles> ls;
    Vehicle_Adapter adapter;
    RecyclerView rv;
    TextView search_button;
    EditText search_text;
    ImageView registration, home, menu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        registration = findViewById(R.id.registeration);
        registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Search.this, TestDriveRegistration.class);
                startActivity(intent);
            }
        });

        home = findViewById(R.id.home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Search.this, Home.class);
                startActivity(intent);
            }
        });

        menu = findViewById(R.id.menu);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDBHelper db = new MyDBHelper(Search.this);
                String value = db.checkUser();
                if (value.equals("mgr")) {
                    Intent intent = new Intent(Search.this, Manager_Dashboard.class);
                    startActivity(intent);
                } else if (value.equals("dlr")) {
                    Intent intent = new Intent(Search.this, Dealer_Dashboard.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(Search.this, Login.class);
                    startActivity(intent);
                }
            }
       });
                
        rv = findViewById(R.id.search_recycle);
        ls = new ArrayList<>();
        RecyclerView.LayoutManager lm = new LinearLayoutManager(Search.this);
        rv.setLayoutManager(lm);

        ls = getData();

        search_text = findViewById(R.id.search_text);
        search_button = findViewById(R.id.search_button);
        search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String temp = search_text.getText().toString();
                ArrayList<Vehicles> new_ls = new ArrayList<>();
                for(int i = 0; i < ls.size(); i++){
                    if(ls.get(i).getName().equals(temp) || ls.get(i).getType().equals(temp) || ls.get(i).getModel().equals(temp)){
                        new_ls.add(ls.get(i));
                    }
                }
                adapter = new Vehicle_Adapter(Search.this, new_ls);
                rv.setAdapter(adapter);
            }
        });

    }

    private ArrayList<Vehicles> getData() {
        IP ip = new IP();
        ls = new ArrayList<>();

        String URL_VEHICLES = ip.getDB_IP() + "get_vehicle.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_VEHICLES,
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
                        Toast.makeText(Search.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }) {

            protected Map<String, String> getParams() {
                Map<String, String> data = new HashMap<String, String>();
                data.put("ip", ip.getOnli_ip());
                return data;
            }
        };

        //adding our string request to queue
        Volley.newRequestQueue(Search.this).add(stringRequest);
        return ls;
    }
}