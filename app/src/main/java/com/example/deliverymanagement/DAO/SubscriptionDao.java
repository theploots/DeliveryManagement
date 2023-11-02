package com.example.deliverymanagement.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.deliverymanagement.models.SubscriptionModel;

import java.util.List;

@Dao
public interface SubscriptionDao {
    @Insert
    void insertSubscription(SubscriptionModel subscription);

    @Update
    void updateSubscription(SubscriptionModel subscription);

    @Delete
    void deleteSubscription(SubscriptionModel subscription);

    @Query("SELECT * FROM subscriptions ORDER BY id ASC")
    LiveData<List<SubscriptionModel>> getAllSubscriptions();

    @Query("SELECT * FROM subscriptions WHERE id = :id")
    LiveData<SubscriptionModel> getSubscriptionById(int id);

    @Query("SELECT id FROM subscriptions ORDER BY id ASC")
    LiveData<List<Integer>> getAllSubscriptionIds();

    // Add a method to get all subscriptions for a specific client
    @Query("SELECT * FROM subscriptions WHERE clientId = :clientId")
    LiveData<List<SubscriptionModel>> getSubscriptionsByClientId(int clientId);

    // Add a method to get all subscriptions for a specific product
    @Query("SELECT * FROM subscriptions WHERE productId = :productId")
    LiveData<List<SubscriptionModel>> getSubscriptionsByProductId(int productId);

    // If you want to fetch subscriptions for a client and specific product
    @Query("SELECT * FROM subscriptions WHERE clientId = :clientId AND productId = :productId")
    LiveData<List<SubscriptionModel>> getSubscriptionsByClientAndProductId(int clientId, int productId);


}
