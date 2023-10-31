package com.example.deliverymanagement.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.deliverymanagement.R;

public class IntroActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        Button proceedButton = findViewById(R.id.proceedButton);
        proceedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start MainActivity when the button is clicked
                Intent intent = new Intent(IntroActivity.this, MainActivity.class);
                startActivity(intent);
                finish(); // Close the IntroActivity
            }
        });
    }
}