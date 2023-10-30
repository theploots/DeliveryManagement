package com.example.deliverymanagement.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.deliverymanagement.R;

public class MainActivity extends AppCompatActivity {

    Button addSubscription,addRoute,addDriver,removeSubscription,list,quit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addRoute=findViewById(R.id.buttonAddRoute);
        addSubscription=findViewById(R.id.MenuAddSubscription);
        addDriver=findViewById(R.id.buttonAddDriver);
        removeSubscription=findViewById(R.id.buttonRemove);
        list=findViewById(R.id.buttonList);
        quit=findViewById(R.id.buttonQuit);


        addRoute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //here put code to add route in database with only the id of the route
            }
        });

        addSubscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddSubscription.class);
                startActivity(intent);
                finish();
            }
        });

        addDriver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddDeliveryDriver.class);
                startActivity(intent);
                finish();
            }
        });

        removeSubscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RemoveSubscription.class);
                startActivity(intent);
                finish();
            }
        });

        list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, List.class);
                startActivity(intent);
                finish();
            }
        });

        quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //exit the application
                finish();


            }
        });

    }
}