package com.example.deliverymanagement.fragments;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import com.example.deliverymanagement.DeliveryManagementRepository;
import com.example.deliverymanagement.R;
import com.example.deliverymanagement.models.RouteModel;

public class MenuFragment extends Fragment {

    private DeliveryManagementRepository repository;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_menu, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Initialize the repository
        repository = new DeliveryManagementRepository(requireContext());

        // Initializing buttons
        Button btnAddRoute = view.findViewById(R.id.buttonAddRoute);
        Button btnAddSubscription = view.findViewById(R.id.MenuAddSubscription);
        Button btnAddDriver = view.findViewById(R.id.buttonAddDriver);
        Button btnRemoveSubscription = view.findViewById(R.id.buttonRemove);



        Button btnLister = view.findViewById(R.id.buttonList);
        Button btnQuit = view.findViewById(R.id.buttonQuit);

        // Setting onClickListeners
        btnAddRoute.setOnClickListener(v -> addRouteToDatabase());
        btnAddSubscription.setOnClickListener(v -> openFragment(new AddSubscriptionFragment()));
        btnRemoveSubscription.setOnClickListener(v -> openFragment(new RemoveSubscriptionFragment()));
        btnLister.setOnClickListener(v -> openFragment(new ListerFragment()));
        btnAddDriver.setOnClickListener(v -> openFragment(new DriversFragment()));
        // Add other listeners similar to the above lines

        // Initial setup for the "Add Driver" button
        RouteModel availableRoute = repository.getAvailableRoute();
        btnAddDriver.setEnabled(availableRoute != null);

        btnQuit.setOnClickListener(v -> {
            if (getActivity() != null) {
                getActivity().finish();
            }
        });
    }

    private void addRouteToDatabase() {
        RouteModel newRoute = new RouteModel();
        repository.insertRoute(newRoute);

        Toast.makeText(requireContext(), "Route added successfully!", Toast.LENGTH_SHORT).show();
    }

    private void openFragment(Fragment fragment) {
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        repository.shutDownExecutor();
    }
}
