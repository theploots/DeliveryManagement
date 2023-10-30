package com.example.deliverymanagement.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.deliverymanagement.R;

public class RemoveSubscription extends AppCompatActivity {

    TextView name,address,quantity,productType;
    EditText id;

    Button removeSubscription,buttonSearch;
    ImageButton backRemove;

    int idToSearch=0;
    boolean idFound=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_subscription);

        id=findViewById(R.id.editTextSearchIdRemove);

        name=findViewById(R.id.textViewFullNameResultRemove);
        address=findViewById(R.id.textViewAddressResultRemove);
        quantity=findViewById(R.id.textViewQuantityResultRemove);
        productType=findViewById(R.id.textViewProductResultRemove);

        removeSubscription=findViewById(R.id.buttonRemoveSubscription);
        buttonSearch=findViewById(R.id.buttonSearchIdRemove);
        backRemove=findViewById(R.id.imageButtonBackRemove);
        removeSubscription.setClickable(false);

        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //here code needed to search the data in the database
                //and set the text of the text views
                //if the id is not found then show a toast message
                //if the id is found then show the text views and the remove button

                try {
                    idToSearch=Integer.parseInt(id.getText().toString());
                    //here code needed to search the data in the database

                    if(idFound){
                        //name.setText(/*name from database*/);
                       // address.setText(/*address from database*/);
                       // quantity.setText(/*quantity from database*/);
                        //productType.setText(/*product type from database*/);
                        //idToSearch= database id
                        removeSubscription.setClickable(true);
                    }else{
                        Toast toast = Toast.makeText(getApplicationContext(), "Id Not Found", Toast.LENGTH_LONG);
                        toast.show();
                    }


                }
                catch (Exception e){
                    Toast toast = Toast.makeText(getApplicationContext(), "Please Enter Valid Id "+e.getLocalizedMessage(), Toast.LENGTH_LONG);
                    toast.show();
                }

            }
        });

        removeSubscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //here code needed to remove the data from the database with the id
                //if the data is removed successfully then show a toast message


                //if the data is removed successfully then show a toast message
                Toast toast = Toast.makeText(getApplicationContext(), "Subscription Removed Successfully", Toast.LENGTH_LONG);
                toast.show();


            }
        });

        backRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RemoveSubscription.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });







    }
}