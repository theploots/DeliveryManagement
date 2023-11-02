package com.example.deliverymanagement.models;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "driver",
        foreignKeys = {
                @ForeignKey(
                        entity = RouteModel.class,
                        parentColumns = "id",
                        childColumns = "routeId",
                        onDelete = ForeignKey.SET_NULL
                )
        })
public class DriverModel {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @NonNull
    private String firstName;
    @NonNull
    private String lastName;
    private String address;
    @NonNull
    private String phoneNumber;

    private Integer routeId; // Made nullable

    public DriverModel(@NonNull String firstName, @NonNull String lastName, @NonNull String address, @NonNull String phoneNumber, Integer routeId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.routeId = routeId ; // No route associated initially
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public String getFirstName() {
        return firstName;
    }

    @NonNull
    public String getLastName() {
        return lastName;
    }

    @NonNull
    public String getAddress() { return address; }

    @NonNull
    public String getPhoneNumber() { // Changed method name here
        return phoneNumber;
    }

    public Integer getRouteId() {
        return routeId;
    }

    public void setRouteId(Integer routeId) {
        this.routeId = routeId;
    }

    public void setFirstName(@NonNull String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(@NonNull String lastName) {
        this.lastName = lastName;
    }

    public void setAddress(@NonNull String address) {
        this.address = address;
    }

    public void setPhoneNumber(@NonNull String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }




}
