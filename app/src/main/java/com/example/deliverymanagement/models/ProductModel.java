package com.example.deliverymanagement.models;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "products")
public class ProductModel {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @NonNull
    private String description;

    public ProductModel(@NonNull String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    @NonNull
    public String getDescription() {
        return description;
    }

    public void setId(int id) {
        this.id = id;
    }
}
