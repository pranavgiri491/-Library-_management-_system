import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookService {

    private bookdb db = new bookdb();  // Create an instance of the bookdb class for database connections

    // Method to add a book to the database
    public void addBook(Book book) {
        String query = "INSERT INTO books (title, author, is_issued) VALUES (?, ?, ?)";
        Connection conn = db.connect();  // Establish the connection

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            // Set the values for the placeholders in the SQL query
            stmt.setString(1, book.getTitle());   // Set title
            stmt.setString(2, book.getAuthor());  // Set author
            stmt.setBoolean(3, book.isIssued());  // Set issued status (true/false)

            // Execute the query to insert the book into the database
            stmt.executeUpdate();
            System.out.println("Book added to the database: " + book.getTitle());

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            db.closeConnection(conn);  // Close the connection after the operation
        }

    }
    // Method to remove a book from the database by its title
    public void removeBook(String title) {
        String query = "DELETE FROM books WHERE title = ?";
        Connection conn = db.connect();  // Establish the connection

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            // Set the title for the DELETE query
            stmt.setString(1, title);

            // Execute the query to remove the book from the database
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Book removed from the database: " + title);
            } else {
                System.out.println("No book found with the title: " + title);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            db.closeConnection(conn);  // Close the connection after the operation
        }
    }
    // Method to issue a book (set is_issued to true)
    public void issueBook(String title, String userId) {
        String query = "UPDATE books SET is_issued = true WHERE title = ? AND is_issued = false";
        Connection conn = db.connect();

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, title);  // Set the book title

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Book issued to user: " + userId);
            } else {
                System.out.println("Book is already issued or not found.");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            db.closeConnection(conn);
        }
    }

    // Method to return a book (set is_issued to false)
    public void returnBook(String title) {
        String query = "UPDATE books SET is_issued = false WHERE title = ? AND is_issued = true";
        Connection conn = db.connect();

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, title);  // Set the book title

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Book returned: " + title);
            } else {
                System.out.println("Book is not issued or not found.");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            db.closeConnection(conn);
        }
    }
    // Method to display all books from the database
    public void displayBooks() {
        String query = "SELECT * FROM books";
        Connection conn = db.connect();

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String title = rs.getString("title");
                String author = rs.getString("author");
                boolean isIssued = rs.getBoolean("is_issued");

                System.out.println("Title: " + title + ", Author: " + author + ", Issued: " + isIssued);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            db.closeConnection(conn);
        }
    }





}


