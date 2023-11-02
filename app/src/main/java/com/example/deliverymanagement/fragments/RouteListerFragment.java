package com.example.deliverymanagement.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.deliverymanagement.R;

public class RouteListerFragment extends Fragment {



    public RouteListerFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // heloo
        return inflater.inflate(R.layout.fragment_route_lister, container, false);
    }
}