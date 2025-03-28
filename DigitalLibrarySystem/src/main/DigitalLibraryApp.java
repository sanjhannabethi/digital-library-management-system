package main;

import java.util.List;
import java.util.Scanner;

import service.LibraryService;
import model.Book;
import model.Book.AvailabilityStatus;

public class DigitalLibraryApp {
    private static LibraryService libraryService = new LibraryService();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;
        while(running){
            printMenu();
            String choice = scanner.nextLine();
            switch(choice){
                case "1":
                    addBook();
                    break;
                case "2":
                    viewAllBooks();
                    break;
                case "3":
                    searchBook();
                    break;
                case "4":
                    updateBook();
                    break;
                case "5":
                    deleteBook();
                    break;
                case "6":
                    System.out.println("Exiting the system.");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please choose again.");
            }
        }
        scanner.close();
    }

    private static void printMenu(){
        System.out.println("\n=== Digital Library Book Management System ===");
        System.out.println("1. Add a Book");
        System.out.println("2. View All Books");
        System.out.println("3. Search Book by ID or Title");
        System.out.println("4. Update Book Details");
        System.out.println("5. Delete a Book Record");
        System.out.println("6. Exit System");
        System.out.print("Enter your choice: ");
    }

    private static void addBook(){
        try {
            System.out.print("Enter Book ID: ");
            String bookId = scanner.nextLine().trim();

            System.out.print("Enter Title: ");
            String title = scanner.nextLine().trim();

            System.out.print("Enter Author: ");
            String author = scanner.nextLine().trim();

            System.out.print("Enter Genre: ");
            String genre = scanner.nextLine().trim();

            System.out.print("Enter Availability Status (Available/Checked_Out): ");
            String statusInput = scanner.nextLine().trim().toUpperCase();
            AvailabilityStatus status;
            if(statusInput.equals("AVAILABLE")){
                status = AvailabilityStatus.AVAILABLE;
            } else if(statusInput.equals("CHECKED_OUT")){
                status = AvailabilityStatus.CHECKED_OUT;
            } else {
                System.out.println("Invalid status. Defaulting to Available.");
                status = AvailabilityStatus.AVAILABLE;
            }

            Book newBook = new Book(bookId, title, author, genre, status);
            if(libraryService.addBook(newBook)){
                System.out.println("Book added successfully.");
            }
        } catch(IllegalArgumentException e){
            System.out.println("Error adding book: " + e.getMessage());
        }
    }

    private static void viewAllBooks(){
        List<Book> books = libraryService.getAllBooks();
        if(books.isEmpty()){
            System.out.println("No books found.");
        } else {
            System.out.println("\nList of Books:");
            books.forEach(System.out::println);
        }
    }

    private static void searchBook(){
        System.out.print("Search by (1) ID or (2) Title? ");
        String option = scanner.nextLine();
        if(option.equals("1")){
            System.out.print("Enter Book ID: ");
            String id = scanner.nextLine().trim();
            libraryService.findBookById(id).ifPresentOrElse(
                book -> System.out.println("Found: " + book),
                () -> System.out.println("Book not found.")
            );
        } else if(option.equals("2")){
            System.out.print("Enter Title keyword: ");
            String title = scanner.nextLine().trim();
            List<Book> foundBooks = libraryService.findBooksByTitle(title);
            if(foundBooks.isEmpty()){
                System.out.println("No books found.");
            } else {
                foundBooks.forEach(System.out::println);
            }
        } else {
            System.out.println("Invalid search option.");
        }
    }

    private static void updateBook(){
        System.out.print("Enter Book ID to update: ");
        String bookId = scanner.nextLine().trim();
        System.out.print("Enter new Title (leave blank to keep current): ");
        String title = scanner.nextLine().trim();
        System.out.print("Enter new Author (leave blank to keep current): ");
        String author = scanner.nextLine().trim();
        System.out.print("Enter new Genre (leave blank to keep current): ");
        String genre = scanner.nextLine().trim();
        System.out.print("Enter new Availability Status (Available/Checked_Out, leave blank to keep current): ");
        String statusInput = scanner.nextLine().trim().toUpperCase();
        AvailabilityStatus status = null;
        if(!statusInput.isEmpty()){
            if(statusInput.equals("AVAILABLE")){
                status = AvailabilityStatus.AVAILABLE;
            } else if(statusInput.equals("CHECKED_OUT")){
                status = AvailabilityStatus.CHECKED_OUT;
            } else {
                System.out.println("Invalid status input. Keeping current status.");
            }
        }
        if(libraryService.updateBook(bookId, title.isEmpty() ? null : title, author.isEmpty() ? null : author, genre.isEmpty() ? null : genre, status)){
            System.out.println("Book updated successfully.");
        }
    }

    private static void deleteBook(){
        System.out.print("Enter Book ID to delete: ");
        String bookId = scanner.nextLine().trim();
        if(libraryService.deleteBook(bookId)){
            System.out.println("Book deleted successfully.");
        }
    }
}