package org.example.librarymanagementsystem.model;

import org.example.librarymanagementsystem.enums.Status;

import java.sql.Statement;

public class Book {
    private int bookId;
    private Status status;

    public Book(int bookId, Status status) {
        this.bookId = bookId;
        this.status = status;
    }

    public int getBookId() {
        return bookId;
    }

    public Status getStatus() {
        return status;
    }
}
