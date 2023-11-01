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
import com.example.deliverymanagement.ViewModels.RouteViewModel;
import com.example.deliverymanagement.models.ClientModel;
import com.example.deliverymanagement.models.RouteModel;
import com.example.deliverymanagement.models.SubscriptionModel;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class AddSubscriptionFragment extends Fragment {


    // Attributes for client and subscription details
    private EditText firstNameAdd, lastNameAdd, addressAdd, magazineQuantity, newsPaperQuantity;
    private CheckBox magazine, newsPaper;
    private Button addSubscription;
    private ImageButton backAdd;
    private boolean formValid = true;

    // DAOs for accessing database operations
    private RouteDao routeDao;
    private DriverDao driverDao;
    private RouteDetailsDao routeDetailsDao;

    private ClientDao clientDao;
    private ProductDao productDao;
    private SubscriptionDao subscriptionDao;

    // Newly added attributes for route selection
    private Spinner routeSpinner;
    private RouteViewModel routeViewModel;


    LiveData<List<RouteModel>> allAvailableRoutes;


    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_add_subscription, container, false);


        // Initialize your DAOs here
        DeliveryManagementDatabase db = DeliveryManagementDatabase.getInstance(getContext());
        clientDao = db.clientDao();
        productDao = db.productDao();
        subscriptionDao = db.subscriptionDao();
        routeDao = db.routeDao();
        driverDao = db.driverDao();
        routeDetailsDao = db.routeDetailsDao();

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

        ArrayAdapter<Integer> routeAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item);
        routeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        routeSpinner.setAdapter(routeAdapter);

        // Initialize the LiveData
        allAvailableRoutes = routeDao.getAvailableRoutes();

        // Observe changes in available routes data
        allAvailableRoutes.observe(getViewLifecycleOwner(), routes -> {
            List<Integer> routeIds = new ArrayList<>();
            for (RouteModel route : routes) {
                routeIds.add(route.getId());
            }

            // Update the ArrayAdapter with the route IDs
            routeAdapter.clear();
            routeAdapter.addAll(routeIds);

            // Notify the adapter that the data set has changed
            routeAdapter.notifyDataSetChanged();

            // Handle enabling/disabling the addSubscription button here.
            if (routeIds.isEmpty()) {
                addSubscription.setEnabled(false);
            } else {
                addSubscription.setEnabled(true);
            }
        });


        routeViewModel = new ViewModelProvider(requireActivity()).get(RouteViewModel.class);

        // Observe changes in available routes data and update the Add button's state
        // Choose ViewModel to manage your data, and remove the direct DAO observation.
       /* routeViewModel.getAvailableRoutes().observe(getViewLifecycleOwner(), routes -> {
            List<String> routeNames = new ArrayList<>();
            for (RouteModel route : routes) {
                routeNames.add(route.getName());
            }
            ArrayAdapter<String> routeAdapter1 = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, routeNames);
            routeAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            routeSpinner.setAdapter(routeAdapter1);

            // Handle enabling/disabling the addSubscription button here.
            if (routes.isEmpty()) {
                addSubscription.setEnabled(false);
            } else {
                addSubscription.setEnabled(true);
            }
            // Inside onCreateView

*/


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
                        LiveData<List<RouteModel>> routeId = routeDao.getAvailableRoutes();
                        SubscriptionModel magazineSubscription = new SubscriptionModel((int) clientId, (int) productId, magQty);
                        subscriptionDao.insert(magazineSubscription);
                    }

                    if (newsPaper.isChecked()) {
                        int newsQty = Integer.parseInt(newsPaperQuantity.getText().toString());
                        long productId = productDao.getProductIdByName("Newspaper");
                        SubscriptionModel newspaperSubscription = new SubscriptionModel((int) clientId, (int) productId, newsQty);
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


