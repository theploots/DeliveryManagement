package com.example.deliverymanagement.fragments;

import static android.text.TextUtils.isEmpty;

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
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import com.example.deliverymanagement.DAO.ClientDao;
import com.example.deliverymanagement.DAO.DriverDao;
import com.example.deliverymanagement.DAO.ProductDao;
import com.example.deliverymanagement.DAO.RouteDao;
import com.example.deliverymanagement.DAO.RouteDetailsDao;
import com.example.deliverymanagement.DAO.SubscriptionDao;
import com.example.deliverymanagement.DeliveryManagementDatabase;
import com.example.deliverymanagement.R;
import com.example.deliverymanagement.ViewModels.DriverViewModel;
import com.example.deliverymanagement.ViewModels.RouteViewModel;
import com.example.deliverymanagement.adapters.RouteAdapter;
import com.example.deliverymanagement.models.DriverModel;
import com.example.deliverymanagement.models.RouteModel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;

public class AddDriverFragment extends Fragment {

    private boolean formValid = true;

    // UI references.
    private EditText firstNameEditText;
    private EditText lastNameEditText;

    private EditText addressEditText;
    private EditText telephoneEditText;

    private ExecutorService executor;
    private RouteDao routeDao;
    private DriverDao driverDao;
    private RouteDetailsDao routeDetailsDao;

    private ClientDao clientDao;
    private ProductDao productDao;
    private SubscriptionDao subscriptionDao;

    private int routeId;

    private Spinner routeSpinner;
    private Button addButton;
    private ImageButton backButton;

    private RouteAdapter routeAdapter;

    LiveData<List<RouteModel>> allEmptyRoutes;
    LiveData<RouteModel> routeById;
    LiveData<RouteModel> routeToUpdate;


    // ViewModel reference.
    private DriverViewModel driverViewModel;
    private RouteViewModel routeViewModel;

    public AddDriverFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_add_driver, container, false);

        DeliveryManagementDatabase db = DeliveryManagementDatabase.getInstance(getContext());
        clientDao = db.clientDao();
        productDao = db.productDao();
        subscriptionDao = db.subscriptionDao();
        routeDao = db.routeDao();
        driverDao = db.driverDao();
        routeDetailsDao = db.routeDetailsDao();

        // Initialize UI components
        firstNameEditText = rootView.findViewById(R.id.editTextFirstNameDriver);
        lastNameEditText = rootView.findViewById(R.id.editTextLastNameDriver);

        addButton = rootView.findViewById(R.id.buttonAddDriver);

        // Additional UI components from the XML layout
        backButton = rootView.findViewById(R.id.imageButtonBackDriver);
        addressEditText = rootView.findViewById(R.id.editTextAddressDriver);
        telephoneEditText = rootView.findViewById(R.id.editTextTelephoneDriver);

        // Initialize ViewModel
        driverViewModel = new ViewModelProvider(this).get(DriverViewModel.class);

        // Initialize the spinner for routes
        routeSpinner = rootView.findViewById(R.id.spinnerRoutes);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<Integer> routeAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item);
        routeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        routeSpinner.setAdapter(routeAdapter);

        allEmptyRoutes = routeDao.getAvailableRoutes();

        routeViewModel = new ViewModelProvider(this).get(RouteViewModel.class);

        // Observe the LiveData for routes from the ViewModel
        allEmptyRoutes.observe(getViewLifecycleOwner(), routes -> {

            List<Integer> routeIds = new ArrayList<>();
            for (RouteModel route : routes) {
                routeIds.add(route.getId());
            }

            routeAdapter.clear();
            routeAdapter.addAll(routeIds);
            routeAdapter.notifyDataSetChanged();
        });

        // Set up the button click listener
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                driverViewModel = new ViewModelProvider(AddDriverFragment.this).get(DriverViewModel.class);
                routeViewModel = new ViewModelProvider(AddDriverFragment.this).get(RouteViewModel.class);
                // Validate the input fields
                formValid = true;

                // Validate the first name
                if (isEmpty(firstNameEditText.getText().toString())) {
                    firstNameEditText.setError("First name is required.");
                    formValid = false;
                }

                // Validate the last name
                if (isEmpty(lastNameEditText.getText().toString())) {
                    lastNameEditText.setError("Last name is required.");
                    formValid = false;
                }

                // Validate the address
                if (isEmpty(addressEditText.getText().toString())) {
                    addressEditText.setError("Address is required.");
                    formValid = false;
                }

                // Validate the telephone
                if (isEmpty(telephoneEditText.getText().toString())) {
                    telephoneEditText.setError("Telephone is required.");
                    formValid = false;
                }

                // Validate the route
                if (routeSpinner.getSelectedItem() == null) {
                    formValid = false;
                }





                    if (formValid) {
                        int routeId = Integer.parseInt(routeSpinner.getSelectedItem().toString());


                        // Create a new driver
                        DriverModel driver = new DriverModel(
                                firstNameEditText.getText().toString(),
                                lastNameEditText.getText().toString(),
                                addressEditText.getText().toString(),
                                telephoneEditText.getText().toString(),
                                 routeId
                        );

                        // Insert the driver in the database
                        try {
                            driverViewModel.insertDriver(driver);
                            Toast.makeText(getContext(), "Driver added successfully.", Toast.LENGTH_LONG).show();
                        } catch (Exception e) {
                            Toast.makeText(getContext(), "Driver not added successfully.", Toast.LENGTH_LONG).show();
                        }

                        // Update the route to assign the driver
                        RouteModel newRoute = new RouteModel();
                        newRoute.setId(routeId);

                            routeById = routeViewModel.getRouteById(routeId);
                            routeById.observe(getViewLifecycleOwner(), route -> {
                                if (route != null) {
                                    routeViewModel.updateRoute(newRoute);
                                }
                            });



                        clearFields();

                    }








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

        return true;
    }

    /**
     * Clears the input fields after a driver is added.
     */
    private void clearFields() {
        // Method content remains the same
    }
}
