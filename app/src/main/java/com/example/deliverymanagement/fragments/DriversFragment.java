package com.example.deliverymanagement.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.deliverymanagement.R;
import com.example.deliverymanagement.ViewModels.DriverViewModel;
import com.example.deliverymanagement.ViewModels.RouteViewModel;
import com.example.deliverymanagement.ViewModels.SubscriptionViewModel;
import com.example.deliverymanagement.adapters.DriverAdapter;
import com.example.deliverymanagement.adapters.RouteAdapter;
import com.example.deliverymanagement.adapters.SubscriptionAdapter;

import java.util.List;

public class DriversFragment extends Fragment {

    TextView textViewDriverFirstFrag, textViewDriverLastFrag,textViewDriverAddressFrag, textViewDriverTelephoneFrag,textViewDriverRouteFrag;

    ListView driversListView;

    private DriverAdapter driverAdapter;

    private DriverViewModel driverViewModel;




    public DriversFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_drivers, container, false);

        textViewDriverFirstFrag = view.findViewById(R.id.textViewDriverFirstFrag);
        textViewDriverLastFrag = view.findViewById(R.id.textViewDriverLastFrag);
        textViewDriverAddressFrag = view.findViewById(R.id.textViewDriverAddressFrag);
        textViewDriverTelephoneFrag = view.findViewById(R.id.textViewTelephoneFrag);
        textViewDriverRouteFrag = view.findViewById(R.id.textViewRouteFrag);

        driversListView = view.findViewById(R.id.driversListView);

        driverViewModel = new ViewModelProvider(this).get(DriverViewModel.class);

        // Initialize the LiveData properly
        LiveData<List<Integer>> allDriversLiveData = driverViewModel.getAllDriverIds();
        allDriversLiveData.observe(getViewLifecycleOwner(), driverIds -> {

            driverAdapter = new DriverAdapter(getContext(), driverIds);
            driversListView.setAdapter(driverAdapter);
        });

        return view;
    }
}