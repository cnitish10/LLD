package org.example.fooddeliverysystem.model;


import org.example.fooddeliverysystem.enums.Status;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private int id;
    private User user;
    private List<MenuItem> items;
    private int totalPrice;
    private Status status;

    public Order(int id, User user) {
        this.id = id;
        this.user = user;
        this.items = new ArrayList<>();
        this.totalPrice = 0;
        this.status = Status.PLACED;
    }

    public int getId() { return id; }
    public User getUser() { return user; }
    public List<MenuItem> getItems() { return items; }
    public int getTotalPrice() { return totalPrice; }
    public Status getStatus() { return status; }

    public void addItem(MenuItem item) {
        items.add(item);
        totalPrice += item.getPrice();
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Order " + id + " by " + user.getUserName() +
                " | Items: " + items.size() +
                " | Total: $" + totalPrice +
                " | Status: " + status;
    }
}
