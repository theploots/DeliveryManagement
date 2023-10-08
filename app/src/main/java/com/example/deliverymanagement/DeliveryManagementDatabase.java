package com.example.deliverymanagement;

import androidx.room.Database;
import androidx.room.RoomDatabase;


import com.example.deliverymanagement.DAO.ClientDao;
import com.example.deliverymanagement.DAO.DriverDao;
import com.example.deliverymanagement.DAO.ProductDao;
import com.example.deliverymanagement.DAO.RouteDao;
import com.example.deliverymanagement.DAO.SubscriptionDao;
import com.example.deliverymanagement.models.ClientModel;
import com.example.deliverymanagement.models.RouteModel;
import com.example.deliverymanagement.models.SubscriptionModel;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {com.example.deliverymanagement.models.RouteModel.class, com.example.deliverymanagement.models.SubscriptionModel.class, com.example.deliverymanagement.models.ClientModel.class, com.example.deliverymanagement.models.ProductModel.class, com.example.deliverymanagement.models.DriverModel.class}, version = 1)
public abstract class DeliveryManagementDatabase extends RoomDatabase {

    private static DeliveryManagementDatabase instance;

    public abstract com.example.deliverymanagement.DAO.RouteDao routeDao();

    public abstract com.example.deliverymanagement.DAO.SubscriptionDao subscriptionDao();

    public abstract com.example.deliverymanagement.DAO.ClientDao clientDao();

    public abstract com.example.deliverymanagement.DAO.ProductDao productDao();

    public abstract com.example.deliverymanagement.DAO.DriverDao driverDao();

    public static synchronized DeliveryManagementDatabase getInstance(android.content.Context context) {
        if (instance == null) {
            instance = androidx.room.Room.databaseBuilder(context.getApplicationContext(), DeliveryManagementDatabase.class, "delivery_management_database").fallbackToDestructiveMigration().build();
        }
        return instance;
    }

    public static RoomDatabase.Callback roomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(androidx.sqlite.db.SupportSQLiteDatabase db) {
            super.onCreate(db);

            ClientDao clientDao = instance.clientDao();
            RouteDao routeDao = instance.routeDao();
            ProductDao productDao = instance.productDao();
            DriverDao driverDao = instance.driverDao();
            SubscriptionDao subscriptionDao = instance.subscriptionDao();

            ExecutorService executorService = Executors.newSingleThreadExecutor();

            executorService.execute(new Runnable() {
                @Override
                public void run() {


                }
            });




        }
    };



}
