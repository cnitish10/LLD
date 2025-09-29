package org.example.restaurantmanagementsystem.model;

import java.util.ArrayList;
import java.util.List;

public class User {
    private final int userId;
    private List<Order> userOrderList;
    private String userName;
    private int counter = 0;

    public User( String userName) {
        this.userId = ++counter;
        this.userOrderList = new ArrayList<>();
        this.userName = userName;
    }

    public int getUserId() {
        return userId;
    }

    public List<Order> getUserOrderList() {
        return userOrderList;
    }

    public String getUserName() {
        return userName;
    }
    public void addOrder(Order  order){
        userOrderList.add(order);
    }
}
