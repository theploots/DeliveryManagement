package com.example.deliverymanagement;



import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.deliverymanagement.DAO.ClientDao;
import com.example.deliverymanagement.DAO.DriverDao;
import com.example.deliverymanagement.DAO.ProductDao;
import com.example.deliverymanagement.DAO.RouteDao;
import com.example.deliverymanagement.DAO.SubscriptionDao;
import com.example.deliverymanagement.models.ClientModel;
import com.example.deliverymanagement.models.DriverModel;
import com.example.deliverymanagement.models.ProductModel;
import com.example.deliverymanagement.models.RouteModel;
import com.example.deliverymanagement.models.SubscriptionModel;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class DeliveryManagementRepository {

    ExecutorService executors= Executors.newSingleThreadExecutor();

    private RouteDao routeDao;
    private DriverDao driverDao;
    private ProductDao productDao;
    private SubscriptionDao subscriptionDao;
    private ClientDao clientDao;

    private LiveData<List<RouteModel>> allRoutes;
    private LiveData<List<DriverModel>> allDrivers;
    private LiveData<List<ProductModel>> allProducts;
    private LiveData<List<SubscriptionModel>> allSubscriptions;
    private LiveData<List<ClientModel>> allClients;

    public DeliveryManagementRepository(Context context) {
        DeliveryManagementDatabase database = DeliveryManagementDatabase.getInstance(context);
        routeDao = database.routeDao();
        driverDao = database.driverDao();
        productDao = database.productDao();
        subscriptionDao = database.subscriptionDao();
        clientDao = database.clientDao();

        allRoutes = routeDao.getAllRoutes();
        allDrivers = driverDao.getAllDrivers();
        allProducts = productDao.getAllProducts();
        allSubscriptions = subscriptionDao.getAllSubscriptions();
        allClients = clientDao.getAllClients();
    }

    public void insertRoute(RouteModel route) {
        executors.execute(new Runnable() {
            @Override
            public void run() {
                routeDao.insertRoute(route);
            }
        });
    }

    public void updateRoute(RouteModel route) {
        executors.execute(new Runnable() {
            @Override
            public void run() {
                routeDao.updateRoute(route);
            }
        });

    }

    public void deleteRoute(RouteModel route) {
        executors.execute(new Runnable() {
            @Override
            public void run() {
                routeDao.deleteRoute(route);
            }
        });

    }

    public LiveData<List<RouteModel>> getAllRoutes() {
        return allRoutes;
    }

    public void insertDriver(DriverModel driver) {
        executors.execute(new Runnable() {
            @Override
            public void run() {
                driverDao.insertDriver(driver);
            }
        });

    }

    public void updateDriver(DriverModel driver) {
        executors.execute(new Runnable() {
            @Override
            public void run() {
                driverDao.updateDriver(driver);
            }
        });

    }

    public void deleteDriver(DriverModel driver) {
        executors.execute(new Runnable() {
            @Override
            public void run() {
                driverDao.deleteDriver(driver);
            }
        });

    }

    public LiveData<List<DriverModel>> getAllDrivers() {
        return allDrivers;
    }

    public LiveData<List<DriverModel>> getDriverById(int id) {
        return  driverDao.getDriverById(id);
    }

    public void insertProduct(ProductModel product) {
        executors.execute(new Runnable() {
            @Override
            public void run() {
                productDao.insertProduct(product);
            }
        });

    }

    public void updateProduct(ProductModel product) {
        executors.execute(new Runnable() {
            @Override
            public void run() {
                productDao.updateProduct(product);
            }
        });

    }

    public void deleteProduct(ProductModel product) {
        executors.execute(new Runnable() {
            @Override
            public void run() {
                productDao.deleteProduct(product);
            }
        });

    }

    public LiveData<List<ProductModel>> getAllProducts() {
        return allProducts;
    }

    public void insertSubscription(SubscriptionModel subscription) {
        executors.execute(new Runnable() {
            @Override
            public void run() {
                subscriptionDao.insertSubscription(subscription);
            }
        });

    }

    public void updateSubscription(SubscriptionModel subscription) {
        executors.execute(new Runnable() {
            @Override
            public void run() {
                subscriptionDao.updateSubscription(subscription);
            }
        });

    }

    public void deleteSubscription(SubscriptionModel subscription) {
        executors.execute(new Runnable() {
            @Override
            public void run() {
                subscriptionDao.deleteSubscription(subscription);
            }
        });

    }

    public LiveData<List<SubscriptionModel>> getAllSubscriptions() {
        return allSubscriptions;
    }

    public LiveData<List<SubscriptionModel>> getSubscriptionById(int id) {
        return  subscriptionDao.getSubscriptionById(id);
    }
    public void insertClient(ClientModel client) {
        executors.execute(new Runnable() {
            @Override
            public void run() {
                clientDao.insertClient(client);
            }
        });

    }

    public void updateClient(ClientModel client) {
        executors.execute(new Runnable() {
            @Override
            public void run() {
                clientDao.updateClient(client);
            }
        });

    }

    public void deleteClient(ClientModel client) {
        executors.execute(new Runnable() {
            @Override
            public void run() {
                clientDao.deleteClient(client);
            }
        });

    }

    public LiveData<List<ClientModel>> getAllClients() {
        return allClients;
    }

    public LiveData<List<ClientModel>> getClientById(int id) {
        return clientDao.getClientById(id);
    }







}
