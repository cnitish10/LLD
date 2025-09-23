package org.example.librarymanagementsystem.model;

import java.util.List;

public class User {
    private int userId;
    private String userName;
    private List<Book> booksBorrowed;

    public User(int userId, String userName, List<Book> booksBorrowed) {
        this.userId = userId;
        this.userName = userName;
        this.booksBorrowed = booksBorrowed;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<Book> getBooksBorrowed() {
        return booksBorrowed;
    }

    public void setBooksBorrowed(List<Book> booksBorrowed) {
        this.booksBorrowed = booksBorrowed;
    }
}
