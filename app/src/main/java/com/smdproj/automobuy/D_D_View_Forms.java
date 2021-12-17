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

import java.nio.file.attribute.AclEntry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class D_D_View_Forms extends AppCompatActivity {

    ArrayList<TestDriveForm> ls;
    TestDrive_Adapter adapter;
    RecyclerView rv;
    EditText id_to_delete;
    TextView approve_button, reject_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dd_view_forms);

        rv = findViewById(R.id.form_list);
        ls = new ArrayList<>();
        RecyclerView.LayoutManager lm = new LinearLayoutManager(D_D_View_Forms.this);
        rv.setLayoutManager(lm);
        getData();

        id_to_delete = findViewById(R.id.id_to_delete);
        approve_button = findViewById(R.id.approve_button);
        approve_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                removeData(id_to_delete.getText().toString());
                String emailBody="";

                emailBody+=("\t\t\t  ** AUTOMOBUY **\n");
                emailBody+=("\t\t\t** TEST DRIVE FORM **\n");
                emailBody+=("Your Test Drive request has been approved!" +"\n");
                emailBody+=("Have a good day sir!" +"\n");
                emailBody+=("\n\t\t\t**Thanks For Support**\n");
                emailBody+=("\t\t\t      ** MERCEDES **\n");
                String email = "i180573@nu.edu.pk";
                JavaMailAPI javaMailAPI = new JavaMailAPI(D_D_View_Forms.this, email, "Request Approved", emailBody);

                javaMailAPI.execute();
            }
        });

        reject_button = findViewById(R.id.reject_button);
        reject_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeData(id_to_delete.getText().toString());
                String emailBody="";
                emailBody+=("\t\t\t  ** AUTOMOBUY **\n");
                emailBody+=("\t\t\t** TEST DRIVE FORM **\n");
                emailBody+=("It is an unfortunate occuring that we have no choice but to reject your test drive request" +"\n");
                emailBody+=("There were some fields missing and/or were given invalid entries which is why we can not accept it." +"\n");
                emailBody+=("Please be sure to recheck your purchase form and try again" +"\n");
                emailBody+=("We will try our best to cooperate with you." +"\n");
                emailBody+=("Have a good day sir!" +"\n");
                emailBody+=("\n\t\t\t**Thanks For Support**\n");
                emailBody+=("\t\t\t      ** MERCEDES **\n");
                String email = "talhamustafa3@gmail.com";
                JavaMailAPI javaMailAPI = new JavaMailAPI(D_D_View_Forms.this, email, "Request Denied", emailBody);

                javaMailAPI.execute();
            }
        });

    }

    private void getData() {
        IP ip = new IP();
        ls = new ArrayList<>();

        String URL_FORM = ip.getDB_IP() + "get_testdrive.php";
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

    public void removeData(String id) {

        IP ip = new IP();
        String INSERT_FORM = ip.getDB_IP() + "delete_testdrive.php";
        StringRequest request = new StringRequest(Request.Method.POST, INSERT_FORM, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                getData();
                //Toast.makeText(D_D_View_Forms.this, "DONE", Toast.LENGTH_SHORT).show();
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(D_D_View_Forms.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
        ) {
            protected Map<String, String> getParams() {
                Map<String, String> data = new HashMap<String, String>();
                data.put("id", id);
                return data;
            }

        };

        Volley.newRequestQueue(D_D_View_Forms.this).add(request);

    }

}