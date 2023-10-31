package com.example.deliverymanagement.models;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import androidx.room.ColumnInfo;

@Entity(tableName = "routes",
        foreignKeys = {
                @ForeignKey(
                        entity = DriverModel.class,
                        parentColumns = "id",
                        childColumns = "driverId",
                        onDelete = ForeignKey.SET_NULL
                )
        })
public class RouteModel {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(index = true)
    private Integer driverId; // Made nullable

    public RouteModel() {
        this.driverId = null;  // No driver associated initially
    }

    public int getId() {
        return id;
    }

    public Integer getDriverId() {
        return driverId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDriverId(Integer driverId) {
        this.driverId = driverId;
    }
}
