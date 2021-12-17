package com.smdproj.automobuy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {

    EditText email, password;
    TextView login;
    LogInfo ls;
    ImageView search, home, registration;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);

        search = findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Search.class);
                startActivity(intent);
            }
        });

        home = findViewById(R.id.home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Home.class);
                startActivity(intent);
            }
        });

        registration = findViewById(R.id.registeration);
        registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, TestDriveRegistration.class);
                startActivity(intent);
            }
        });



        login = findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData(new LogInfo(password.getText().toString(),email.getText().toString(),0,0));
            }

        });
    }

    private void getData(LogInfo log) {
        IP ip = new IP();
        String db = ip.DB_IP;

        String URL_LOGIN = db + "login.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_LOGIN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject object = new JSONObject(response);
                            if (object.getInt("success") == 1) {
                                //adding the product to product list
                                ls = (new LogInfo(
                                        password.getText().toString(),
                                        email.getText().toString(),
                                        object.getInt("is_mgr"),
                                        object.getInt("is_dlr")
                                ));

                                MyDBHelper db = new MyDBHelper(Login.this);
                                db.insertData(ls.getEmail(), ls.getPw(), String.valueOf(ls.getIsManager()), String.valueOf(ls.getIsDealer()));

                                if(ls.getIsManager() == 1){
                                    Intent intent = new Intent(Login.this, Manager_Dashboard.class);
                                    startActivity(intent);
                                }
                                else if(ls.getIsDealer() == 1){
                                    Intent intent = new Intent(Login.this, Dealer_Dashboard.class);
                                    startActivity(intent);
                                }
                                else{
                                    Toast.makeText(Login.this, "No Such User", Toast.LENGTH_LONG).show();
                                }
                                //Toast.makeText(Search.this, "DONE", Toast.LENGTH_SHORT).show();

                            }
                            else{
                                Toast.makeText(Login.this, "No Such User", Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Login.this, "NOT DONE", Toast.LENGTH_SHORT).show();
                    }

                }) {

            protected Map<String, String> getParams() {
                Map<String, String> data = new HashMap<String, String>();
                data.put("email", log.getEmail());
                data.put("password",log.getPw());
                return data;
            }
        };

        //adding our string request to queue
        Volley.newRequestQueue(this).add(stringRequest);

    }


}