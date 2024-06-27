import java.util.*;

public class Library {

    public List<Book> books;
    private Map<String, List<Book>> borrowedBooks;
    private Scanner scanner;

    // constructor
    public Library () {
        this.books = new ArrayList<>();
        this.borrowedBooks = new HashMap<>();
        this.scanner = new Scanner(System.in);
    }

    // a method to add a book to the list
    public void addBook(Book book) {
        books.add(book);
        System.out.println("Book with title: " + book.getTitle() + " added.");
    }

    // a method to borrow a book
    public void borrowBook(String memberName, String bookTitle) {
        Book book = findBook(bookTitle);
        if (book != null && book.isAvailable()) {
            book.setAvailable(false);
            borrowedBooks.computeIfAbsent(memberName, k -> new ArrayList<>().add(book));
            System.out.println(memberName + " has borrowed: " + bookTitle);
        } else {
            System.out.println("Book with title " + bookTitle + " not available");
        }
    }

    // a method to return a book
    public void returnBook(String memberName, String bookTitle) {
        List<Book> memberBooks = borrowedBooks.get(memberName);
        if (memberBooks != null) {
            for (Book book : memberBooks) {
                if (book.getTitle().equals(bookTitle)) {
                    book.setAvailable(true);
                    memberBooks.remove(book);
                    System.out.println(memberName + " has returned: " + book.getTitle());
                    return;
                }
            }
        }
        System.out.println("Book with title '" + bookTitle + "' not found in the borrowed list");
    }

    // a method to find a book by its title
    public Book findBook(String title) {
        for (Book book : books) {
            if (book.getTitle().equals(title)) {
                return book;
            }
        }
        return null;
    }

    // a method to display all the available books
    public void displayAvailableBooks() {
        System.out.println("Available Books: ");
        for (Book book : books) {
            if (book.isAvailable()) {
                System.out.println("- " + book.getTitle() + " by" + book.getAuthor());
            }
        }
    }

    // a method to run and display a menu to the user
    public void run() {
        while (true) {
            System.out.println("\n --- Library Management System ---");
            System.out.println("1. Add a Book");
            System.out.println("2. Borrow a Book");
            System.out.println("3. Return a Book");
            System.out.println("4. Display Available Books");
            System.out.println("5. Exit");
            
            int choice = scanner.nextInt();
            scanner.nextLine();
            

            }
            
        }
    }
}
