package com.example.deliverymanagement.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.deliverymanagement.R;

public class AddSubscription extends AppCompatActivity {

    EditText nameAdd,addressAdd,quantity;
    RadioButton magazine,newsPaper;

    Button addSubscription;
    ImageButton backAdd;
    boolean formValid=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_subscription);

        nameAdd=findViewById(R.id.editTextFullNameAdd);
        addressAdd=findViewById(R.id.editTextAddressAdd);
        quantity=findViewById(R.id.editTextQuantity);

        magazine=findViewById(R.id.radioButtonMagazine);
        newsPaper=findViewById(R.id.radioButtonNewsPaper);

        addSubscription=findViewById(R.id.buttonAddSubscription);
        backAdd=findViewById(R.id.imageButtonBacKAdd);

        addSubscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //here code needed to enter the data in the database

                if(!(magazine.isChecked()&&newsPaper.isChecked())){
                    formValid=false;
                    Toast toast = Toast.makeText(getApplicationContext(), "Please Select Type of Product", Toast.LENGTH_LONG);
                    toast.show();
                }
                if(nameAdd.getText()==null){
                    formValid=false;
                    Toast toast = Toast.makeText(getApplicationContext(), "Please Enter Name", Toast.LENGTH_LONG);
                    toast.show();
                }
                if(addressAdd.getText()==null){
                    formValid=false;
                    Toast toast = Toast.makeText(getApplicationContext(), "Please Enter Address", Toast.LENGTH_LONG);
                    toast.show();
                }
                if(quantity.getText()==null){
                    formValid=false;
                    Toast toast = Toast.makeText(getApplicationContext(), "Please Enter Quantity", Toast.LENGTH_LONG);
                    toast.show();
                }

                if(formValid){

                    String name=nameAdd.getText().toString();
                    String address=addressAdd.getText().toString();
                    String productType= magazine.isChecked()?"Magazine":"NewsPaper";
                    String quantityString=quantity.getText().toString();
                    int quantityInt=Integer.parseInt(quantityString);

                    //put the code to add the subscription to the database here



                    Toast toast = Toast.makeText(getApplicationContext(), "Subscription Added Successfully", Toast.LENGTH_LONG);
                    toast.show();

                    if(magazine.isChecked()){
                        magazine.toggle();
                    }else {
                        newsPaper.toggle();
                    }
                    nameAdd.setText("");
                    addressAdd.setText("");
                    quantity.setText("");
                }


            }
        });

        backAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddSubscription.this, List.class);
                startActivity(intent);
                finish();
            }
        });
    }
}