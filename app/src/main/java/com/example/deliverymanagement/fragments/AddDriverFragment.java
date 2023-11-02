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

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.deliverymanagement.R;
import com.example.deliverymanagement.ViewModels.DriverViewModel;
import com.example.deliverymanagement.models.DriverModel;

public class AddDriverFragment extends Fragment {

    // UI references.
    private EditText firstNameEditText;
    private EditText lastNameEditText;
    private Button addButton;

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

//        // Initialize the spinner for routes
//        routeSpinner = rootView.findViewById(R.id.spinnerRoutes);
//
//        ArrayAdapter<Integer> routeAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item);
//        routeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        routeSpinner.setAdapter(routeAdapter);
//
//        // Initialize the LiveData
//        allAvailableRoutes = routeDao.getAvailableRoutes();

        // Set up the button click listener
        addButton.setOnClickListener(v -> {
            // Validate inputs
            if (validateInput()) {
                // Create a new driver from the input fields
                DriverModel newDriver = new DriverModel(
                        firstNameEditText.getText().toString().trim(),
                        lastNameEditText.getText().toString().trim(),
                        addressEditText.getText().toString().trim(),
                        telephoneEditText.getText().toString().trim()
                );

                // Add the new driver using the ViewModel
                driverViewModel.addDriver(newDriver);
            }
        });

        // Set up the back button click listener
        backButton.setOnClickListener(v -> {
            // Handle the back button action
            // For example, you might want to close the fragment or navigate back
        });

        return rootView;
    }

    /**
     * Validates the input fields to ensure they are not empty.
     *
     * @return boolean representing whether the input is valid or not.
     */
    private boolean validateInput() {
        boolean isValid = true;
        // Reset errors.
        firstNameEditText.setError(null);
        lastNameEditText.setError(null);

        // Store values at the time of the login attempt.
        String firstName = firstNameEditText.getText().toString().trim();
        String lastName = lastNameEditText.getText().toString().trim();

        // Check for a valid first name.
        if (TextUtils.isEmpty(firstName)) {
            firstNameEditText.setError(getString(R.string.first_name_error_field_required));
            isValid = false;
        }

        // Check for a valid last name.
        if (TextUtils.isEmpty(lastName)) {
            lastNameEditText.setError(getString(R.string.last_name_error_field_required));
            isValid = false;
        }

        return isValid;
    }

    /**
     * Clears the input fields after a driver is added.
     */
    private void clearFields() {
        firstNameEditText.setText("");
        lastNameEditText.setText("");

    }
}
