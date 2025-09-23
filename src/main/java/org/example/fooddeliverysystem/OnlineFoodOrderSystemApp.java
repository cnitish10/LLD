package org.example.fooddeliverysystem;

import org.example.fooddeliverysystem.model.MenuItem;
import org.example.fooddeliverysystem.model.Order;
import org.example.fooddeliverysystem.model.User;
import org.example.fooddeliverysystem.service.OnlineFoodOrderService;

import java.util.Arrays;

public class OnlineFoodOrderSystemApp {
    public static void main(String args[]){
        OnlineFoodOrderService service = new OnlineFoodOrderService();

        // Add Users
        service.addUser(new User(1, "Nitish"));
        service.addUser(new User(2, "Manish"));

        // Add Menu Items
        service.addMenuItem(new MenuItem(1, "Pizza", 8));
        service.addMenuItem(new MenuItem(2, "Burger", 5));
        service.addMenuItem(new MenuItem(3, "Pasta", 7));

        System.out.println("Menu: " + service.getMenu());

        // Place Orders
        Order order1 = service.placeOrder(1, Arrays.asList(1, 2)); // Alice
        Order order2 = service.placeOrder(2, Arrays.asList(3));    // Bob

        System.out.println("Placed: " + order1);
        System.out.println("Placed: " + order2);

        // Cancel Bob's order
        System.out.println("Cancel Manish's order: " + service.cancelOrder(order2.getId()));

        // Complete Alice's order
        System.out.println("Complete Nitish's order: " + service.completeOrder(order1.getId()));

        // Get Alice's orders
        System.out.println("Nitish's orders: " + service.getUserOrders(1));
    }
}
