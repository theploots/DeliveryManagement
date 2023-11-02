package com.example.deliverymanagement;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.deliverymanagement.DAO.*;
import com.example.deliverymanagement.models.*;

import java.util.List;
import java.util.concurrent.Executors;

@Database(entities = {
        RouteModel.class,
        SubscriptionModel.class,
        ClientModel.class,
        ProductModel.class,
        DriverModel.class,
        RouteDetailsModel.class
}, version = 2, exportSchema = false)
public abstract class DeliveryManagementDatabase extends RoomDatabase {

    private static DeliveryManagementDatabase instance;



    public abstract RouteDao routeDao();
    public abstract SubscriptionDao subscriptionDao();
    public abstract ClientDao clientDao();
    public abstract ProductDao productDao();
    public abstract DriverDao driverDao();
    public abstract RouteDetailsDao routeDetailsDao();

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            Executors.newSingleThreadExecutor().execute(() -> {
                ProductDao productDao = instance.productDao();
                productDao.insertProduct(new ProductModel("Magazine"));

                productDao.insertProduct(new ProductModel("Newspaper"));
            });
        }
    };

    public static synchronized DeliveryManagementDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            DeliveryManagementDatabase.class, "delivery_management_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }
}
