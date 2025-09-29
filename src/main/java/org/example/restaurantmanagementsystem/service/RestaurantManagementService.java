package org.example.restaurantmanagementsystem.service;

import org.example.restaurantmanagementsystem.model.FoodItem;
import org.example.restaurantmanagementsystem.model.Order;
import org.example.restaurantmanagementsystem.model.Restaurant;
import org.example.restaurantmanagementsystem.model.User;

import java.util.*;
import java.util.stream.Collectors;

public class RestaurantManagementService {
    Map<Integer, Restaurant> restaurantMap;
    Map<Integer, Map<Integer, Integer>> restaurantFoodItemQuantity; // FIXED
    Map<Integer, FoodItem> foodItemMap;
    Map<String, Restaurant> stringRestaurantMap;
    List<Restaurant> allRestaurants;
    Map<Integer, User> userMap;

    public RestaurantManagementService() {
        restaurantMap = new HashMap<>();
        restaurantFoodItemQuantity = new HashMap<>();
        foodItemMap = new HashMap<>();
        stringRestaurantMap = new HashMap<>();
        allRestaurants = new ArrayList<>();
        userMap = new HashMap<>();
    }

    public boolean registerRestaurant(String restaurantName, List<Integer> servicablePincodes,
                                      List<FoodItem> foodItems, Map<FoodItem, Integer> foodItemQuantity) {
        Set<Integer> serviceablePincodes = new HashSet<>(servicablePincodes);
        Restaurant restaurant = new Restaurant(restaurantName, foodItems, serviceablePincodes);
        int restaurantId = restaurant.getRestaurantId();
        restaurantMap.putIfAbsent(restaurantId, restaurant);
        allRestaurants.add(restaurant);
        // Convert FoodItem -> foodItemId mapping
        Map<Integer, Integer> foodQtyMap = new HashMap<>();
        for (FoodItem item : foodItems) {
            foodItemMap.put(item.getFoodId(), item); // keep global reference
            foodQtyMap.put(item.getFoodId(), foodItemQuantity.get(item));
        }
        restaurantFoodItemQuantity.putIfAbsent(restaurantId, foodQtyMap);

        System.out.println("Restaurant Registered " + restaurantId);
        return true;
    }

    public boolean updateFoodQuantity(int restaurantId, int foodItemId, int quantityToAdd) {
        Restaurant restaurant = restaurantMap.get(restaurantId);
        if (restaurant == null) {
            System.out.println("Restaurant doesn't exist!");
            return false;
        }

        Map<Integer, Integer> foodItemList = restaurantFoodItemQuantity.get(restaurantId);
        if (foodItemList == null || !foodItemList.containsKey(foodItemId)) {
            System.out.println("Food item doesn't exist!");
            return false;
        }

        int oldQty = foodItemList.get(foodItemId);
        int newQty = oldQty + quantityToAdd;
        foodItemList.put(foodItemId, newQty);

        FoodItem foodItem = foodItemMap.get(foodItemId);
        System.out.println("Quantity for food " + foodItem.getFoodName() +
                " at restaurant " + restaurant.getRestaurantName() +
                " updated successfully! New qty = " + newQty);

        return true;
    }

    public boolean rateRestaurant(int rating, int restaurantId){
        Restaurant restaurant = restaurantMap.get(restaurantId);
        if(restaurant == null){
            System.out.println("restaurant doesnt exist");
            return  false;
        }
        if(rating < 1 || rating > 5){
            System.out.println("please provide valid raitng!");
            return false;
        }
        double newRating = restaurant.calculateAverageRating(rating);

        System.out.println(" you gave restaurant " + restaurant.getRestaurantName() + " a rating of " + rating + " updated rating " + newRating);
        return true;
    }

    // show_restaurant(rating/price) -> User should be able to get list of all serviceable restaurant, food item name and price in descending order, based on rating, possibly based on price
    public List<Restaurant> showAllServiceableRestaurant(int pincode){
        List<Restaurant> allServiceableRestaurant = allRestaurants.stream().filter((a)-> a.getServicableAddressList().contains(pincode)).collect(Collectors.toList());
        return allServiceableRestaurant;
    }

    public List<Restaurant> showAllRestaurantsSortedByRating(int pincode) {
        List<Restaurant> allServiceableRestaurant = showAllServiceableRestaurant(pincode);

        if (allServiceableRestaurant == null || allServiceableRestaurant.isEmpty()) {
            System.out.println("Sorry, no restaurant is serving at your address");
            return new ArrayList<>();
        }

        return allServiceableRestaurant.stream()
                .sorted(Comparator.comparingDouble(Restaurant::getAverageRating).reversed()) // highest first
                .collect(Collectors.toList());
    }

    public List<Restaurant> showAllRestaurantsSortedByPrice(int pincode, int foodItemId) {
        List<Restaurant> allServiceableRestaurant = showAllServiceableRestaurant(pincode);

        if (allServiceableRestaurant == null || allServiceableRestaurant.isEmpty()) {
            System.out.println("Sorry, no restaurant is serving at your address");
            return new ArrayList<>();
        }

        FoodItem foodItem = foodItemMap.get(foodItemId);
        if (foodItem == null) {
            System.out.println("No restaurant serving this food");
            return new ArrayList<>();
        }

        return allServiceableRestaurant.stream()
                .filter(r -> r.getFoodItemList().stream().anyMatch(f -> f.getFoodId() == foodItemId))
                .sorted(Comparator.comparingInt(r ->
                        r.getFoodItemList().stream()
                                .filter(f -> f.getFoodId() == foodItemId)
                                .findFirst().get().getFoodPrice()
                ))
                .collect(Collectors.toList());
    }

    //        place_order(restaurant name, quantity): A restaurant is serviceable when it delivers to the user's pincode and has a non-zero quantity of food item. Place an order from any restaurant with any allowed quantity.
    public boolean placeOrder(int restaurantId, int foodItemId, int pincode, int quantity, int userId) {
        // Check if restaurant exists and is serviceable
        Restaurant restaurant = restaurantMap.get(restaurantId);
        if (restaurant == null) {
            System.out.println("Restaurant not found");
            return false;
        }

        List<Restaurant> allServiceableRestaurant = showAllServiceableRestaurant(pincode);
        if (allServiceableRestaurant == null || allServiceableRestaurant.isEmpty()
                || !allServiceableRestaurant.contains(restaurant)) {
            System.out.println("Sorry, not servicing at your location");
            return false;
        }

        // Check if food item exists
        FoodItem foodItem = foodItemMap.get(foodItemId);
        if (foodItem == null) {
            System.out.println("Food item not found");
            return false;
        }

        // Check stock
        Map<Integer, Integer> foodQuantityMap = restaurantFoodItemQuantity.get(restaurantId);
        if (foodQuantityMap == null || !foodQuantityMap.containsKey(foodItemId)) {
            System.out.println("This food item is not available in the selected restaurant");
            return false;
        }

        int availableQty = foodQuantityMap.get(foodItemId);
        if (availableQty < quantity) {
            System.out.println("Not enough stock. Available: " + availableQty);
            return false;
        }

        // Place order
        Order order = new Order(userId, restaurantId, foodItemId, quantity);
        userMap.get(userId).addOrder(order);

        // Update stock
        foodQuantityMap.put(foodItemId, availableQty - quantity);

        System.out.println("Order placed successfully!");
        return true;
    }

    public boolean registerUser(String userName){
        User user = new User(userName);
        userMap.putIfAbsent(user.getUserId(), user);
        System.out.println("User " + userName +  " registered successfully");
        return true;

    }

}
