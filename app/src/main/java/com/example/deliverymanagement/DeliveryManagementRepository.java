package com.example.deliverymanagement;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import com.example.deliverymanagement.DAO.*;
import com.example.deliverymanagement.models.*;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class DeliveryManagementRepository {

    private ExecutorService executors = Executors.newSingleThreadExecutor();

    private RouteDao routeDao;
    private DriverDao driverDao;
    private ProductDao productDao;
    private SubscriptionDao subscriptionDao;
    private ClientDao clientDao;
    private RouteDetailsDao routeDetailsDao;
    private LiveData<List<RouteDetailsModel>> allRouteDetails;

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
        routeDetailsDao = database.routeDetailsDao();

        allRoutes = routeDao.getAllRoutes();
        allDrivers = driverDao.getAllDrivers();
        allProducts = productDao.getAllProducts();
        allSubscriptions = subscriptionDao.getAllSubscriptions();
        allClients = clientDao.getAllClients();
        allRouteDetails = routeDetailsDao.getAllRouteDetails();
    }

    /**
     * Shutdown the executor service.
     */
    public void shutDownExecutor() {
        if (!executors.isShutdown()) {
            executors.shutdown();
        }
    }

    // Route Operations

    /**
     * Insert a new route.
     */
    public void insertRoute(RouteModel route) {
        // Use the executor service to run the database operation on a separate thread.
        executors.execute(() -> {
            try {
                // Try to insert the route into the database.
                routeDao.insertRoute(route);
            } catch (Exception e) {
                // Log any exceptions that occur during the database operation.
                Log.e("DeliveryRepo", "Error inserting route", e);
            }
        });
    }

    /**
     * Update an existing route.
     */
    public void updateRoute(RouteModel route) {
        executors.execute(() -> {
            try {
                routeDao.updateRoute(route);
            } catch (Exception e) {
                // Handle or log the error for route update.
            }
        });
    }

    /**
     * Delete a route.
     */
    public void deleteRoute(RouteModel route) {
        executors.execute(() -> {
            try {
                routeDao.deleteRoute(route);
            } catch (Exception e) {
                // Handle or log the error for route deletion.
            }
        });
    }

    /**
     * Get all routes.
     */
    public LiveData<List<RouteModel>> getAllRoutes() {
        return allRoutes;
    }

    /**
     * Insert a new driver.
     */
    public void insertDriver(DriverModel driver) {
        executors.execute(() -> {
            try {
                driverDao.insertDriver(driver);
            } catch (Exception e) {
                // Handle or log the error for driver insertion.
            }
        });
    }

    /**
     * Update an existing driver.
     */
    public void updateDriver(DriverModel driver) {
        executors.execute(() -> {
            try {
                driverDao.updateDriver(driver);
            } catch (Exception e) {
                // Handle or log the error for driver update.
            }
        });
    }

    /**
     * Delete a driver.
     */
    public void deleteDriver(DriverModel driver) {
        executors.execute(() -> {
            try {
                driverDao.deleteDriver(driver);
            } catch (Exception e) {
                // Handle or log the error for driver deletion.
            }
        });
    }

    /**
     * Get all drivers.
     */
    public LiveData<List<DriverModel>> getAllDrivers() {
        return allDrivers;
    }

    public LiveData<DriverModel> getDriverById(int id) {
        return driverDao.getDriverById(id);
    }

    // ... Similarly for Product, Subscription, and Client with respective DAO methods ...

    public void insertProduct(ProductModel product) {
        executors.execute(() -> {
            try {
                productDao.insertProduct(product);
            } catch (Exception e) {
                // Handle or log the error for product insertion.
            }
        });
    }

    // Product Operations
    public void updateProduct(ProductModel product) {
        executors.execute(() -> {
            try {
                productDao.updateProduct(product);
            } catch (Exception e) {
                // Handle or log the error for product update.
            }
        });
    }

    public void deleteProduct(ProductModel product) {
        executors.execute(() -> {
            try {
                productDao.deleteProduct(product);
            } catch (Exception e) {
                // Handle or log the error for product deletion.
            }
        });
    }

    public LiveData<List<ProductModel>> getAllProducts() {
        return allProducts;
    }

    // Subscription Operations
    public void insertSubscription(SubscriptionModel subscription) {
        Log.d("DeliveryRepo", "Inserting subscription: " + subscription.toString());
        executors.execute(() -> {
            try {
                subscriptionDao.insertSubscription(subscription);
            } catch (Exception e) {
                // Handle or log the error for subscription insertion.
                Log.e("DeliveryRepo", "Error inserting subscription", e);
            }
        });
    }

    public void updateSubscription(SubscriptionModel subscription) {
        executors.execute(() -> {
            try {
                subscriptionDao.updateSubscription(subscription);
            } catch (Exception e) {
                // Handle or log the error for subscription update.
            }
        });
    }

    public void deleteSubscription(SubscriptionModel subscription) {
        executors.execute(() -> {
            try {
                subscriptionDao.deleteSubscription(subscription);
            } catch (Exception e) {
                // Handle or log the error for subscription deletion.
            }
        });
    }

    public LiveData<List<SubscriptionModel>> getAllSubscriptions() {
        return allSubscriptions;
    }

    public LiveData<SubscriptionModel> getSubscriptionById(int id) {
        return subscriptionDao.getSubscriptionById(id);
    }

    // Client Operations
    public void insertClient(ClientModel client) {
        executors.execute(() -> {
            try {
                clientDao.insertClient(client);
            } catch (Exception e) {
                // Handle or log the error for client insertion.
            }
        });
    }

    public void updateClient(ClientModel client) {
        executors.execute(() -> {
            try {
                clientDao.updateClient(client);
            } catch (Exception e) {
                // Handle or log the error for client update.
            }
        });
    }

    public void deleteClient(ClientModel client) {
        executors.execute(() -> {
            try {
                clientDao.deleteClient(client);
            } catch (Exception e) {
                // Handle or log the error for client deletion.
            }
        });
    }

    public LiveData<List<ClientModel>> getAllClients() {
        return allClients;
    }

    public LiveData<ClientModel> getClientById(int id) {
        return clientDao.getClientById(id);
    }

    // RouteDetails Operations
    public void insertRouteDetail(RouteDetailsModel routeDetail) {
        executors.execute(() -> {
            try {
                routeDetailsDao.insert(routeDetail);
            } catch (Exception e) {
                // Handle or log the error for route detail insertion.
            }
        });
    }

    public void updateRouteDetail(RouteDetailsModel routeDetail) {
        executors.execute(() -> {
            try {
                routeDetailsDao.update(routeDetail);
            } catch (Exception e) {
                // Handle or log the error for route detail update.
            }
        });
    }

    public void deleteRouteDetail(RouteDetailsModel routeDetail) {
        executors.execute(() -> {
            try {
                routeDetailsDao.delete(routeDetail);
            } catch (Exception e) {
                // Handle or log the error for route detail deletion.
            }
        });
    }

    public LiveData<List<RouteDetailsModel>> getAllRouteDetails() {
        return allRouteDetails;
    }
    public LiveData<List<RouteModel>> getAvailableRoutes() {
        // Simply returning the LiveData directly from the DAO.
        return routeDao.getAvailableRoutes();
    }


    public LiveData<RouteDetailsModel> getRouteDetailById(int id) {
        return routeDetailsDao.getRouteDetailById(id);
    }

    public LiveData<List<RouteDetailsModel>> getRouteDetailsByRouteId(int routeId) {
        return routeDetailsDao.getRouteDetailsByRouteId(routeId);
    }

    public LiveData<List<RouteDetailsModel>> getRouteDetailsByClientId(int clientId) {
        return routeDetailsDao.getRouteDetailsByClientId(clientId);
    }

    public LiveData<List<RouteDetailsModel>> getRouteDetailsByProductId(int productId) {
        return routeDetailsDao.getRouteDetailsByProductId(productId);
    }

    /**
     * Fetch the first available route which does not have a driver assigned.
     * @return An available RouteModel or null if none are found.
     */
    public RouteModel getAvailableRoute() {
        // Since database operations shouldn't be performed on the main thread,
        // we'll use the ExecutorService to fetch the data.
        Future<RouteModel> future = executors.submit(() -> {
            return routeDao.getAvailableRoute();
        });

        try {
            return future.get();  // This will wait for the operation to complete and return the result.
        } catch (Exception e) {
            // Handle or log the error.
            return null;
        }
    }

    public LiveData<List<Integer>> getAllSubscriptionIds() {
        return subscriptionDao.getAllSubscriptionIds();
    }

    public LiveData<ProductModel> getProductById(int productId) {
        return productDao.getProductById(productId);
    }
}
