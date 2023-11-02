package com.example.deliverymanagement.models;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import androidx.room.ColumnInfo;
@Entity(tableName = "subscriptions",
        foreignKeys = {
                @ForeignKey(
                        entity = ClientModel.class,
                        parentColumns = "id",
                        childColumns = "clientId",
                        onDelete = ForeignKey.CASCADE
                ),
                @ForeignKey(
                        entity = ProductModel.class,
                        parentColumns = "id",
                        childColumns = "productId",
                        onDelete = ForeignKey.CASCADE
                )
        })
public class SubscriptionModel {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(index = true)
    private int clientId;

    @ColumnInfo(index = true)
    private int productId;

    private int quantity;
//    private int quantity;

    public SubscriptionModel(int clientId, int productId, int quantity) {
        this.clientId = clientId;
        this.productId = productId;
        this.quantity = quantity;
//        this.quantityNewsPaper = quantityNewsPaper;
    }


    public int getId() {
        return id;
    }

    public int getClientId() {
        return this.clientId;
    }

    public int getProductId() {
        return productId;
    }


    public int getQuantity() {
        return quantity;
    }

//    public int getQuantityNewsPaper() {
//        return quantityNewsPaper;
//    }



    public void setId(int id) {
        this.id = id;
    }
}
