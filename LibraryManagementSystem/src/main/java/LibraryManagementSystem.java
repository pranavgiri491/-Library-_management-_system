import java.util.Scanner;

public class LibraryManagementSystem {
    private Admin admin;
    private Scanner scanner;
    private BookService bookService;
    private UserService userService;  // Add UserService instance

    public LibraryManagementSystem() {
        admin = new Admin();
        scanner = new Scanner(System.in);
        bookService = new BookService();  // Initialize BookService
        userService = new UserService();  // Initialize UserService
    }

    public void start() {
        while (true) {
            System.out.println("\n1. Add Book");
            System.out.println("2. Remove Book");
            System.out.println("3. Issue Book");
            System.out.println("4. Return Book");
            System.out.println("5. Display Books");
            System.out.println("6. Add User");
            System.out.println("7. Remove User");
            System.out.println("8. Display Users");
            System.out.println("9. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    addBook();
                    break;
                case 2:
                    removeBook();
                    break;
                case 3:
                    issueBook();
                    break;
                case 4:
                    returnBook();
                    break;
                case 5:
                    bookService.displayBooks();  // Display books from the database
                    break;
                case 6:
                    addUser();
                    break;
                case 7:
                    removeUser();
                    break;
                case 8:
                    userService.displayUsers();  // Display users from the database
                    break;
                case 9:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private void addBook() {
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();
        System.out.print("Enter book author: ");
        String author = scanner.nextLine();

        // Create a new book object and add it to the database
        Book newBook = new Book(title, author, false);  // Default isIssued to false
        bookService.addBook(newBook);  // Add book to the database
    }

    private void removeBook() {
        System.out.print("Enter book title to remove: ");
        String title = scanner.nextLine();
        admin.removeBook(title);  // Ensure Admin has this method
    }

    private void issueBook() {
        System.out.print("Enter book title to issue: ");
        String title = scanner.nextLine();
        System.out.print("Enter user ID: ");
        String userId = scanner.nextLine();
        admin.issueBook(title, userId);  // Ensure Admin has this method
    }

    private void returnBook() {
        System.out.print("Enter book title to return: ");
        String title = scanner.nextLine();
        admin.returnBook(title);  // Ensure Admin has this method
    }

    private void addUser() {
        System.out.print("Enter user name: ");
        String name = scanner.nextLine();
        System.out.print("Enter user ID: ");
        int userId = scanner.nextInt();
        userService.addUser(new Book.User(userId, name));  // Add user to the database
    }

    private void removeUser() {
        System.out.print("Enter user ID to remove: ");
        int userId = scanner.nextInt();
        userService.removeUser(userId);  // Call the removeUser method from UserService
    }
}
