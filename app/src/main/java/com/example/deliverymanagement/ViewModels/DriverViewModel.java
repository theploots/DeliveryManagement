package com.example.deliverymanagement.ViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.deliverymanagement.DeliveryManagementRepository;
import com.example.deliverymanagement.models.DriverModel;

import java.util.List;

public class DriverViewModel extends AndroidViewModel {

    private DeliveryManagementRepository repository;
    private LiveData<List<DriverModel>> allDrivers;

    public DriverViewModel(@NonNull Application application) {
        super(application);

        repository = new DeliveryManagementRepository(application);
        allDrivers = repository.getAllDrivers();
    }

    public void insertDriver(DriverModel driver) {
        repository.insertDriver(driver);
    }

    public void updateDriver(DriverModel driver) {
        repository.updateDriver(driver);
    }

    public void deleteDriver(DriverModel driver) {
        repository.deleteDriver(driver);
    }

    public LiveData<List<DriverModel>> getAllDrivers() {
        return allDrivers;
    }

    public LiveData<DriverModel> getDriverById(int id) {
        return repository.getDriverById(id);
    }
}
