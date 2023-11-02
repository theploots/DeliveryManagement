package com.example.deliverymanagement.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import androidx.fragment.app.Fragment;

import com.example.deliverymanagement.R;

public class RouteListerFragment extends Fragment {

    private ListView routesListView;
    private ListView addressesListView;
    private TextView deliveryPersonName;

    // Dummy data for demonstration purposes
    private String[] routesArray = new String[]{"Route 101", "Route 102", "Route 103"};
    private String[] addressesArray = new String[]{"123 Main St - Books", "456 Oak St - Electronics", "789 Pine St - Groceries"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_route_lister, container, false);

        // Initialize the list and TextViews
        routesListView = view.findViewById(R.id.routesListView);
        addressesListView = view.findViewById(R.id.addressesListView);
        deliveryPersonName = view.findViewById(R.id.deliveryPersonName);

        // Set the adapter for the routes list view
        ArrayAdapter<String> routesAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, routesArray);
        routesListView.setAdapter(routesAdapter);

        // Set the adapter for the addresses list view
        ArrayAdapter<String> addressesAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, addressesArray);
        addressesListView.setAdapter(addressesAdapter);

        // Set up a listener for list item clicks
        routesListView.setOnItemClickListener((parent, view1, position, id) -> {
            // This is where you'd fetch and update the details for the selected route
            // For now, we're just updating the delivery person's name statically
            deliveryPersonName.setText("Driver for " + routesArray[position]);

            // You would also update the addresses list for the selected route here
        });

        return view;
    }
}
