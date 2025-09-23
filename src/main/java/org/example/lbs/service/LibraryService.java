package org.example.lbs.service;

import org.example.lbs.model.Book;
import org.example.lbs.model.User;

import java.util.*;

public class LibraryService {
    private Map<Integer, Book> books = new HashMap<>();
    private Map<Integer, User> users = new HashMap<>();

    // Add a book
    public void addBook(Book book) {
        books.put(book.getId(), book);
    }

    // Add a user
    public void addUser(User user) {
        users.put(user.getId(), user);
    }

    // Borrow a book
    public boolean borrowBook(int userId, int bookId) {
        User user = users.get(userId);
        Book book = books.get(bookId);

        if (user == null || book == null) return false;
        if (book.isBorrowed()) return false;

        book.setBorrowed(true);
        user.borrowBook(book);
        return true;
    }

    // Return a book
    public boolean returnBook(int userId, int bookId) {
        User user = users.get(userId);
        Book book = books.get(bookId);

        if (user == null || book == null) return false;
        if (!book.isBorrowed()) return false;

        book.setBorrowed(false);
        user.returnBook(book);
        return true;
    }

    // Search books by title
    public List<Book> searchByTitle(String title) {
        List<Book> result = new ArrayList<>();
        for (Book b : books.values()) {
            if (b.getTitle().toLowerCase().contains(title.toLowerCase())) {
                result.add(b);
            }
        }
        return result;
    }

    // List all borrowed books for a user
    public List<Book> listBorrowedBooks(int userId) {
        User user = users.get(userId);
        if (user == null) return Collections.emptyList();
        return user.getBorrowedBooks();
    }
}
