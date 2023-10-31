package com.example.deliverymanagement.ViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.deliverymanagement.DeliveryManagementRepository;
import com.example.deliverymanagement.models.ClientModel;

import java.util.List;

public class ClientViewModel extends AndroidViewModel {
    private DeliveryManagementRepository repository;
    private LiveData<List<ClientModel>> allClients;
    public ClientViewModel(@NonNull Application application) {
        super(application);

        repository = new DeliveryManagementRepository(application);
        allClients = repository.getAllClients();
    }

    public void insertClient(ClientModel client) {
        repository.insertClient(client);
    }

    public void updateClient(ClientModel client) {
        repository.updateClient(client);
    }

    public void deleteClient(ClientModel client) {
        repository.deleteClient(client);
    }

    public LiveData<List<ClientModel>> getAllClients() {
        return allClients;
    }

    public LiveData<ClientModel> getClientById(int id) { // Note the change in return type here
        return repository.getClientById(id);
    }
}
