//package org.example.librarymanagementsystem.service;
//
//import org.example.librarymanagementsystem.enums.Status;
//import org.example.librarymanagementsystem.model.Book;
//import org.example.librarymanagementsystem.model.User;
//
//import java.util.Map;
//
//public class LibraryManagementServiceImpl implements LibraryManagementService{
//    Map<Integer, Integer> bookMapping;
//    private LibraryService libraryService;
//
//    LibraryManagementServiceImpl(){
//        libraryService = new LibraryService();
//    }
//    @Override
//    public void addBook(Book book) {
//        libraryService.addBook(book);
//    }
//
//    @Override
//    public void removeBook(Book book) {
//        // remove book logic to add in library service
//    }
//
//    @Override
//    public void issueBook(Book book, User user) {
//        Book book1 = libraryService.getBookById(book.getBookId());
//        if(bookMapping.containsKey(book1.getBookId())){
//            System.out.println("cannot issue");
//        }
//        else{
//            bookMapping.put(book.getBookId(), user.getUserId());
//            userService.issueBook(book);
//        }
//    }
//
//    @Override
//    public void returnBook(Book book, User user) {
//        Book book1 = libraryService.getBookById(book.getBookId());
//        if(bookMapping.containsKey(book1.getBookId())){
//            System.out.println("cannot issue");
//        }
//        else{
//            bookMapping.put(book.getBookId(), user.getUserId());
//            userService.issueBook(book);
//        }
//    }
//}
