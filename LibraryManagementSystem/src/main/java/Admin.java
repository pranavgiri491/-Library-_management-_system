import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Admin {
    private List<Book> books;
  //  private List<User> users;

    public Admin() {
        books = new ArrayList<>();
       // users = new ArrayList<>();
    }

    // Book-related methods
    public void addBook(Book book) {
        books.add(book);
        System.out.println("Book added: " + book.getTitle());


    }

    public void removeBook(String title) {
        books.removeIf(book -> book.getTitle().equalsIgnoreCase(title));
        System.out.println("Book removed: " + title);
    }

    public void issueBook(String title, String userId) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title) && !book.isIssued()) {
                book.setIssued(true);
                System.out.println("Book issued to " + userId);
                return;
            }
        }
        System.out.println("Book not available.");
    }

    public void returnBook(String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title) && book.isIssued()) {
                book.setIssued(false);
                System.out.println("Book returned: " + title);
                return;
            }
        }
        System.out.println("Book not found.");
    }

    public void displayBooks() {
        for (Book book : books) {
            System.out.println(book);
        }
    }

    // User-related methods
    public void addUser(String username,int userId) {
        String insertSQL = "INSERT INTO public.\"UserInfo\"(\"userID\", \"UserName\") VALUES (?, ?);"; // Modify based on your table structure
        databaseconnection dbConnection = new databaseconnection();
        try (Connection conn = dbConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {

            // Set the values for the prepared statement
            pstmt.setString(2, username);
            pstmt.setInt(1, userId);

            // Execute the insert operation
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("User added successfully.");
            } else {
                System.out.println("Failed to add user.");
            }

        } catch (SQLException e) {
            System.out.println("Error adding user: " + e.getMessage());
        }
    }

    public void removeUser(String userId) {
     //   users.removeIf(user -> user.getUserId().equalsIgnoreCase(userId));
        System.out.println("User removed: " + userId);
    }

    public void displayUsers() {
//        for (User user : users) {
//            System.out.println(user);
//
//
//
//    }
    }




}



