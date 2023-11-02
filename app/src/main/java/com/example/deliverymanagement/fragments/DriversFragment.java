package com.example.deliverymanagement.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.deliverymanagement.R;
import com.example.deliverymanagement.ViewModels.DriverViewModel;
import com.example.deliverymanagement.ViewModels.RouteViewModel;
import com.example.deliverymanagement.ViewModels.SubscriptionViewModel;
import com.example.deliverymanagement.adapters.DriverAdapter;
import com.example.deliverymanagement.adapters.RouteAdapter;
import com.example.deliverymanagement.adapters.SubscriptionAdapter;
import com.example.deliverymanagement.models.DriverModel;
import com.example.deliverymanagement.models.SubscriptionModel;

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
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        textViewDriverFirstFrag = view.findViewById(R.id.textViewDriverFirstFrag);
        textViewDriverLastFrag = view.findViewById(R.id.textViewDriverLastFrag);
        textViewDriverAddressFrag = view.findViewById(R.id.textViewDriverAddressFrag);
        textViewDriverTelephoneFrag = view.findViewById(R.id.textViewTelephoneFrag);
        textViewDriverRouteFrag = view.findViewById(R.id.textViewRouteFrag);

        driversListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Integer driverId = (Integer) adapterView.getItemAtPosition(i);

                int id = i + 1;

                LiveData<DriverModel> driverLiveData = driverViewModel.getDriverById(id);

                driverLiveData.observe(getViewLifecycleOwner(), driver -> {
                    textViewDriverFirstFrag.setText(driver.getFirstName());
                    textViewDriverLastFrag.setText(driver.getLastName());
                    textViewDriverAddressFrag.setText(driver.getAddress());
                    textViewDriverTelephoneFrag.setText(driver.getPhoneNumber());
                    //textViewDriverRouteFrag.setText(driver.getRouteId().toString());
                });


            }
        });

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