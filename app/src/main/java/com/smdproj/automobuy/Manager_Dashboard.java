package com.smdproj.automobuy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Manager_Dashboard extends AppCompatActivity {

    TextView view_employees_button;
    TextView add_dealer_button;
    TextView add_vehicle_button;
    TextView view_sales_button;
    TextView view_feedback_button;
    TextView logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_dashboard);

        view_employees_button = findViewById(R.id.view_employees_button);
        view_employees_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Manager_Dashboard.this, D_D_View_Customers.class);
                startActivity(intent);
            }
        });

        add_dealer_button = findViewById(R.id.add_dealer_button);
        add_dealer_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Manager_Dashboard.this, M_D_Add_Dealer.class);
                startActivity(intent);
            }
        });


        add_vehicle_button = findViewById(R.id.add_vehicle_button);
        add_vehicle_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Manager_Dashboard.this, M_D_Add_Vehicle.class);
                startActivity(intent);
            }
        });

        view_sales_button = findViewById(R.id.view_sales_button);
        view_sales_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Manager_Dashboard.this, M_D_View_Sales.class);
                startActivity(intent);
            }
        });

        view_feedback_button = findViewById(R.id.view_feedback_button);
        view_feedback_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Manager_Dashboard.this, M_D_View_Feedback.class);
                startActivity(intent);
            }
        });

        logout = findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDBHelper db = new MyDBHelper(Manager_Dashboard.this);
                db.logout();
                Intent intent = new Intent(Manager_Dashboard.this, Home.class);
                startActivity(intent);
            }
        });


    }
}