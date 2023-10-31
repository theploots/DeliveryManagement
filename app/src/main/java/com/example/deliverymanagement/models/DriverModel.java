package com.example.deliverymanagement.models;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "driver")
public class DriverModel {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @NonNull
    private String firstName;
    @NonNull
    private String lastName;
    @NonNull
    private String phoneNumber;

    public DriverModel(@NonNull String firstName, @NonNull String lastName, @NonNull String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
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
    public String getPhoneNumber() { // Changed method name here
        return phoneNumber;
    }
}
