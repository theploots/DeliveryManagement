package com.example.deliverymanagement.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.deliverymanagement.models.ProductModel;

import java.util.List;

@Dao
public interface ProductDao {
    @Insert
    void insertProduct(ProductModel product);

    @Update
    void updateProduct(ProductModel product);

    @Delete
    void deleteProduct(ProductModel product);

    @Query("SELECT * FROM products ORDER BY id ASC")
    LiveData<List<ProductModel>> getAllProducts();

    @Query("SELECT * FROM products WHERE id = :id")
    LiveData<ProductModel> getProductById(int id);

    @Query("SELECT id FROM products WHERE productName = :productName LIMIT 1")
    long getProductIdByName(String productName);



}