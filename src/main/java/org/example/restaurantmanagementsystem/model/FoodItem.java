package org.example.restaurantmanagementsystem.model;

public class FoodItem {
    private final int foodId;
    private int counter = 0;
    private String foodName;
    private int foodPrice;

    public int getFoodId() {
        return foodId;
    }

    public String getFoodName() {
        return foodName;
    }

    public int getFoodPrice() {
        return foodPrice;
    }

    public FoodItem( String foodName, int foodPrice) {
        this.foodId = ++counter;
        this.foodName = foodName;
        this.foodPrice = foodPrice;
    }
}
