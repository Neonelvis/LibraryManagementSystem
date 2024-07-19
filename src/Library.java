import java.util.*;

public class Library
{
    public List<Book> books;
    private Map<String, List<Book>> borrowedBooks;

    private Scanner scanner;

    //constructor

    public Library ()
    {
        this.books = new ArrayList<>();
        this.borrowedBooks = new HashMap<>();
        this.scanner = new Scanner(System.in);
    }

    //a method to adda book to the List

    public void addBook(Book book)
    {
        books.add(book);
        System.out.println("Book with title: " + book.getTitle() + " added");


    }
    //a method to borrow a book
    public void borrowBook(String memberName, String bookTitle)
    {
        Book book = findBook(bookTitle);
        if (book != null && book.isAvailable())
        {
            book.setAvailable(false);
            borrowedBooks.computeIfAbsent(memberName, k -> new ArrayList<>()).add(book);
            System.out.println(memberName + "has borrowed: " + bookTitle);
        }
        else
        {
            System.out.println("Book with title " + bookTitle + " not available");
        }
    }

    //a method to return a book
    public void returnBook(String memberName, String bookTitle)
    {
        List<Book> memberBooks = borrowedBooks.get(memberName);
        if(memberBooks != null)
        {
            for(Book book : memberBooks)
            {
                if(book.getTitle().equals(bookTitle))
                {
                    book.setAvailable(true);
                    memberBooks.remove(book);
                    System.out.println(memberName + "has returned: " + book.getTitle() );
                    return;
                }
            }
        }
        else
        {
            System.out.println("Book with title '" + bookTitle + "' not found in the borrowed list");
        }
    }

    //a method to find a book by its title
    public Book findBook(String title)
    {
        for(Book book : books)
        {
            if(book.getTitle().equals(title))
            {
                return book;
            }
        }
        return null;
    }

    //a method to display all available books

    public void displayAvailableBooks()
    {
        System.out.println("Available books: ");
        for(Book book : books)
        {
            if(book.isAvailable())
            {
                System.out.println("- " + book.getTitle() + " 1by " + book.getAuthor());
            }
        }
    }

    //a method to run and display a menu to the user
    public void run()
    {
        while(true)
        {
            System.out.println("\n --- Library management system ---");
            System.out.println("1. Add a book");
            System.out.println("2. Borrow a book");
            System.out.println("3. Return a book");
            System.out.println("4. Display Available Books");
            System.out.println("5. Exit");
            System.out.println("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice)
            {
                case 1:
                    addBookMenu();
                    break;
                case 2:
                    borrowBookMenu();
                    break;
                case 3:
                    returnBookMenu();
                    break;
                case 4:
                    displayAvailableBooks();
                    break;
                case 5:
                    System.out.println("Thank You for using the Library Management System");
                    break;
                default:
                    System.out.println("Invalid choice Please try again");
            }
        }
    }

    //a method to display book menu
    public void addBookMenu()
    {
        System.out.println("Enter Book Title");
        String title = scanner.nextLine();
        System.out.println("Enter book Author");
        String author = scanner.nextLine();
        addBook(new Book(title, author));
    }

    //a method to display the borrow book menu
    public void borrowBookMenu()
    {
        System.out.println("Enter Member Name: ");
        String memberName = scanner.nextLine();
        System.out.println("Enter Book Title: ");
        String title = scanner.nextLine();
        borrowBook(memberName, title);
    }

    //a method to display return book menu
    public void returnBookMenu()
    {
        System.out.println("Enter Member Name: ");
        String memberName = scanner.nextLine();
        System.out.println("Enter Book Title: ");
        String title = scanner.nextLine();
        returnBook(memberName, title);
    }
}
