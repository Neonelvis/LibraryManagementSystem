package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class LibraryManagementSystem {
    private static final Logger logger = LoggerFactory.getLogger(LibraryManagementSystem.class);

    private static LibraryService libraryService = new LibraryService();
    private static Scanner scanner = new Scanner(System.in);
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public static void main(String[] args) {
        boolean exit = false;
        while (!exit) {
            printMenu();
            int choice =getIntInput("Enter your choice: ");
            try {
                switch (choice) {
                    case 1:
                        addBook();
                        break;
                    case 5:
                        displayAllBooks();
                        break;
                    case 11:
                        exit = true;
                        System.out.println("Exiting the system, goodbye!");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (Exception e) {
                logger.error("An error occurred while performing the operation.", e);
                System.out.println("An error occurred, please try again.");
            }
        }
    }

    // a method to display the menu to the user
    public static void printMenu() {
        System.out.println("\n ---- Library Management System ---");
        System.out.println("1. Add a new book");
        System.out.println("2. Add a new member");
        System.out.println("3. Loan a book");
        System.out.println("4. Return a book");
        System.out.println("5. Display all books");
        System.out.println("6. Display all members");
        System.out.println("7. Display all loans");
        System.out.println("8. Generate book report (PDF)");
        System.out.println("9. Generate book report (Excel)");
        System.out.println("10. Generate book report (CSV)");
        System.out.println("11. Exit");
    }

    // a method to add a new book
    public static void addBook() throws SQLException {
        String title = getStringInput("Enter book title: ");
        String author = getStringInput("Enter book author: ");
        String isbn = getStringInput("Enter book ISBN");
        Date publishedDate = getDateInput("Enter published Date (yyyy-MM-dd): ");
        int quantity = getIntInput("Enter quantity: ");

        Book book = new Book(0, title, author, isbn, publishedDate, quantity);
        libraryService.addBook(book);
        System.out.println("Book added successfully.");
    }

    // a method to display all books
    public static void displayAllBooks() throws SQLException {
        List<Book> books = libraryService.getAllBooks();
        System.out.println("\nAll Books");
        for (Book book : books) {
            System.out.println(book);
        }
    }

    // helper methods
    public static String getStringInput(String prompt) {
        System.out.println(prompt);
        return scanner.nextLine();
    }

    public static int getIntInput (String prompt) {
        while (true) {
            try  {
                System.out.println(prompt);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    public static Date getDateInput (String prompt) {
        while (true) {
            try  {
                System.out.println(prompt);
                return dateFormat.parse(scanner.nextLine());
            } catch (ParseException e) {
                System.out.println("Invalid date format. Please use yyyy-MM-dd");
            }
        }
    }

}
