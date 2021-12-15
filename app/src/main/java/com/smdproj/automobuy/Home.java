package com.smdproj.automobuy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Home extends AppCompatActivity {

    ImageView registration, search, menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        registration = findViewById(R.id.registeration);
        registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, TestDriveRegistration.class);
                startActivity(intent);
            }
        });

        search = findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, Search.class);
                startActivity(intent);
            }
        });

        menu = findViewById(R.id.menu);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDBHelper db = new MyDBHelper(Home.this);
                String value = db.checkUser();
                if(value.equals("mgr")) {
                    Intent intent = new Intent(Home.this, Dealer_Dashboard.class);
                    startActivity(intent);
                }
                else if(value.equals("dlr")){
                    Intent intent = new Intent(Home.this, Dealer_Dashboard.class);
                    startActivity(intent);
                }
                else{
                    Intent intent = new Intent(Home.this, Login.class);
                    startActivity(intent);
                }
            }

        });


    }
}