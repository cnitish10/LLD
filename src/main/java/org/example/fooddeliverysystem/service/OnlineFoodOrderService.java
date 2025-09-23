package org.example.fooddeliverysystem.service;

import org.example.fooddeliverysystem.enums.Status;
import org.example.fooddeliverysystem.model.MenuItem;
import org.example.fooddeliverysystem.model.Order;
import org.example.fooddeliverysystem.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OnlineFoodOrderService {

    private Map<Integer, User> users = new HashMap<>();
    private Map<Integer, MenuItem> menu = new HashMap<>();
    private Map<Integer, Order> orders = new HashMap<>();
    private int orderCounter = 1;


    //adding new USer
    // Add user
    public void addUser(User user) {
        users.put(user.getUserId(), user);
    }

    //adding menu items
    public void addMenuItem(MenuItem item) {
        menu.put(item.getMenuItemId(), item);
    }



    //placing and order
    public Order placeOrder(int userId, List<Integer> itemIds) {
        User user = users.get(userId);
        if (user == null) {
            throw new IllegalArgumentException("User not found!");
        }
        Order order = new Order(orderCounter++, user);
        for (int itemId : itemIds) {
            MenuItem item = menu.get(itemId);
            if (item != null) {
                order.addItem(item);
            }
        }
        orders.put(order.getId(), order);
        return order;
    }

    // Cancel order
    public boolean cancelOrder(int orderId) {
        Order order = orders.get(orderId);
        if (order == null || order.getStatus() != Status.PLACED) {
            return false;
        }
        order.setStatus(Status.CANCELLED);
        return true;
    }

    // Complete order
    public boolean completeOrder(int orderId) {
        Order order = orders.get(orderId);
        if (order == null || order.getStatus() != Status.PLACED) {
            return false;
        }
        order.setStatus(Status.COMPLETED);
        return true;
    }

    // Get all orders for a user
    public List<Order> getUserOrders(int userId) {
        List<Order> result = new ArrayList<>();
        for (Order order : orders.values()) {
            if (order.getUser().getUserId() == userId) {
                result.add(order);
            }
        }
        return result;
    }

    // Show menu
    public List<MenuItem> getMenu() {
        return new ArrayList<>(menu.values());
    }
}
