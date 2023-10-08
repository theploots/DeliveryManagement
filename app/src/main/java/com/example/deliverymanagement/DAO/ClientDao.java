package com.example.deliverymanagement.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.deliverymanagement.models.ClientModel;

import java.util.List;

@Dao
public interface ClientDao {

    @Insert
    void insertClient(ClientModel client);
    @Update
    void updateClient(ClientModel client);
    @Delete
    void deleteClient(ClientModel client);
    @Query("SELECT * FROM clients ORDER BY id ASC")
    LiveData<List<ClientModel>> getAllClients();
    @Query("SELECT * FROM clients WHERE id = :id")
    LiveData<List<ClientModel>> getClientById(int id);


}
