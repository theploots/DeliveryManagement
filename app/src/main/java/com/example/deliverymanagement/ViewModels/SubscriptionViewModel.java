package com.example.deliverymanagement.ViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.deliverymanagement.DeliveryManagementRepository;
import com.example.deliverymanagement.models.SubscriptionModel;

import java.util.List;

public class SubscriptionViewModel extends AndroidViewModel {

    private DeliveryManagementRepository repository;
    private LiveData<List<SubscriptionModel>> allSubscriptions;

    public SubscriptionViewModel(@NonNull Application application) {
        super(application);

        repository = new DeliveryManagementRepository(application);
        allSubscriptions = repository.getAllSubscriptions();
    }

    public void insertSubscription(SubscriptionModel subscription) {
        repository.insertSubscription(subscription);
    }

    public void updateSubscription(SubscriptionModel subscription) {
        repository.updateSubscription(subscription);
    }

    public void deleteSubscription(SubscriptionModel subscription) {
        repository.deleteSubscription(subscription);
    }

    public LiveData<List<SubscriptionModel>> getAllSubscriptions() {
        return allSubscriptions;
    }

    public LiveData<SubscriptionModel> getSubscriptionById(int id) {
        return  repository.getSubscriptionById(id);
    }


}
