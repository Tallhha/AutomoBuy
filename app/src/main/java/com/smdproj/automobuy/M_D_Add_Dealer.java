package com.smdproj.automobuy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

import java.util.HashMap;
import java.util.Map;

public class M_D_Add_Dealer extends AppCompatActivity {


    EditText email;
    EditText cnic;
    EditText fname;
    EditText lname;

    EditText password;
    EditText type;
    EditText phone_no;
    EditText salary;

    TextView add_dealer_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_md_add_dealer);

        email = findViewById(R.id.email);
        cnic = findViewById(R.id.cnic);
        fname = findViewById(R.id.fname);
        lname = findViewById(R.id.lname);
        password = findViewById(R.id.password);
        type = findViewById(R.id.type);
        phone_no = findViewById(R.id.phone_no);
        salary = findViewById(R.id.salary);
        add_dealer_button = findViewById(R.id.add_dealer_button);
        add_dealer_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postData(new Employee("",fname.getText().toString(),lname.getText().toString(),email.getText().toString(),phone_no.getText().toString(),cnic.getText().toString(),"",type.getText().toString(),salary.getText().toString(),"0"));
                //postData2(new LogInfo(password.getText().toString(),email.getText().toString(),0,1));
            }
        });

    }
    public void postData(Employee emp) {

        IP ip = new IP();
        String INSERT_FORM = ip.getDB_IP() + "insert_employee.php";
        StringRequest request = new StringRequest(Request.Method.POST, INSERT_FORM, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(M_D_Add_Dealer.this, "DONE", Toast.LENGTH_SHORT).show();
                postData2(new LogInfo(password.getText().toString(),email.getText().toString(),0,1));
                //Toast.makeText(M_D_Add_Dealer.this, "DONE", Toast.LENGTH_SHORT).show();
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(M_D_Add_Dealer.this, error.toString(), Toast.LENGTH_SHORT).show();

                    }
                }
        ) {
            protected Map<String, String> getParams() {
                Map<String, String> data = new HashMap<String, String>();
                data.put("email", emp.getEmail());
                data.put("cnic", emp.getCNIC());
                data.put("fname", emp.getFirstName());
                data.put("lname", emp.getLastName());
                data.put("phone_no", emp.getPhoneNo());
                data.put("address", emp.getAddress());
                data.put("salary", emp.getSalary());
                data.put("type", emp.getType());
                data.put("total_sales", emp.getSales());
                data.put("is_mgr", "0");
                return data;
            }

        };

        Volley.newRequestQueue(M_D_Add_Dealer.this).add(request);

    }

    public void postData2(LogInfo log) {

        IP ip = new IP();
        String INSERT_FORM = ip.getDB_IP() + "register.php";
        StringRequest request = new StringRequest(Request.Method.POST, INSERT_FORM, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Toast.makeText(M_D_Add_Dealer.this, "DONE", Toast.LENGTH_SHORT).show();
                finish();
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(M_D_Add_Dealer.this, error.toString(), Toast.LENGTH_SHORT).show();

                    }
                }
        ) {
            protected Map<String, String> getParams() {
                Map<String, String> data = new HashMap<String, String>();
                data.put("email", log.getEmail());
                data.put("password", log.getPw());
                data.put("is_dlr", "1");
                data.put("is_mgr", "0");
                return data;
            }

        };

        Volley.newRequestQueue(M_D_Add_Dealer.this).add(request);

    }

}