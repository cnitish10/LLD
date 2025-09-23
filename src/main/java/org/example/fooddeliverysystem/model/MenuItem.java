package org.example.fooddeliverysystem.model;

public class MenuItem {
    private int menuItemId;
    private String menuItem;

    private int price;

    public MenuItem(int menuItemId, String menuItem, int price) {
        this.menuItemId = menuItemId;
        this.menuItem = menuItem;
        this.price = price;
    }

    public int getMenuItemId() {
        return menuItemId;
    }

    public String getMenuItem() {
        return menuItem;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return menuItemId + " - " + menuItem + " (INR" + price + ")";
    }

}
