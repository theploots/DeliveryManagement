package com.example.deliverymanagement.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.deliverymanagement.DAO.ClientDao;
import com.example.deliverymanagement.DAO.ProductDao;
import com.example.deliverymanagement.DAO.RouteDao;
import com.example.deliverymanagement.DAO.SubscriptionDao;
import com.example.deliverymanagement.R;
import com.example.deliverymanagement.ViewModels.RouteViewModel;
import com.example.deliverymanagement.models.ClientModel;
import com.example.deliverymanagement.models.RouteModel;
import com.example.deliverymanagement.models.SubscriptionModel;

import java.util.ArrayList;
import java.util.List;

public class AddSubscriptionFragment extends Fragment {

    // Attributes for client and subscription details
    private EditText firstNameAdd, lastNameAdd, addressAdd, magazineQuantity, newsPaperQuantity;
    private CheckBox magazine, newsPaper;
    private Button addSubscription;
    private ImageButton backAdd;
    private boolean formValid = true;

    // DAOs for accessing database operations
    private ClientDao clientDao;
    private ProductDao productDao;
    private SubscriptionDao subscriptionDao;

    // Newly added attributes for route selection
    private Spinner routeSpinner;
    private RouteDao routeDao; // Ensure this DAO is initialized appropriately
    private RouteViewModel routeViewModel;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_add_subscription, container, false);

        // Initialize client and subscription views
        firstNameAdd = rootView.findViewById(R.id.editTextFirstNameAdd);
        lastNameAdd = rootView.findViewById(R.id.editTextLastNameAdd);
        addressAdd = rootView.findViewById(R.id.editTextAddressAdd);
        magazineQuantity = rootView.findViewById(R.id.editTextMagazineQuantity);
        newsPaperQuantity = rootView.findViewById(R.id.editTextNewsPaperQuantity);
        magazine = rootView.findViewById(R.id.checkBoxMagazine);
        newsPaper = rootView.findViewById(R.id.checkBoxNewsPaper);
        addSubscription = rootView.findViewById(R.id.buttonAddSubscription);
        backAdd = rootView.findViewById(R.id.imageButtonBacKAdd);

        // Initialize the spinner for routes
        routeSpinner = rootView.findViewById(R.id.spinnerRoutes);

        // Observe changes in routes data and update the spinner
        routeDao.getAllRoutes().observe(getViewLifecycleOwner(), routes -> {
            List<String> routeNames = new ArrayList<>();
            for (RouteModel route : routes) {
                routeNames.add(route.getName()); // Assuming RouteModel has a getName() method
            }

            ArrayAdapter<String> routeAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, routeNames);
            routeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            routeSpinner.setAdapter(routeAdapter);
        });

        routeViewModel = new ViewModelProvider(requireActivity()).get(RouteViewModel.class);

        // Observe changes in available routes data and update the Add button's state
        // Choose ViewModel to manage your data, and remove the direct DAO observation.
        routeViewModel.getAvailableRoutes().observe(getViewLifecycleOwner(), routes -> {
            List<String> routeNames = new ArrayList<>();
            for (RouteModel route : routes) {
                routeNames.add(route.getName());
            }
            ArrayAdapter<String> routeAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, routeNames);
            routeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            routeSpinner.setAdapter(routeAdapter);

            // Handle enabling/disabling the addSubscription button here.
            if (routes.isEmpty()) {
                addSubscription.setEnabled(false);
            } else {
                addSubscription.setEnabled(true);
            }
        });


        // Click listener for adding subscription
        addSubscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                formValid = true;

                if (!magazine.isChecked() && !newsPaper.isChecked()) {
                    formValid = false;
                    Toast.makeText(getContext(), "Please Select Type of Product", Toast.LENGTH_LONG).show();
                }
                if (magazine.isChecked() && magazineQuantity.getText().toString().trim().isEmpty()) {
                    formValid = false;
                    Toast.makeText(getContext(), "Please Enter Magazine Quantity", Toast.LENGTH_LONG).show();
                }
                if (newsPaper.isChecked() && newsPaperQuantity.getText().toString().trim().isEmpty()) {
                    formValid = false;
                    Toast.makeText(getContext(), "Please Enter News Paper Quantity", Toast.LENGTH_LONG).show();
                }
                if (firstNameAdd.getText().toString().trim().isEmpty()) {
                    formValid = false;
                    Toast.makeText(getContext(), "Please Enter First Name", Toast.LENGTH_LONG).show();
                }
                if (lastNameAdd.getText().toString().trim().isEmpty()) {
                    formValid = false;
                    Toast.makeText(getContext(), "Please Enter Last Name", Toast.LENGTH_LONG).show();
                }
                if (addressAdd.getText().toString().trim().isEmpty()) {
                    formValid = false;
                    Toast.makeText(getContext(), "Please Enter Address", Toast.LENGTH_LONG).show();
                }

                if (formValid) {
                    String firstName = firstNameAdd.getText().toString().trim();
                    String lastName = lastNameAdd.getText().toString().trim();
                    String address = addressAdd.getText().toString().trim();
                    String selectedRouteName = routeSpinner.getSelectedItem().toString();

                    ClientModel client = new ClientModel(firstName, lastName, address);
                    long clientId = clientDao.insertClient(client);

                    if (magazine.isChecked()) {
                        int magQty = Integer.parseInt(magazineQuantity.getText().toString());
                        long productId = productDao.getProductIdByName("Magazine");
                        SubscriptionModel magazineSubscription = new SubscriptionModel((int)clientId, (int)productId, magQty);
                        subscriptionDao.insert(magazineSubscription);
                    }

                    if (newsPaper.isChecked()) {
                        int newsQty = Integer.parseInt(newsPaperQuantity.getText().toString());
                        long productId = productDao.getProductIdByName("Newspaper");
                        SubscriptionModel newspaperSubscription = new SubscriptionModel((int)clientId, (int)productId, newsQty);
                        subscriptionDao.insert(newspaperSubscription);
                    }

                    Toast.makeText(getContext(), "Subscription Added Successfully", Toast.LENGTH_LONG).show();

                }
            }
        });


        backAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });

        return rootView;
    }
}
