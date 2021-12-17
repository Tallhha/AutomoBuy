package com.smdproj.automobuy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
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

public class D_D_Manage extends AppCompatActivity {

    ArrayList<BookingForm> ls;
    Booking_Adapter adapter;
    RecyclerView rv;
    EditText id_to_delete;
    TextView approve_button, reject_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dd_manage);

        rv = findViewById(R.id.Manage_list);
        ls = new ArrayList<>();
        RecyclerView.LayoutManager lm = new LinearLayoutManager(D_D_Manage.this);
        rv.setLayoutManager(lm);
        ls = getData();

        id_to_delete = findViewById(R.id.id_to_delete);
        approve_button = findViewById(R.id.approve_button);
        approve_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String temp = id_to_delete.getText().toString();
                for(int i = 0; i < ls.size(); i++) {
                    if (temp.equals(ls.get(i).getId())) {
                        postData(new Sale("", "i180417@nu.edu.pk", ls.get(i).getEmail(), "Tayyab Abbas", ls.get(i).getFirstName(), ls.get(i).getVehicle(), ls.get(i).getModel(), ls.get(i).getCost(), ls.get(i).getBuyingOption()));

                        removeData(id_to_delete.getText().toString());
                        String emailBody="";


                        emailBody+=("\t\t\t  ** AUTOMOBUY **\n");
                        emailBody+=("\t\t\t** SALE FORM **\n");
                        emailBody+=("Our team would like to congratulate your brand new purchcase!" +"\n");
                        emailBody+=("We hope you have an astonishing experience while cruising in YOUR neww found vehicle" +"\n");
                        emailBody+=("We will make sure to offer free maintenance for one year to give you full support" +"\n");

                        emailBody+=("Have a good day sir!" +"\n");

                        emailBody+=("\n\t\t\t**Thanks For Support**\n");
                        emailBody+=("\t\t\t      ** B.M.W **\n");
                        String email = "i180573@nu.edu.pk";
                        JavaMailAPI javaMailAPI = new JavaMailAPI(D_D_Manage.this, email, "Request Approved", emailBody);
                        javaMailAPI.execute();
                        break;
                    }
                }
            }
        });

        reject_button = findViewById(R.id.reject_button);
        reject_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String temp = id_to_delete.getText().toString();
                for(int i = 0; i < ls.size(); i++) {
                    if (temp.equals(ls.get(i).getId())) {
                        removeData(id_to_delete.getText().toString());
                        String emailBody="";
                        emailBody+=("\t\t\t  ** AUTOMOBUY **\n");
                        emailBody+=("\t\t\t** SALE FORM **\n");
                        emailBody+=("It is an unfortunate occuring that we have no choice but to reject your purchase request" +"\n");
                        emailBody+=("There were some fields missing and/or were given invalid entries which is why we can not accept it." +"\n");
                        emailBody+=("Please be sure to recheck your purchase form and try again" +"\n");
                        emailBody+=("We will try our best to cooperate with you." +"\n");

                        emailBody+=("Have a good day sir!" +"\n");

                        emailBody+=("\n\t\t\t**Thanks For Support**\n");
                        emailBody+=("\t\t\t      ** B.M.W **\n");
                        String email = "i180573@nu.edu.pk";
                        JavaMailAPI javaMailAPI = new JavaMailAPI(D_D_Manage.this, email, "Request Denied", email);
                        javaMailAPI.execute();
                        break;
                    }
                }
            }
        });
    }

    private ArrayList<BookingForm> getData() {
        IP ip = new IP();
        ls = new ArrayList<>();
        String URL_FORM = ip.getDB_IP() + "get_booking.php";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_FORM,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject object = new JSONObject(response);
                            if (object.getInt("success") == 1) {

                                //converting the string to json array object
                                JSONArray array = object.getJSONArray("booking");

                                //traversing through all the object
                                for (int i = 0; i < array.length(); i++) {

                                    //getting product object from json array
                                    JSONObject booking = array.getJSONObject(i);

                                    //adding the product to product list
                                    ls.add(new BookingForm(
                                            booking.getString("id"),
                                            booking.getString("vehicle"),
                                            booking.getString("fname"),
                                            booking.getString("lname"),
                                            booking.getString("email"),
                                            booking.getString("phone_no"),
                                            booking.getString("cnic"),
                                            booking.getString("buying_option"),
                                            booking.getString("type"),
                                            booking.getString("model"),
                                            booking.getString("cost"),
                                            booking.getString("color")
                                            ));
                                }

                                //creating adapter object and setting it to recyclerview
                                adapter = new Booking_Adapter(D_D_Manage.this, ls);
                                rv.setAdapter(adapter);
                                
                            }
                            else{
                                Toast.makeText(D_D_Manage.this, "Failed to load data", Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(D_D_Manage.this, "NOT DONE", Toast.LENGTH_SHORT).show();
                    }
                });

        //adding our string request to queue
        Volley.newRequestQueue(this).add(stringRequest);
        return ls;
    }

    public void removeData(String id) {
        IP ip = new IP();
        String INSERT_FORM = ip.getDB_IP() + "delete_booking.php";
        StringRequest request = new StringRequest(Request.Method.POST, INSERT_FORM, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                getData();
                //Toast.makeText(D_D_Manage.this, "DONE", Toast.LENGTH_SHORT).show();
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(D_D_Manage.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
        ) {
            protected Map<String, String> getParams() {
                Map<String, String> data = new HashMap<String, String>();
                data.put("id", id);
                return data;
            }

        };

        Volley.newRequestQueue(D_D_Manage.this).add(request);

    }

    public void postData(Sale sale) {

        IP ip = new IP();
        String INSERT_FORM = ip.getDB_IP() + "insert_sale.php";
        StringRequest request = new StringRequest(Request.Method.POST, INSERT_FORM, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(D_D_Manage.this, "DONE", Toast.LENGTH_SHORT).show();
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(D_D_Manage.this, error.toString(), Toast.LENGTH_SHORT).show();

                    }
                }
        ) {
            protected Map<String, String> getParams() {
                Map<String, String> data = new HashMap<String, String>();
                data.put("dlr_email", sale.getDlr_email());
                data.put("cust_email", sale.getCust_email());
                data.put("dlr_name", sale.getDlr_name());
                data.put("cust_name", sale.getCust_name());
                data.put("vehicle", sale.getVehicle());
                data.put("model", sale.getModel());
                data.put("cost", sale.getCost());
                data.put("buying_option", sale.getBuying_option());
                return data;
            }

        };

        Volley.newRequestQueue(D_D_Manage.this).add(request);

    }




}