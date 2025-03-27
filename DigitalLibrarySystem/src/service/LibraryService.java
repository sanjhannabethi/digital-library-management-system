package service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import model.Book;

public class LibraryService {
    private List<Book> books;

    public LibraryService() {
        books = new ArrayList<>();
    }

    // Add a book if the Book ID is unique
    public boolean addBook(Book book) {
        if(findBookById(book.getBookId()).isPresent()){
            System.out.println("Error: Book ID already exists!");
            return false;
        }
        books.add(book);
        return true;
    }

    // View all books
    public List<Book> getAllBooks() {
        return books;
    }

    // Search by ID
    public Optional<Book> findBookById(String bookId) {
        return books.stream().filter(b -> b.getBookId().equalsIgnoreCase(bookId)).findFirst();
    }

    // Search by title (partial match allowed)
    public List<Book> findBooksByTitle(String title) {
        List<Book> result = new ArrayList<>();
        for(Book book : books) {
            if(book.getTitle().toLowerCase().contains(title.toLowerCase())) {
                result.add(book);
            }
        }
        return result;
    }

    // Update a book's details
    public boolean updateBook(String bookId, String newTitle, String newAuthor, String newGenre, Book.AvailabilityStatus newStatus) {
        Optional<Book> optBook = findBookById(bookId);
        if(optBook.isPresent()){
            Book book = optBook.get();
            try {
                if(newTitle != null && !newTitle.trim().isEmpty()){
                    book.setTitle(newTitle);
                }
                if(newAuthor != null && !newAuthor.trim().isEmpty()){
                    book.setAuthor(newAuthor);
                }
                if(newGenre != null){
                    book.setGenre(newGenre);
                }
                if(newStatus != null){
                    book.setStatus(newStatus);
                }
            } catch(IllegalArgumentException e) {
                System.out.println("Update Error: " + e.getMessage());
                return false;
            }
            return true;
        }
        System.out.println("Book with ID " + bookId + " not found.");
        return false;
    }

    // Delete a book record
    public boolean deleteBook(String bookId) {
        Optional<Book> optBook = findBookById(bookId);
        if(optBook.isPresent()){
            books.remove(optBook.get());
            return true;
        }
        System.out.println("Book with ID " + bookId + " not found.");
        return false;
    }
}
