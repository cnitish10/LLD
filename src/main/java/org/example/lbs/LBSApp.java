package org.example.lbs;

import org.example.lbs.model.Book;
import org.example.lbs.model.User;
import org.example.lbs.service.LibraryService;

public class LBSApp {
    public static void main(String args[]){
        LibraryService library = new LibraryService();

        // Add Books
        library.addBook(new Book(1, "Clean Code", "Robert Martin"));
        library.addBook(new Book(2, "Design Patterns", "GoF"));
        library.addBook(new Book(3, "Effective Java", "Joshua Bloch"));

        // Add Users
        library.addUser(new User(1, "Alice"));
        library.addUser(new User(2, "Bob"));

        // Borrow a Book
        System.out.println("Alice borrows 'Clean Code': " + library.borrowBook(1, 1));

        // Try to borrow already borrowed book
        System.out.println("Bob tries to borrow 'Clean Code': " + library.borrowBook(2, 1));

        // Search by Title
        System.out.println("Search results for 'Design': " + library.searchByTitle("Design"));

        // List borrowed books for Alice
        System.out.println("Alice's borrowed books: " + library.listBorrowedBooks(1));

        // Return book
        System.out.println("Alice returns 'Clean Code': " + library.returnBook(1, 1));

        // Borrow again after return
        System.out.println("Bob borrows 'Clean Code': " + library.borrowBook(2, 1));
    }
}
