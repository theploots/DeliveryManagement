package com.example.deliverymanagement.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.deliverymanagement.R;

public class AddDeliveryDriver extends AppCompatActivity {

    EditText driverName, driverAddress;
    EditText telephoneNumber;

    Button addDriver;
    ImageButton backDriver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_delivery_driver);

        driverName = findViewById(R.id.editTextNameDriver);
        driverAddress=findViewById(R.id.editTextAddressDriver);
        telephoneNumber=findViewById(R.id.editTextTelephoneDriver);
        addDriver=findViewById(R.id.buttonAddDriver);
        backDriver=findViewById(R.id.imageButtonBackDriver);

        addDriver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //put the code to add the driver to the database here

                driverName.setText("");
                driverAddress.setText("");
                telephoneNumber.setText("");

                Toast toast = Toast.makeText(getApplicationContext(), "Driver Added Successfully", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
        backDriver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddDeliveryDriver.this, MainActivity.class);
                startActivity(intent);
                finish();

            }
        });
    }
}