package com.example.deliverymanagement.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import com.example.deliverymanagement.R;
import com.example.deliverymanagement.ViewModels.ClientViewModel;
import com.example.deliverymanagement.ViewModels.ProductViewModel;
import com.example.deliverymanagement.ViewModels.SubscriptionViewModel;
import com.example.deliverymanagement.models.ClientModel;
import com.example.deliverymanagement.models.ProductModel;
import com.example.deliverymanagement.models.SubscriptionModel;

public class RemoveSubscriptionFragment extends Fragment {
    TextView textViewFullNameResultRemove,textViewAddressResultRemove, textViewProductResultRemove, textViewQuantityResultRemove,editTextSearchIdRemove;


    private EditText edtSubscriptionNumber;
    private ImageButton imageButtonBackRemove;
    private SubscriptionViewModel subscriptionViewModel;

    private ClientViewModel clientViewModel;

    private ProductViewModel productViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_remove_subscription, container, false);

        SubscriptionViewModel subscriptionViewModel = new ViewModelProvider(this).get(SubscriptionViewModel.class);
        ClientViewModel clientViewModel = new ViewModelProvider(this).get(ClientViewModel.class);
        ProductViewModel productViewModel = new ViewModelProvider(this).get(ProductViewModel.class);




        textViewFullNameResultRemove = view.findViewById(R.id.textViewFullNameResultRemove);
        textViewAddressResultRemove = view.findViewById(R.id.textViewAddressResultRemove);
        textViewProductResultRemove = view.findViewById(R.id.textViewProductResultRemove);
        textViewQuantityResultRemove = view.findViewById(R.id.textViewQuantityResultRemove);

        edtSubscriptionNumber = view.findViewById(R.id.editTextSearchIdRemove);
        imageButtonBackRemove = view.findViewById(R.id.imageButtonBackRemove);

        Button btnRemoveSubscription = view.findViewById(R.id.buttonRemoveSubscription);
        Button btnShowSubscription = view.findViewById(R.id.buttonSearchIdRemove);

        //... (Code for populating the ListView)

        btnRemoveSubscription.setOnClickListener(v -> {
            int id = Integer.valueOf(edtSubscriptionNumber.getText().toString());
            LiveData<SubscriptionModel> subscriptionLiveData = subscriptionViewModel.getSubscriptionById(id);

            // Observe the LiveData to ensure you have the data
            subscriptionLiveData.observe(getViewLifecycleOwner(), subscription -> {
                if (subscription != null) {
                    subscriptionViewModel.deleteSubscription(subscription);
                    Toast toast = Toast.makeText(getContext(), "Subscription removed", Toast.LENGTH_LONG);
                    toast.show();
                }
            });
        });

        imageButtonBackRemove.setOnClickListener(v -> {
            //... (Navigation logic to return to the menu)
            if(getActivity() != null) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });

        btnShowSubscription.setOnClickListener(v -> {
            int id = Integer.valueOf(edtSubscriptionNumber.getText().toString());
            int  subscriptionNumber = Integer.valueOf(edtSubscriptionNumber.getText().toString());
            LiveData<SubscriptionModel> subscriptionLiveData = subscriptionViewModel.getSubscriptionById(id);

            // Observe the LiveData to ensure you have the data
            subscriptionLiveData.observe(getViewLifecycleOwner(), subscription -> {
                if (subscription != null) {
                    int clientId = subscription.getClientId();
                    int productId = subscription.getProductId();

                    // Now you have the data, you can safely access it
                    LiveData<ClientModel> clientLiveData = clientViewModel.getClientById(clientId);
                    LiveData<ProductModel> productLiveData = productViewModel.getProductById(productId);

                    clientLiveData.observe(getViewLifecycleOwner(), client -> {
                        if (client != null) {
                            textViewFullNameResultRemove.setText(String.valueOf(client.getFirstName() + " " + client.getLastName()));
                            textViewAddressResultRemove.setText(String.valueOf(client.getAddress()));
                        }
                    });

                    productLiveData.observe(getViewLifecycleOwner(), product -> {
                        if (product != null) {
                            textViewProductResultRemove.setText(String.valueOf(product.getProductName()));

                        }
                    });

                    textViewQuantityResultRemove.setText(String.valueOf(subscription.getQuantity()));

                }
            });
        });


        return view;
    }
}
