package com.example.deliverymanagement.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.deliverymanagement.models.RouteModel;

import java.util.List;

@Dao
public interface RouteDao {
    @Insert
    void insertRoute(RouteModel route);
    @Update
    void updateRoute(RouteModel route);
    @Delete
    void deleteRoute(RouteModel route);
    @Query("SELECT * FROM routes ORDER BY id ASC")
    LiveData<List<RouteModel>> getAllRoutes();
}
