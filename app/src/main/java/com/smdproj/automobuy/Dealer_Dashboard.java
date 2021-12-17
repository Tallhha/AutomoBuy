package com.smdproj.automobuy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Dealer_Dashboard extends AppCompatActivity {

    TextView view_reg_form_button, manage_bookings_button, logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dealer_dashboard);

        view_reg_form_button = findViewById(R.id.view_reg_form_button);
        view_reg_form_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dealer_Dashboard.this, D_D_View_Forms.class);
                startActivity(intent);
            }
        });

        manage_bookings_button = findViewById(R.id.manage_bookings_button);
        manage_bookings_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dealer_Dashboard.this, D_D_Manage.class);
                startActivity(intent);
            }
        });

        logout = findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDBHelper db = new MyDBHelper(Dealer_Dashboard.this);
                db.logout();
                Intent intent = new Intent(Dealer_Dashboard.this, Home.class);
                startActivity(intent);
            }
        });
    }
}