package org.example.librarymanagementsystem.model;

import java.util.ArrayList;
import java.util.List;

public class Library {
    List<Book> books;
    List<User> users;

    public Library() {
        this.books = new ArrayList<>();
        this.users = new ArrayList<>();
    }

    public List<Book> getBooks() {
        return books;
    }

    public List<User> getUsers() {
        return users;
    }
}
