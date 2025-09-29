package org.example.restaurantmanagementsystem.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Restaurant {
    private final int restaurantId;
    private int counter = 0;
    private String restaurantName;
    private List<FoodItem> foodItemList;
    private Set<Integer> servicableAddressList;
    private int totalRatingsCnt;
    private int ratingCounter = 0;
    private int totalRating;

    public Restaurant(String restaurantName, List<FoodItem> foodItemList, Set<Integer> servicableAddressList) {
        this.restaurantId = ++counter;
        this.restaurantName = restaurantName;
        this.foodItemList = foodItemList;
        this.servicableAddressList = servicableAddressList;
        this.totalRating = 0;
        this.totalRatingsCnt= 0;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "restaurantId=" + restaurantId +
                ", restaurantName='" + restaurantName + '\'' +
                ", averageRating=" + totalRating +
                '}';
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public List<FoodItem> getFoodItemList() {
        return foodItemList;
    }

    public Set<Integer> getServicableAddressList() {
        return servicableAddressList;
    }

    public double getAverageRating() {
        return (double) totalRating / totalRatingsCnt;
    }
    public double calculateAverageRating(int rating){
        this.totalRatingsCnt = ++ratingCounter;
        totalRating += rating;
        return getAverageRating();
    }
}
