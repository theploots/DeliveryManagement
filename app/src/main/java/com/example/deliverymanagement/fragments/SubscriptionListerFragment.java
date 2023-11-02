package com.example.deliverymanagement.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.deliverymanagement.DAO.ClientDao;
import com.example.deliverymanagement.DAO.DriverDao;
import com.example.deliverymanagement.DAO.ProductDao;
import com.example.deliverymanagement.DAO.RouteDao;
import com.example.deliverymanagement.DAO.RouteDetailsDao;
import com.example.deliverymanagement.DAO.SubscriptionDao;
import com.example.deliverymanagement.DeliveryManagementDatabase;
import com.example.deliverymanagement.R;
import com.example.deliverymanagement.ViewModels.ClientViewModel;
import com.example.deliverymanagement.ViewModels.ProductViewModel;
import com.example.deliverymanagement.ViewModels.SubscriptionViewModel;
import com.example.deliverymanagement.adapters.SubscriptionAdapter;
import com.example.deliverymanagement.models.ClientModel;
import com.example.deliverymanagement.models.ProductModel;
import com.example.deliverymanagement.models.RouteModel;
import com.example.deliverymanagement.models.SubscriptionModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SubscriptionListerFragment extends Fragment {

    Context context;

    TextView subscriberNameTextView,addressTextView,product1NameTextView,
            product2NameTextView,product1QuantityTextView,
            product2QuantityTextView;

    private SubscriptionDao subscriptionDao;
    private ClientDao clientDao;
    private ProductDao productDao;

    private DeliveryManagementDatabase deliveryManagementDatabase = DeliveryManagementDatabase.getInstance(getContext());


    private ListView listViewSubscriptions;
    private SubscriptionAdapter subscriptionAdapter;


    private SubscriptionViewModel subscriptionViewModel;

    private ClientViewModel clientViewModel;

    private ProductViewModel productViewModel;



    public SubscriptionListerFragment() {
        // Required empty public constructor
    }


    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        subscriberNameTextView = view.findViewById(R.id.subscriberNameTextView);
        addressTextView = view.findViewById(R.id.addressTextView);
        product1NameTextView = view.findViewById(R.id.product1NameTextView);
        product2NameTextView = view.findViewById(R.id.product2NameTextView);
        product1QuantityTextView = view.findViewById(R.id.product1QuantityTextView);
        product2QuantityTextView = view.findViewById(R.id.product2QuantityTextView);



        ClientViewModel clientViewModel = new ViewModelProvider(this).get(ClientViewModel.class);
        ProductViewModel productViewModel = new ViewModelProvider(this).get(ProductViewModel.class);





        listViewSubscriptions.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int id = i + 1;

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
                                subscriberNameTextView.setText(String.valueOf(client.getFirstName() + " " + client.getLastName()));
                                addressTextView.setText(String.valueOf(client.getAddress()));
                            }
                        });

                        productLiveData.observe(getViewLifecycleOwner(), product -> {
                            if (product != null) {
                                product1NameTextView.setText(String.valueOf(product.getProductName()));
                                product2NameTextView.setText(String.valueOf(product.getProductName()));
                            }
                        });

                        product1QuantityTextView.setText(String.valueOf(subscription.getQuantity()));
                    }
                });
            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_subscription_lister, container, false);


        subscriberNameTextView = view.findViewById(R.id.subscriberNameTextView);
        addressTextView = view.findViewById(R.id.addressTextView);
        product1NameTextView = view.findViewById(R.id.product1NameTextView);
        product2NameTextView = view.findViewById(R.id.product2NameTextView);
        product1QuantityTextView = view.findViewById(R.id.product1QuantityTextView);
        product2QuantityTextView = view.findViewById(R.id.product2QuantityTextView);



        listViewSubscriptions = view.findViewById(R.id.subscriberListView);
        subscriptionViewModel = new ViewModelProvider(this).get(SubscriptionViewModel.class);

        // Initialize the LiveData properly
        LiveData<List<Integer>> allSubscriptionsLiveData = subscriptionViewModel.getAllSubscriptionIds();
        allSubscriptionsLiveData.observe(getViewLifecycleOwner(), subscriptionIds -> {

            subscriptionAdapter = new SubscriptionAdapter(getContext(), subscriptionIds);
            listViewSubscriptions.setAdapter(subscriptionAdapter);
        });






        return view;
    }

    // Replace this with your actual database query to get subscription IDs
    private List<Integer> getSubscriptionIdsFromDatabase() {
        List<Integer> subscriptionIds = new ArrayList<>();
        for(SubscriptionModel subscription : DeliveryManagementDatabase.getInstance(requireContext()).subscriptionDao().getAllSubscriptions().getValue()) {
            subscriptionIds.add(subscription.getId());
        }
        return subscriptionIds;
    }
}
