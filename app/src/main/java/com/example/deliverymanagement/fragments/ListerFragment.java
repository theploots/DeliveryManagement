package com.example.deliverymanagement.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.deliverymanagement.R;

public class ListerFragment extends Fragment {

    Button drivers, routes, subscriptions,products;
    ImageButton imageButtonBackList;


    public ListerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        drivers = view.findViewById(R.id.buttonDrivers);
        routes = view.findViewById(R.id.buttonRoutes);
        subscriptions = view.findViewById(R.id.buttonSubscriptions);
        products = view.findViewById(R.id.buttonProducts);
        imageButtonBackList = view.findViewById(R.id.imageButtonBackList);

        drivers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                  FragmentManager fragmentManager = getChildFragmentManager();
                  FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                  fragmentTransaction.add(R.id.frame_lister, new DriversFragment());
                  fragmentTransaction.commit();
            }
        });

        routes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getChildFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frame_lister, new RouteListerFragment());
                fragmentTransaction.commit();
            }
        });

        subscriptions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getChildFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frame_lister, new SubscriptionListerFragment());
                fragmentTransaction.commit();
            }
        });

        products.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getChildFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frame_lister, new ProductListerFragment());
                fragmentTransaction.commit();
            }
        });

        imageButtonBackList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                if (fragmentManager != null && fragmentManager.getBackStackEntryCount() > 0) {
                    fragmentManager.popBackStack();
                } else {
                    // There's nothing in the back stack to go back to.
                    // Handle this case if needed.
                }
            }
        });



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_lister, container, false);






         return view;
    }
}