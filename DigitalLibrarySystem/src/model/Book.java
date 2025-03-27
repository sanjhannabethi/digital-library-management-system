package model;

public class Book {
    public enum AvailabilityStatus {
        AVAILABLE, CHECKED_OUT
    }

    private String bookId;
    private String title;
    private String author;
    private String genre;
    private AvailabilityStatus status;
    
    /*
     * Constructor to initialize a Book object with the given attributes.
     * bookId - Unique identifier for the book (cannot be empty)
     * title - Title of the book (cannot be empty)
     * author - Author of the book (cannot be empty)
     * genre - Genre of the book
     * status - Availability status of the book (AVAILABLE or CHECKED_OUT)
     * throws IllegalArgumentException - if bookId, title, or author are empty or null
    */

    public Book(String bookId, String title, String author, String genre, AvailabilityStatus status) {
        if(bookId == null || bookId.trim().isEmpty()){
            throw new IllegalArgumentException("\nBook ID cannot be empty. Please enter a unique Book ID.");
        }
        if(title == null || title.trim().isEmpty()){
            throw new IllegalArgumentException("\nTitle cannot be empty!");
        }
        if(author == null || author.trim().isEmpty()){
            throw new IllegalArgumentException("\nAuthor cannot be empty!");
        }

        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.status = status;
    }

    // Get and set methods

    public String getBookId() {
        return bookId;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        if(title == null || title.trim().isEmpty()){
            throw new IllegalArgumentException("Title cannot be empty!");
        }
        this.title = title;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        if(author == null || author.trim().isEmpty()){
            throw new IllegalArgumentException("Author cannot be empty!");
        }
        this.author = author;
    }
    public String getGenre() {
        return genre;
    }
    public void setGenre(String genre) {
        this.genre = genre;
    }
    public AvailabilityStatus getStatus() {
        return status;
    }
    public void setStatus(AvailabilityStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Book [ID = " + bookId + ", Title = " + title + ", Author = " + author +
                ", Genre = " + genre + ", Status = " + status + "]";
    }
}
