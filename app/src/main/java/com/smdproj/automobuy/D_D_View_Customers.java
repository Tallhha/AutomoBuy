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

public class D_D_View_Customers extends AppCompatActivity {

    ArrayList<Employee> ls;
    Employee_Adapter adapter;
    RecyclerView rv;
    EditText id_to_delete;
    TextView fire;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dd_view_customers);
        
        rv = findViewById(R.id.employee_list);
        ls = new ArrayList<>();
        RecyclerView.LayoutManager lm = new LinearLayoutManager(D_D_View_Customers.this);
        rv.setLayoutManager(lm);
        getData();

        id_to_delete = findViewById(R.id.id_to_delete);
        fire = findViewById(R.id.approve_button);
        fire.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                removeData(id_to_delete.getText().toString());
            }
        });
    }

    private void getData() {
        IP ip = new IP();
        ls = new ArrayList<>();

        String URL_FORM = ip.getDB_IP() + "get_employee.php";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_FORM,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject object = new JSONObject(response);
                            if (object.getInt("success") == 1) {

                                //converting the string to json array object
                                JSONArray array = object.getJSONArray("employee");
                                MyDBHelper db = new MyDBHelper(D_D_View_Customers.this);
                                //traversing through all the object
                                for (int i = 0; i < array.length(); i++) {

                                    //getting product object from json array
                                    JSONObject employee = array.getJSONObject(i);

                                    //adding the product to product list
                                    ls.add(new Employee(
                                            employee.getString("id"),
                                            employee.getString("fname"),
                                            employee.getString("lname"),
                                            employee.getString("email"),
                                            employee.getString("phone_no"),
                                            employee.getString("cnic"),
                                            employee.getString("address"),
                                            employee.getString("type"),
                                            employee.getString("salary"),
                                            employee.getString("total_sales")
                                    ));

                                    db.insertEmp(employee.getString("email"), employee.getString("cnic"),"0",employee.getString("fname"),employee.getString("lname"),employee.getString("phone_no"),employee.getString("address"), employee.getString("salary"),employee.getString("type"),employee.getString("total_sales"));

                                }

                                //creating adapter object and setting it to recyclerview
                                adapter = new Employee_Adapter(D_D_View_Customers.this, ls);
                                rv.setAdapter(adapter);
                                //Toast.makeText(Search.this, "DONE", Toast.LENGTH_SHORT).show();

                            }
                            else{
                                Toast.makeText(D_D_View_Customers.this, "Failed to load data", Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(D_D_View_Customers.this, "NOT DONE", Toast.LENGTH_SHORT).show();
                    }
                });

        //adding our string request to queue
        Volley.newRequestQueue(this).add(stringRequest);

    }

    public void removeData(String id) {

        IP ip = new IP();
        String INSERT_FORM = ip.getDB_IP() + "delete_employee.php";
        StringRequest request = new StringRequest(Request.Method.POST, INSERT_FORM, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                getData();
                //Toast.makeText(D_D_View_Customers.this, "DONE", Toast.LENGTH_SHORT).show();
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(D_D_View_Customers.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
        ) {
            protected Map<String, String> getParams() {
                Map<String, String> data = new HashMap<String, String>();
                data.put("id", id);
                return data;
            }

        };

        Volley.newRequestQueue(D_D_View_Customers.this).add(request);

    }


}