package com.example.deliverymanagement.ViewModels;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import com.example.deliverymanagement.DeliveryManagementRepository;
import com.example.deliverymanagement.models.RouteModel;
import java.util.List;

public class RouteViewModel extends AndroidViewModel {

    private DeliveryManagementRepository repository;
    private LiveData<List<RouteModel>> allRoutes;

    public RouteViewModel(@NonNull Application application) {
        super(application);
        repository = new DeliveryManagementRepository(application);
        allRoutes = repository.getAllRoutes();

        
    }



    public void insertRoute(RouteModel route) {
        repository.insertRoute(route);
    }

    public void updateRoute(RouteModel route) {
        repository.updateRoute(route);
    }

    public void deleteRoute(RouteModel route) {
        repository.deleteRoute(route);
    }

    public LiveData<List<RouteModel>> getAllRoutes() {
        return allRoutes;
    }

    public LiveData<List<RouteModel>> getAvailableRoutes() {
        return repository.getAvailableRoutes();
    }

}
