package com.example.deliverymanagement.fragments;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.deliverymanagement.R;
import com.example.deliverymanagement.ViewModels.DriverViewModel;
import com.example.deliverymanagement.ViewModels.RouteViewModel;
import com.example.deliverymanagement.models.DriverModel;
import com.example.deliverymanagement.models.RouteModel;

import java.util.List;

public class AddDriverFragment extends Fragment {

    // UI references.
    private EditText firstNameEditText;
    private EditText lastNameEditText;
    private Button addButton;
    private Spinner routeSpinner;

    // ViewModel reference.
    private DriverViewModel driverViewModel;

    public AddDriverFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_add_driver, container, false);


        // Initialize UI components
        firstNameEditText = rootView.findViewById(R.id.editTextFirstNameDriver);
        lastNameEditText = rootView.findViewById(R.id.editTextLastNameDriver);
        addButton = rootView.findViewById(R.id.buttonAddDriver);

        // Additional UI components from the XML layout
        ImageButton backButton = rootView.findViewById(R.id.imageButtonBackDriver);
        EditText addressEditText = rootView.findViewById(R.id.editTextAddressDriver);
        EditText telephoneEditText = rootView.findViewById(R.id.editTextTelephoneDriver);

        // Initialize ViewModel
        driverViewModel = new ViewModelProvider(this).get(DriverViewModel.class);

        // Initialize the spinner for routes
        routeSpinner = rootView.findViewById(R.id.spinnerRoutes);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<RouteModel> routeAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item);
        routeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        routeSpinner.setAdapter(routeAdapter);



        // Set up the button click listener
        addButton.setOnClickListener(v -> {
            // Validate inputs
            if (validateInput()) {
                // Create a new driver from the input fields
                DriverModel newDriver = new DriverModel(
                        firstNameEditText.getText().toString().trim(),
                        lastNameEditText.getText().toString().trim(),
                        addressEditText.getText().toString().trim(),
                        telephoneEditText.getText().toString().trim(),
                        ((RouteModel) routeSpinner.getSelectedItem()).getId() // Assuming RouteModel has an getId() method
                );

                // Add the new driver using the ViewModel
//                driverViewModel.addDriver(newDriver);

                // Clear fields after adding
                clearFields();
            }
        });

        // Set up the back button click listener
        backButton.setOnClickListener(v -> {
            // Check if the fragment is part of a FragmentActivity
            if (isAdded() && getActivity() != null) {
                // Use the FragmentManager to pop the current fragment off of the back stack
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });

        return rootView;
    }

    /**
     * Validates the input fields to ensure they are not empty.
     *
     * @return boolean representing whether the input is valid or not.
     */
    private boolean validateInput() {
        // Method content remains the same

        return false;
    }

    /**
     * Clears the input fields after a driver is added.
     */
    private void clearFields() {
        // Method content remains the same
    }
}
