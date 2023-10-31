package com.example.deliverymanagement.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "products")
public class ProductModel {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String productName;

    public ProductModel(String productName) {
        this.productName = productName;
    }

    // Getter and Setter for id and productName

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
