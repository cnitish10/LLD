package org.example.restaurantmanagementsystem;

import org.example.restaurantmanagementsystem.model.FoodItem;
import org.example.restaurantmanagementsystem.model.Restaurant;
import org.example.restaurantmanagementsystem.model.User;
import org.example.restaurantmanagementsystem.service.RestaurantManagementService;

import java.util.*;

public class RestaurantManagemnetSystemApp {
    public static void main(String args[]){
//        Design Restaurant Management System: ClearFood
//
//        Overview:
//
//        Restaurants can serve in multiple areas (identified by Pincode)
//        Location - At a time, users can order from one restaurant, and the quantity of food can be more than one.
//        Rating - Users should be able to rate any restaurant with or without comment. Rating of a restaurant is the average rating given by all customers.
//
//        Functional Requirements:
//
        FoodItem pasta = new FoodItem("Pasta", 25);
        FoodItem burger = new FoodItem("Burger", 55);
        List<FoodItem> foodItems = new ArrayList<>(Arrays.asList(pasta, burger));
        Map<FoodItem, Integer> foodItemIntegerMap = new HashMap<>();
        foodItemIntegerMap.putIfAbsent(pasta, 10);
        foodItemIntegerMap.putIfAbsent(burger, 25);
        RestaurantManagementService restaurantManagementService = new RestaurantManagementService();

        //register restaurant
        restaurantManagementService.registerRestaurant("Zaayka Restaurant",
                Arrays.asList(226021, 226003, 225003),
                foodItems,
                foodItemIntegerMap
                );
        restaurantManagementService.registerRestaurant("Bar and Resto",
                Arrays.asList( 225003),
                foodItems,
                foodItemIntegerMap
        );

        //update quantity
        restaurantManagementService.updateFoodQuantity(1,1, 40);

        //rate restro
        restaurantManagementService.rateRestaurant(5, 1);
        restaurantManagementService.rateRestaurant(4, 1);

        //show all serviceable list
        List<Restaurant> serviceableRestop = restaurantManagementService.showAllServiceableRestaurant(225003);
        for(Restaurant r : serviceableRestop){
            System.out.println(r.getRestaurantName());
        }

        //show
        List<Restaurant> serviceableRestoOderByRating = restaurantManagementService.showAllRestaurantsSortedByRating(225003);
        for(Restaurant r : serviceableRestoOderByRating){
            System.out.println(r.getRestaurantName());
        }

        List<Restaurant> serviceableRestoOrderByPrice = restaurantManagementService.showAllRestaurantsSortedByPrice(225003, 1);
        for(Restaurant r : serviceableRestoOrderByPrice){
            System.out.println(r.getRestaurantId());
        }

        //place order
        restaurantManagementService.registerUser("Nitish");
        restaurantManagementService.placeOrder(1, 1, 225003, 2, 1);
//        order_history(username) -> Order History of User: For a given user you should be able to fetch order history.

    //object : Restaurant
        //food item
        // Order

    }
}
