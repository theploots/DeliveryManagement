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

    @Query("SELECT * FROM driver ORDER BY id ASC")
    LiveData<List<DriverModel>> getAllDrivers();

    @Query("SELECT * FROM driver WHERE id = :driverId")
    LiveData<DriverModel> getDriverById(int driverId);


}
