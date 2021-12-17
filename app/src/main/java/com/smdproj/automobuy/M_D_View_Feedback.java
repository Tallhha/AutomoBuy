package com.smdproj.automobuy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

import java.util.ArrayList;

public class M_D_View_Feedback extends AppCompatActivity {

    ArrayList<Store_Feedback> ls;
    Feedback_Adapter adapter;
    RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_md_view_feedback);

        rv = findViewById(R.id.feed_list);
        ls = new ArrayList<>();
        RecyclerView.LayoutManager lm = new LinearLayoutManager(M_D_View_Feedback.this);
        rv.setLayoutManager(lm);
        getData();

    }
    private void getData() {
        IP ip = new IP();

        String URL_FORM = ip.getDB_IP() + "get_feedback.php";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_FORM,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {

                            JSONObject object = new JSONObject(response);
                            if (object.getInt("success") == 1) {

                                //converting the string to json array object
                                JSONArray array = object.getJSONArray("feedback");

                                //traversing through all the object
                                for (int i = 0; i < array.length(); i++) {
                                    //getting product object from json array
                                    JSONObject feed = array.getJSONObject(i);

                                    //adding the product to product list
                                    ls.add(new Store_Feedback(
                                            feed.getString("id"),
                                            feed.getString("fname"),
                                            feed.getString("lname"),
                                            feed.getString("desc")
                                    ));

                                }

                                //creating adapter object and setting it to recyclerview
                                adapter = new Feedback_Adapter(M_D_View_Feedback.this, ls);
                                rv.setAdapter(adapter);
                                //Toast.makeText(Search.this, "DONE", Toast.LENGTH_SHORT).show();

                            }
                            else{
                                Toast.makeText(M_D_View_Feedback.this, "Failed to load data", Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(M_D_View_Feedback.this, "NOT DONE", Toast.LENGTH_SHORT).show();
                    }
                });

        //adding our string request to queue
        Volley.newRequestQueue(this).add(stringRequest);

    }

}