package com.example.deliverymanagement.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.deliverymanagement.models.DriverModel;

import java.util.List;

@Dao
public interface DriverDao {

    @Insert
    void insertDriver(DriverModel driver);
    @Update
    void updateDriver(DriverModel driver);
    @Delete
    void deleteDriver(DriverModel driver);
    @Query("SELECT * FROM drivers ORDER BY id ASC")
    LiveData<List<DriverModel>> getAllDrivers();
    @Query("SELECT * FROM drivers WHERE id = :id")
    LiveData<List<DriverModel>> getDriverById(int id);
}
