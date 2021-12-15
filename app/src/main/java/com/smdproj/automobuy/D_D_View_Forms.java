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

public class D_D_View_Forms extends AppCompatActivity {

    ArrayList<TestDriveForm> ls;
    TestDrive_Adapter adapter;
    RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dd_view_forms);

        rv = findViewById(R.id.form_list);
        ls = new ArrayList<>();
        RecyclerView.LayoutManager lm = new LinearLayoutManager(D_D_View_Forms.this);
        rv.setLayoutManager(lm);
        getData();

    }

    private void getData() {

        String URL_FORM = "http://192.168.1.7/automobuy/get_testdrive.php";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_FORM,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject object = new JSONObject(response);
                            if (object.getInt("success") == 1) {

                                //converting the string to json array object
                                JSONArray array = object.getJSONArray("testdrivereg");

                                //traversing through all the object
                                for (int i = 0; i < array.length(); i++) {

                                    //getting product object from json array
                                    JSONObject testdrive = array.getJSONObject(i);

                                    //adding the product to product list
                                    ls.add(new TestDriveForm(
                                            testdrive.getString("id"),
                                            testdrive.getString("vehicle"),
                                            testdrive.getString("fname"),
                                            testdrive.getString("lname"),
                                            testdrive.getString("email"),
                                            testdrive.getString("phone_no"),
                                            testdrive.getString("cnic"),
                                            testdrive.getString("regdate"),
                                            testdrive.getString("outlet")
                                            ));
                                }

                                //creating adapter object and setting it to recyclerview
                                adapter = new TestDrive_Adapter(D_D_View_Forms.this, ls);
                                rv.setAdapter(adapter);
                                //Toast.makeText(Search.this, "DONE", Toast.LENGTH_SHORT).show();

                            }
                            else{
                                Toast.makeText(D_D_View_Forms.this, "Failed to load data", Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(D_D_View_Forms.this, "NOT DONE", Toast.LENGTH_SHORT).show();
                    }
                });

        //adding our string request to queue
        Volley.newRequestQueue(this).add(stringRequest);

    }

}