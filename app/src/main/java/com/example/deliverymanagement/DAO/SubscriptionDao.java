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
}
