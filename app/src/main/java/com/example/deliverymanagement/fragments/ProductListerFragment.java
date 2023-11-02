package com.example.deliverymanagement.fragments;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.deliverymanagement.DeliveryManagementDatabase;
import com.example.deliverymanagement.R;
import com.example.deliverymanagement.ViewModels.ProductViewModel;
import com.example.deliverymanagement.adapters.ProductAdapter;
import com.example.deliverymanagement.models.ProductModel;

import java.util.List;

public class ProductListerFragment extends Fragment {

    private ListView listViewProducts;
    private ProductAdapter productAdapter;
    private ProductViewModel productViewModel;

    public ProductListerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        listViewProducts = view.findViewById(R.id.listViewProducts);
        productViewModel = new ViewModelProvider(this).get(ProductViewModel.class);

        // Initialize the LiveData properly
        LiveData<List<ProductModel>> allProductsLiveData = productViewModel.getAllProducts();
        allProductsLiveData.observe(getViewLifecycleOwner(), productList -> {
            // Update your ListView adapter with the new product list
            productAdapter = new ProductAdapter(getContext(), productList);
            listViewProducts.setAdapter(productAdapter);
        });

        // Here you can set an onItemClick listener if needed to perform actions when a product is clicked
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product_lister, container, false);

        // Perform initialization of your ListView and ViewModel here




        return view;
    }

    // Replace this with your actual database query to get product data
    private List<ProductModel> getProductDataFromDatabase() {
        // Fetch the product list from the database and return it
        return DeliveryManagementDatabase.getInstance(requireContext()).productDao().getAllProducts().getValue();
    }
}
