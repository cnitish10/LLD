package org.example.librarymanagementsystem.service;

import org.example.librarymanagementsystem.model.Book;
import org.example.librarymanagementsystem.model.User;

public interface LibraryManagementService {
    void addBook(Book book);
    void removeBook(Book book);

    void issueBook(Book book, User user);

    void returnBook(Book book, User user);
}
