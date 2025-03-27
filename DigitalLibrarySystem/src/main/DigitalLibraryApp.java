package main;

import java.util.Scanner;

import service.LibraryService;

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
}