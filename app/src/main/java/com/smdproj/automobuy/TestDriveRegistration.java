package com.smdproj.automobuy;

import androidx.appcompat.app.AppCompatActivity;

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

public class TestDriveRegistration extends AppCompatActivity {

    EditText fname, lname, email, cnic, phone, date, outlet, time, cars;
    TextView submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_drive_registration);

        fname = findViewById(R.id.fname);
        lname = findViewById(R.id.lname);
        email = findViewById(R.id.email);
        cnic = findViewById(R.id.cnic);
        phone = findViewById(R.id.phone);
        date = findViewById(R.id.date);
        outlet = findViewById(R.id.outlet);
        time = findViewById(R.id.time);
        cars = findViewById(R.id.cars);

        submit = findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postData(new TestDriveForm("",cars.getText().toString(), fname.getText().toString(), lname.getText().toString(), email.getText().toString(), phone.getText().toString(), cnic.getText().toString(), date.getText().toString(), outlet.getText().toString()));
            }
        });

    }

    public void postData(TestDriveForm form) {

        String urll = "http://192.168.56.1/automobuy/insert_testdrive.php";
        StringRequest request = new StringRequest(Request.Method.POST, urll, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(TestDriveRegistration.this, "DONE", Toast.LENGTH_SHORT).show();
                finish();
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(TestDriveRegistration.this, error.toString(), Toast.LENGTH_SHORT).show();

                    }
                }
        ) {
            protected Map<String, String> getParams() {
                Map<String, String> data = new HashMap<String, String>();
                data.put("email", form.getEmail());
                data.put("cnic", form.getCnic());
                data.put("fname", form.getFirstName());
                data.put("lname", form.getLastName());
                data.put("phone_no", form.getPhoneNo());
                data.put("vehicle", form.getVehicle());
                data.put("outlet", form.getOutlet());
                data.put("regdate", form.getDate());
                return data;
            }

        };

        Volley.newRequestQueue(TestDriveRegistration.this).add(request);

    }
}