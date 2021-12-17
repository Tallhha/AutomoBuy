package com.smdproj.automobuy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PurchaseCar extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    private Spinner spinner;
    private static final String[] paths = {"Full Payment", "10k/ month", "50k / month"};
    Vehicles vehicles;
    ImageView carImage;
    TextView carModel, carName, carCost, carCompany, buy_button;
    EditText email, cnic, fname, lname, phone_no;
    String choice;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_car);

        spinner = (Spinner)findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,paths);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        carImage = findViewById(R.id.carImage);
        vehicles = (Vehicles) getIntent().getSerializableExtra("vehicle");
        Glide.with(PurchaseCar.this)
                .load(vehicles.getImg())
                .into(carImage);

        carModel = findViewById(R.id.carModel);
        carName = findViewById(R.id.carName);
        carCost = findViewById(R.id.carCost);
        carCompany = findViewById(R.id.carCompany);

        carModel.setText(vehicles.getModel());
        carName.setText(vehicles.getName());
        carCost.setText(vehicles.getCost());
        carCompany.setText(vehicles.getCompany());

        email = findViewById(R.id.email);
        cnic = findViewById(R.id.cnic);
        phone_no = findViewById(R.id.phone_no);
        fname = findViewById(R.id.fname);
        lname = findViewById(R.id.lname);
        buy_button = findViewById(R.id.buy_button);
        buy_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postData(new BookingForm("", vehicles.getName(),fname.getText().toString(), lname.getText().toString(),email.getText().toString(),phone_no.getText().toString(),cnic.getText().toString(),choice, vehicles.getType(),vehicles.getModel(), vehicles.getCost(), vehicles.getColor()));
            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {

        switch (position) {
            case 0:
                choice = paths[0];
                break;
            case 1:
                choice = paths[1];
                break;
            case 2:
                choice = paths[2];
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // TODO Auto-generated method stub
    }

    public void postData(BookingForm form) {

        IP ip = new IP();
        String INSERT_FORM = ip.getDB_IP() + "insert_booking.php";
        StringRequest request = new StringRequest(Request.Method.POST, INSERT_FORM, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(PurchaseCar.this, "DONE", Toast.LENGTH_SHORT).show();
                finish();
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(PurchaseCar.this, error.toString(), Toast.LENGTH_SHORT).show();

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
                data.put("type", form.getType());
                data.put("model", form.getModel());
                data.put("cost", form.getCost());
                data.put("color", form.getColor());
                data.put("buying_option", form.getBuyingOption());
                return data;
            }

        };

        Volley.newRequestQueue(PurchaseCar.this).add(request);

    }

}