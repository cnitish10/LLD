import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

/**
 * LibraryManagementSystem.java
 *
 * Single-file runnable Java implementation (in-memory) of a Library Management System
 * suitable for machine-coding / LLD interview practice.
 *
 * How to run:
 *   javac LibraryManagementSystem.java
 *   java LibraryManagementSystem
 *
 * The main() includes a small demo that acts like lightweight tests.
 */
public class LibraryManagementSystem {

    /* ---------------------------- Models ---------------------------- */

    public enum UserType { STUDENT, FACULTY, LIBRARIAN }

    public static class User {
        private final int userId;
        private final String name;
        private final UserType userType;

        public User(int userId, String name, UserType userType) {
            this.userId = userId;
            this.name = name;
            this.userType = userType;
        }

        public int getUserId() { return userId; }
        public String getName() { return name; }
        public UserType getUserType() { return userType; }

        @Override
        public String toString() {
            return String.format("User{id=%d, name=%s, type=%s}", userId, name, userType);
        }
    }

    public static class Book {
        private final int bookId;
        private String title;
        private String author;
        private final String isbn;
        private int totalCopies;
        private int availableCopies;

        public Book(int bookId, String title, String author, String isbn, int copies) {
            this.bookId = bookId;
            this.title = title;
            this.author = author;
            this.isbn = isbn;
            this.totalCopies = copies;
            this.availableCopies = copies;
        }

        public int getBookId() { return bookId; }
        public String getTitle() { return title; }
        public String getAuthor() { return author; }
        public String getIsbn() { return isbn; }
        public int getTotalCopies() { return totalCopies; }
        public int getAvailableCopies() { return availableCopies; }

        public boolean isAvailable() { return availableCopies > 0; }

        public void incrementCopy() {
            totalCopies++;
            availableCopies++;
        }

        public void decrementCopy() {
            if (availableCopies <= 0) throw new IllegalStateException("No copies available");
            availableCopies--;
        }

        public void returnCopy() {
            if (availableCopies < totalCopies) availableCopies++;
        }

        public void updateInfo(String title, String author) {
            if (title != null && !title.isEmpty()) this.title = title;
            if (author != null && !author.isEmpty()) this.author = author;
        }

        @Override
        public String toString() {
            return String.format("Book{id=%d, title=%s, author=%s, isbn=%s, avail=%d/%d}",
                    bookId, title, author, isbn, availableCopies, totalCopies);
        }
    }

    public static class BorrowRecord {
        private final UUID recordId;
        private final int userId;
        private final int bookId;
        private final LocalDate borrowDate;
        private final LocalDate dueDate;
        private LocalDate returnDate; // null if not returned

        public BorrowRecord(int userId, int bookId, LocalDate borrowDate, LocalDate dueDate) {
            this.recordId = UUID.randomUUID();
            this.userId = userId;
            this.bookId = bookId;
            this.borrowDate = borrowDate;
            this.dueDate = dueDate;
            this.returnDate = null;
        }

        public UUID getRecordId() { return recordId; }
        public int getUserId() { return userId; }
        public int getBookId() { return bookId; }
        public LocalDate getBorrowDate() { return borrowDate; }
        public LocalDate getDueDate() { return dueDate; }
        public LocalDate getReturnDate() { return returnDate; }

        public void markReturned(LocalDate returnDate) { this.returnDate = returnDate; }

        public boolean isOverdue() {
            LocalDate check = (returnDate == null) ? LocalDate.now() : returnDate;
            return check.isAfter(dueDate);
        }

        public long daysOverdue() {
            LocalDate check = (returnDate == null) ? LocalDate.now() : returnDate;
            if (!check.isAfter(dueDate)) return 0;
            return ChronoUnit.DAYS.between(dueDate, check);
        }

        @Override
        public String toString() {
            return String.format("BorrowRecord{id=%s, user=%d, book=%d, borrow=%s, due=%s, return=%s}",
                    recordId, userId, bookId, borrowDate, dueDate, returnDate);
        }
    }

    /* ---------------------------- Repositories (In-memory) ---------------------------- */

    // Simple in-memory repositories. In production, replace with DB adapters.
    public static class BookRepository {
        private final Map<Integer, Book> books = new HashMap<>();
        private int idCounter = 1000;

        public Book save(Book book) {
            books.put(book.getBookId(), book);
            return book;
        }

        public Book create(String title, String author, String isbn, int copies) {
            int id = ++idCounter;
            Book b = new Book(id, title, author, isbn, copies);
            books.put(id, b);
            return b;
        }

        public Optional<Book> findById(int bookId) {
            return Optional.ofNullable(books.get(bookId));
        }

        public List<Book> findAll() { return new ArrayList<>(books.values()); }

        public List<Book> findByTitle(String title) {
            List<Book> out = new ArrayList<>();
            for (Book b : books.values()) if (b.getTitle().toLowerCase().contains(title.toLowerCase())) out.add(b);
            return out;
        }

        public List<Book> findByAuthor(String author) {
            List<Book> out = new ArrayList<>();
            for (Book b : books.values()) if (b.getAuthor().toLowerCase().contains(author.toLowerCase())) out.add(b);
            return out;
        }

        public void delete(int bookId) { books.remove(bookId); }
    }

    public static class UserRepository {
        private final Map<Integer, User> users = new HashMap<>();
        private int idCounter = 0;

        public User create(String name, UserType type) {
            int id = ++idCounter;
            User u = new User(id, name, type);
            users.put(id, u);
            return u;
        }

        public Optional<User> findById(int userId) { return Optional.ofNullable(users.get(userId)); }

        public List<User> findAll() { return new ArrayList<>(users.values()); }
    }

    public static class BorrowRecordRepository {
        private final Map<UUID, BorrowRecord> records = new HashMap<>();

        public BorrowRecord save(BorrowRecord r) {
            records.put(r.getRecordId(), r);
            return r;
        }

        public List<BorrowRecord> findByUserId(int userId) {
            List<BorrowRecord> out = new ArrayList<>();
            for (BorrowRecord r : records.values()) if (r.getUserId() == userId) out.add(r);
            return out;
        }

        public List<BorrowRecord> findByBookId(int bookId) {
            List<BorrowRecord> out = new ArrayList<>();
            for (BorrowRecord r : records.values()) if (r.getBookId() == bookId) out.add(r);
            return out;
        }

        public Optional<BorrowRecord> findActiveRecord(int userId, int bookId) {
            for (BorrowRecord r : records.values()) {
                if (r.getUserId() == userId && r.getBookId() == bookId && r.getReturnDate() == null) return Optional.of(r);
            }
            return Optional.empty();
        }

        public List<BorrowRecord> findAll() { return new ArrayList<>(records.values()); }
    }

    /* ---------------------------- Services ---------------------------- */

    public static class BookService {
        private final BookRepository bookRepo;

        public BookService(BookRepository repo) { this.bookRepo = repo; }

        public Book addBook(String title, String author, String isbn, int copies) {
            return bookRepo.create(title, author, isbn, copies);
        }

        public void removeBook(int bookId) {
            bookRepo.delete(bookId);
        }

        public Optional<Book> getBook(int id) { return bookRepo.findById(id); }

        public List<Book> searchByTitle(String title) { return bookRepo.findByTitle(title); }

        public List<Book> searchByAuthor(String author) { return bookRepo.findByAuthor(author); }

        public List<Book> listAll() { return bookRepo.findAll(); }
    }

    public static class BorrowPolicy {
        private final int maxBooks;
        private final int borrowDays;

        public BorrowPolicy(int maxBooks, int borrowDays) {
            this.maxBooks = maxBooks;
            this.borrowDays = borrowDays;
        }

        public int getMaxBooks() { return maxBooks; }
        public int getBorrowDays() { return borrowDays; }
    }

    public static class BorrowService {
        private final BorrowRecordRepository recordRepo;
        private final UserRepository userRepo;
        private final BookRepository bookRepo;
        private final Map<UserType, BorrowPolicy> policyForType;

        public BorrowService(BorrowRecordRepository recordRepo, UserRepository userRepo, BookRepository bookRepo) {
            this.recordRepo = recordRepo;
            this.userRepo = userRepo;
            this.bookRepo = bookRepo;
            this.policyForType = new HashMap<>();
            // default policies
            policyForType.put(UserType.STUDENT, new BorrowPolicy(3, 14));
            policyForType.put(UserType.FACULTY, new BorrowPolicy(5, 28));
            policyForType.put(UserType.LIBRARIAN, new BorrowPolicy(10, 60));
        }

        public void setPolicy(UserType type, BorrowPolicy p) { policyForType.put(type, p); }

        public BorrowRecord borrowBook(int userId, int bookId) throws IllegalStateException {
            User user = userRepo.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));
            Book book = bookRepo.findById(bookId).orElseThrow(() -> new IllegalArgumentException("Book not found"));

            if (!book.isAvailable()) throw new IllegalStateException("Book not available");

            // check how many active books user has
            List<BorrowRecord> userRecords = recordRepo.findByUserId(userId);
            long activeCount = userRecords.stream().filter(r -> r.getReturnDate() == null).count();
            BorrowPolicy policy = policyForType.getOrDefault(user.getUserType(), policyForType.get(UserType.STUDENT));
            if (activeCount >= policy.getMaxBooks()) throw new IllegalStateException("User has reached borrowing limit");

            // create record
            LocalDate borrowDate = LocalDate.now();
            LocalDate dueDate = borrowDate.plusDays(policy.getBorrowDays());
            BorrowRecord rec = new BorrowRecord(userId, bookId, borrowDate, dueDate);
            recordRepo.save(rec);

            // reduce available copies
            book.decrementCopy();
            return rec;
        }

        public BorrowRecord returnBook(int userId, int bookId) {
            Optional<BorrowRecord> opt = recordRepo.findActiveRecord(userId, bookId);
            BorrowRecord rec = opt.orElseThrow(() -> new IllegalStateException("No active borrow record found"));
            rec.markReturned(LocalDate.now());

            // increment book
            Book b = bookRepo.findById(bookId).orElseThrow(() -> new IllegalArgumentException("Book not found"));
            b.returnCopy();
            return rec;
        }

        public List<BorrowRecord> getBorrowedForUser(int userId) { return recordRepo.findByUserId(userId); }

        public List<BorrowRecord> getAllRecords() { return recordRepo.findAll(); }

        public List<BorrowRecord> getOverdueRecords() {
            List<BorrowRecord> out = new ArrayList<>();
            for (BorrowRecord r : recordRepo.findAll()) if (r.getReturnDate() == null && r.isOverdue()) out.add(r);
            return out;
        }
    }

    /* ---------------------------- Simple Controller-like Facade ---------------------------- */

    public static class LibraryFacade {
        private final BookService bookService;
        private final BorrowService borrowService;
        private final UserRepository userRepo;

        public LibraryFacade(BookService bookService, BorrowService borrowService, UserRepository userRepo) {
            this.bookService = bookService;
            this.borrowService = borrowService;
            this.userRepo = userRepo;
        }

        // librarian operations
        public Book addBook(String title, String author, String isbn, int copies) {
            return bookService.addBook(title, author, isbn, copies);
        }

        public void removeBook(int bookId) { bookService.removeBook(bookId); }

        public List<Book> searchByTitle(String title) { return bookService.searchByTitle(title); }

        public List<Book> searchByAuthor(String author) { return bookService.searchByAuthor(author); }

        public List<Book> listAllBooks() { return bookService.listAll(); }

        // user operations
        public BorrowRecord borrowBook(int userId, int bookId) { return borrowService.borrowBook(userId, bookId); }
        public BorrowRecord returnBook(int userId, int bookId) { return borrowService.returnBook(userId, bookId); }
        public List<BorrowRecord> myBorrowHistory(int userId) { return borrowService.getBorrowedForUser(userId); }

        public List<BorrowRecord> overdue() { return borrowService.getOverdueRecords(); }
    }

    /* ---------------------------- Demo / Simple Tests ---------------------------- */

    public static void main(String[] args) {
        System.out.println("=== Library Management System Demo ===");

        // setup repositories
        BookRepository bookRepo = new BookRepository();
        UserRepository userRepo = new UserRepository();
        BorrowRecordRepository recordRepo = new BorrowRecordRepository();

        // services
        BookService bookService = new BookService(bookRepo);
        BorrowService borrowService = new BorrowService(recordRepo, userRepo, bookRepo);
        LibraryFacade facade = new LibraryFacade(bookService, borrowService, userRepo);

        // create users
        User alice = userRepo.create("Alice", UserType.STUDENT);
        User bob = userRepo.create("Bob", UserType.FACULTY);
        User lib = userRepo.create("Libby", UserType.LIBRARIAN);

        System.out.println("Created users: " + userRepo.findAll());

        // add books
        Book b1 = facade.addBook("Data Structures", "Ayush", "ISBN-DS-001", 3);
        Book b2 = facade.addBook("Operating Systems", "B. Tanenbaum", "ISBN-OS-002", 2);
        Book b3 = facade.addBook("Algorithms", "Cormen", "ISBN-ALG-003", 1);

        System.out.println("Books in library:");
        for (Book b : facade.listAllBooks()) System.out.println("  " + b);

        // Alice borrows Data Structures
        System.out.println("\nAlice borrows Data Structures");
        BorrowRecord r1 = facade.borrowBook(alice.getUserId(), b1.getBookId());
        System.out.println("Borrow record: " + r1);
        System.out.println("Book after borrow: " + bookRepo.findById(b1.getBookId()).get());

        // Bob borrows Data Structures twice (should be allowed once more)
        System.out.println("\nBob borrows Data Structures");
        BorrowRecord r2 = facade.borrowBook(bob.getUserId(), b1.getBookId());
        System.out.println("Borrow record: " + r2);
        System.out.println("Book after borrow: " + bookRepo.findById(b1.getBookId()).get());

        // try to borrow Algorithms twice (only 1 copy)
        System.out.println("\nAttempting to over-borrow Algorithms");
        BorrowRecord r3 = facade.borrowBook(alice.getUserId(), b3.getBookId());
        System.out.println("Borrow record: " + r3);
        try {
            facade.borrowBook(bob.getUserId(), b3.getBookId());
        } catch (Exception ex) {
            System.out.println("Expected error (no copies): " + ex.getMessage());
        }

        // return book
        System.out.println("\nAlice returns Data Structures");
        BorrowRecord ret = facade.returnBook(alice.getUserId(), b1.getBookId());
        System.out.println("Return record: " + ret);
        System.out.println("Book after return: " + bookRepo.findById(b1.getBookId()).get());

        // list borrow history for Alice
        System.out.println("\nAlice borrow history:");
        for (BorrowRecord r : facade.myBorrowHistory(alice.getUserId())) System.out.println("  " + r);

        // simulate overdue: create a manual record in the past to show overdue list
        System.out.println("\nSimulating overdue record (manual insert)");
        BorrowRecord old = new BorrowRecord(alice.getUserId(), b2.getBookId(), LocalDate.now().minusDays(30), LocalDate.now().minusDays(16));
        recordRepo.save(old);
        // decrement book available because it's borrowed
        bookRepo.findById(b2.getBookId()).ifPresent(Book::decrementCopy);

        System.out.println("Overdue records:");
        for (BorrowRecord ov : facade.overdue()) System.out.println("  " + ov + " daysOverdue=" + ov.daysOverdue());

        // search example
        System.out.println("\nSearch title 'Data':");
        for (Book found : facade.searchByTitle("Data")) System.out.println("  " + found);

        System.out.println("\nAll done. Demo finished.");
    }
}
