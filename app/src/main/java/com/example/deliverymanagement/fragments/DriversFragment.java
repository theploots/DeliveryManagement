package com.example.deliverymanagement.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.deliverymanagement.R;

public class DriversFragment extends Fragment {

    TextView driverFirstName, driverLastName, driverAddress;


    public DriversFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        RecyclerView recyclerView = container.findViewById(R.id.recyclerViewDriver);

        driverFirstName = container.findViewById(R.id.textViewDriverFirstFrag);
        driverLastName = container.findViewById(R.id.textViewDriverLastFrag);
        driverAddress = container.findViewById(R.id.textViewDriverAddressFrag);

        return inflater.inflate(R.layout.fragment_drivers, container, false);
    }
}