package com.example.deliverymanagement.models;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import androidx.room.ColumnInfo;

@Entity(tableName = "route_details",
        foreignKeys = {
                @ForeignKey(
                        entity = RouteModel.class,
                        parentColumns = "id",
                        childColumns = "routeId",
                        onDelete = ForeignKey.CASCADE
                ),
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
public class RouteDetailsModel {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(index = true)
    private int routeId;

    @ColumnInfo(index = true)
    private int clientId;

    @ColumnInfo(index = true)
    private int productId;

    private int quantity; // Quantity of the product for the client on this route

    public RouteDetailsModel(int routeId, int clientId, int productId, int quantity) {
        this.routeId = routeId;
        this.clientId = clientId;
        this.productId = productId;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public int getRouteId() {
        return routeId;
    }

    public int getClientId() {
        return clientId;
    }

    public int getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setId(int id) {
        this.id = id;
    }
}