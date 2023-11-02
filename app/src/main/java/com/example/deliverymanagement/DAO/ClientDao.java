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
    long insertClient(ClientModel client);

    @Update
    void updateClient(ClientModel client);

    @Delete
    void deleteClient(ClientModel client);

    @Query("SELECT * FROM clients ORDER BY id ASC")
    LiveData<List<ClientModel>> getAllClients();

    @Query("SELECT * FROM clients WHERE id = :id")
    LiveData<ClientModel> getClientById(int id);

    @Query("SELECT COUNT(*) FROM clients WHERE firstName = :firstName AND lastName = :lastName AND address = :address")
    int countClientsWithSameNameAndAddress(String firstName, String lastName, String address);
}
