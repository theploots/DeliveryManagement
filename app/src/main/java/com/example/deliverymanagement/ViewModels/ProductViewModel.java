package com.example.deliverymanagement.ViewModels;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.example.deliverymanagement.DeliveryManagementRepository;
import com.example.deliverymanagement.models.ProductModel;
import java.util.List;

public class ProductViewModel extends AndroidViewModel {

    private DeliveryManagementRepository repository;
    private LiveData<List<ProductModel>> allProducts;

    public ProductViewModel(@NonNull Application application) {
        super(application);
        repository = new DeliveryManagementRepository(application);
        allProducts = repository.getAllProducts();
    }

    public void insertProduct(ProductModel product) {
        repository.insertProduct(product);
    }

    public void updateProduct(ProductModel product) {
        repository.updateProduct(product);
    }

    public void deleteProduct(ProductModel product) {
        repository.deleteProduct(product);
    }

    public LiveData<List<ProductModel>> getAllProducts() {
        return allProducts;
    }

    // You can add more methods specific to ProductModel if needed
}
