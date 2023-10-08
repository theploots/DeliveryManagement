package com.example.deliverymanagement.models;

import androidx.annotation.Nullable;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "routes")
public class RouteModel {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @Nullable
    private SubscriptionModel subscription;
    @Nullable
    private DriverModel driver;

    public RouteModel(@Nullable SubscriptionModel subscription, @Nullable DriverModel driver) {
        this.subscription = subscription;
        this.driver = driver;
    }

    public int getId() {
        return id;
    }

    @Nullable
    public SubscriptionModel getSubscription() {
        return subscription;
    }

    @Nullable
    public DriverModel getDriver() {
        return driver;
    }

    public void setId(int id) {
        this.id = id;
    }
}
