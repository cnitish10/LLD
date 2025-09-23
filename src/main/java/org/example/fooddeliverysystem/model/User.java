package org.example.fooddeliverysystem.model;

import java.util.ArrayList;
import java.util.List;

public class User {
    private int userId;
    private String userName;

    public User(int userId, String  userName) {
        this.userId = userId;
        this.userName = userName;
    }

    public int getUserId() {
        return userId;
    }

    public String  getUserName() {
        return userName;
    }



}
