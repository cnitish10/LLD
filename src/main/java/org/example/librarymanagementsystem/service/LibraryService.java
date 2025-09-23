package org.example.librarymanagementsystem.service;

import com.sun.net.httpserver.Authenticator;
import org.example.librarymanagementsystem.model.Book;
import org.example.librarymanagementsystem.model.Library;

import java.util.List;

public class LibraryService {
    private Library library;
    LibraryService(){
       library  = new Library();
    }

    public void  addBook(Book book){
        List<Book> libraryBooks = library.getBooks();
        libraryBooks.add(book);
        System.out.println("Book added successfully");
    }


    public Book getBookById(int bookId) {
        Book book = library.getBooks().get(bookId);
        return book;
    }
}
