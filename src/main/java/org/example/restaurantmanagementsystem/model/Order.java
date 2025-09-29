package org.example.restaurantmanagementsystem.model;

public class Order {
    private final int orderId;
    private int counter = 0;
    private int userId;
    private int restaurantId;
    private int foodItemId;
    private int quanitity;

    public Order(int userId, int restaurantId, int foodItemId, int quanitity) {
        this.orderId = ++counter;
        this.userId = userId;
        this.restaurantId = restaurantId;
        this.foodItemId = foodItemId;
        this.quanitity = quanitity;
    }

    public int getOrderId() {
        return orderId;
    }

    public int getCounter() {
        return counter;
    }

    public int getUserId() {
        return userId;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public int getFoodItemId() {
        return foodItemId;
    }

    public int getQuanitity() {
        return quanitity;
    }
}
