package com.example.deliverymanagement.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.deliverymanagement.R;

public class RemoveSubscriptionFragment extends Fragment {

    private ListView lstSubscriptions;
    private EditText edtSubscriptionNumber;
    private ImageButton imageButtonBackRemove;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_remove_subscription, container, false);

        lstSubscriptions = view.findViewById(R.id.lstSubscriptions);
        edtSubscriptionNumber = view.findViewById(R.id.edtSubscriptionNumber);
        imageButtonBackRemove = view.findViewById(R.id.imageButtonBackRemove);

        Button btnRemoveSubscription = view.findViewById(R.id.buttonRemoveSubscription);
        Button btnShowSubscription = view.findViewById(R.id.btnShowSubscription);

        //... (Code for populating the ListView)

        btnRemoveSubscription.setOnClickListener(v -> {
            //... (Logic for removing subscription)
        });

        imageButtonBackRemove.setOnClickListener(v -> {
            //... (Navigation logic to return to the menu)
            if(getActivity() != null) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });

        btnShowSubscription.setOnClickListener(v -> {
            String subscriptionNumber = edtSubscriptionNumber.getText().toString();
            // Logic to show the subscription based on the entered number goes here
        });

        return view;
    }
}
