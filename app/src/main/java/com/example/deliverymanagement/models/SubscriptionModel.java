package com.example.deliverymanagement.models;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "subscriptions")
public class SubscriptionModel {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @NonNull
    private ClientModel client;
    @NonNull
    private ProductModel product;
    @NonNull
    private int quantity;

    public SubscriptionModel(@NonNull ClientModel client, @NonNull ProductModel product, @NonNull int quantity) {
        this.client = client;
        this.product = product;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    @NonNull
    public ClientModel getClient() {
        return client;
    }

    @NonNull
    public ProductModel getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setId(int id) {
        this.id = id;
    }
}
