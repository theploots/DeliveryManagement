package com.example.deliverymanagement.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.example.deliverymanagement.DeliveryManagementRepository;
import com.example.deliverymanagement.R;
import com.example.deliverymanagement.fragments.AddSubscriptionFragment;
import com.example.deliverymanagement.fragments.MenuFragment;
import com.example.deliverymanagement.models.RouteModel;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity"; // Tag for logging

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            try {
                MenuFragment menuFragment = new MenuFragment();

                // Begin fragment transaction
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

                // Replace the contents of the container with the new fragment
                ft.replace(R.id.fragment_container, menuFragment);

                // Adding transaction to back stack for back navigation
                ft.addToBackStack(null);
                ft.commit();
            } catch (Exception e) {
                Log.e(TAG, "Error while adding MenuFragment", e);
            }
        }
    }
//    private DeliveryManagementRepository repository;
//    private Button addDriver;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        // Initialize the repository
//        repository = new DeliveryManagementRepository(getApplicationContext());
//
//        // Initializing buttons
//        Button addRoute = findViewById(R.id.buttonAddRoute);
//        Button addSubscription = findViewById(R.id.MenuAddSubscription);
//        addDriver = findViewById(R.id.buttonAddDriver);
//        Button removeSubscription = findViewById(R.id.buttonRemove);
//        Button list = findViewById(R.id.buttonList);
//        Button quit = findViewById(R.id.buttonQuit);
//
//        // Initial setup for the "Add Driver" button
//        RouteModel availableRoute = repository.getAvailableRoute();
//        addDriver.setEnabled(availableRoute != null);
//
//        // Setting onClickListeners
//        setupButtonClickListeners(addRoute, addSubscription, addDriver, removeSubscription, list, quit);
//    }
//
//    private void setupButtonClickListeners(Button... buttons) {
//        for (Button button : buttons) {
//            button.setOnClickListener(view -> {
//                int buttonId = button.getId();
//
//                if (buttonId == R.id.buttonAddRoute) {
//                    addRouteToDatabase();
//                } else if (buttonId == R.id.MenuAddSubscription) {
//                    navigateToAddSubscriptionFragment();
////                } else if (buttonId == R.id.buttonAddDriver) {
////                    navigateToAddDriver();
////                } else if (buttonId == R.id.buttonRemove) {
////                    navigateToRemoveSubscription();
////                } else if (buttonId == R.id.buttonList) {
////                    navigateToList();
//                } else if (buttonId == R.id.buttonQuit) {
//                    finish();  // Exit the application
//                }
//            });
//        }
//    }
//
//    private void addRouteToDatabase() {
//        RouteModel newRoute = new RouteModel();
//        repository.insertRoute(newRoute);
//
//        // Update the status of the "Add Driver" button after a new route is added:
//        addDriver.setEnabled(true);
//
//        Toast.makeText(this, "Route added successfully!", Toast.LENGTH_SHORT).show();
//    }
//
//    private void navigateToAddSubscriptionFragment() {
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.replace(R.id.container, new AddSubscriptionFragment());
//        fragmentTransaction.addToBackStack(null);
//        fragmentTransaction.commit();
//    }

//    private void navigateToAddDriver() {
//        Intent intent = new Intent(MainActivity.this, AddDeliveryDriver.class);
//        startActivity(intent);
//    }
//
//    private void navigateToRemoveSubscription() {
//        Intent intent = new Intent(MainActivity.this, RemoveSubscription.class);
//        startActivity(intent);
//    }
//
//    private void navigateToList() {
//        Intent intent = new Intent(MainActivity.this, List.class);
//        startActivity(intent);
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        repository.shutDownExecutor();
//    }
}
