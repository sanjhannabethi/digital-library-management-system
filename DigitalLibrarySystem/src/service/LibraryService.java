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
}
