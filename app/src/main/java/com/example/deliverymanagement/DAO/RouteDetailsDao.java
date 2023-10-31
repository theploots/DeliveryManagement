package com.example.deliverymanagement.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import androidx.room.Delete;

import com.example.deliverymanagement.models.RouteDetailsModel;

import java.util.List;

@Dao
public interface RouteDetailsDao {

    @Insert
    long insert(RouteDetailsModel routeDetails);

    @Update
    void update(RouteDetailsModel routeDetails);

    @Delete
    void delete(RouteDetailsModel routeDetails);

    @Query("SELECT * FROM route_details")
    LiveData<List<RouteDetailsModel>> getAllRouteDetails();

    @Query("SELECT * FROM route_details WHERE id = :id")
    LiveData<RouteDetailsModel> getRouteDetailById(int id);

    @Query("SELECT * FROM route_details WHERE routeId = :routeId")
    LiveData<List<RouteDetailsModel>> getRouteDetailsByRouteId(int routeId);

    @Query("SELECT * FROM route_details WHERE clientId = :clientId")
    LiveData<List<RouteDetailsModel>> getRouteDetailsByClientId(int clientId);

    @Query("SELECT * FROM route_details WHERE productId = :productId")
    LiveData<List<RouteDetailsModel>> getRouteDetailsByProductId(int productId);
}