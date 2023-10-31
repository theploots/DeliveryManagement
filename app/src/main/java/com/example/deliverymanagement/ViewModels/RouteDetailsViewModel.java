package com.example.deliverymanagement.ViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.deliverymanagement.DeliveryManagementRepository;
import com.example.deliverymanagement.models.RouteDetailsModel;

import java.util.List;

public class RouteDetailsViewModel extends AndroidViewModel {

    private DeliveryManagementRepository repository;
    private LiveData<List<RouteDetailsModel>> allRouteDetails;

    public RouteDetailsViewModel(@NonNull Application application) {
        super(application);
        repository = new DeliveryManagementRepository(application);
        allRouteDetails = repository.getAllRouteDetails();
    }

    public void insertRouteDetail(RouteDetailsModel routeDetail) {
        repository.insertRouteDetail(routeDetail);
    }

    public void updateRouteDetail(RouteDetailsModel routeDetail) {
        repository.updateRouteDetail(routeDetail);
    }

    public void deleteRouteDetail(RouteDetailsModel routeDetail) {
        repository.deleteRouteDetail(routeDetail);
    }

    public LiveData<List<RouteDetailsModel>> getAllRouteDetails() {
        return allRouteDetails;
    }

    public LiveData<RouteDetailsModel> getRouteDetailById(int id) {
        return repository.getRouteDetailById(id);
    }

    public LiveData<List<RouteDetailsModel>> getRouteDetailsByRouteId(int routeId) {
        return repository.getRouteDetailsByRouteId(routeId);
    }

    public LiveData<List<RouteDetailsModel>> getRouteDetailsByClientId(int clientId) {
        return repository.getRouteDetailsByClientId(clientId);
    }

    public LiveData<List<RouteDetailsModel>> getRouteDetailsByProductId(int productId) {
        return repository.getRouteDetailsByProductId(productId);
    }
}