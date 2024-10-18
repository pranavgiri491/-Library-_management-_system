import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserService {

    private bookdb db = new bookdb();  // Reuse your database connection class

    // Method to add a user to the database
    public void addUser(Book.User user) {
        String query = "INSERT INTO users (user_id, name) VALUES (?, ?)";
        Connection conn = db.connect();

        if (conn != null) {  // Check if the connection was successful
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, user.getUserId());   // Set user_id
                stmt.setString(2, user.getName());  // Set user name

                stmt.executeUpdate();
                System.out.println("User added to the database: " + user.getName());

            } catch (SQLException e) {
                System.out.println("Error while adding user: " + e.getMessage());
            } finally {
                db.closeConnection(conn);
            }
        } else {
            System.out.println("Connection to the database failed.");
        }
    }

    // Method to remove a user from the database
    public void removeUser(int userId) {
        String query = "DELETE FROM users WHERE user_id = ?";
        Connection conn = db.connect();

        if (conn != null) {
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, userId);  // Set user ID to delete

                int rowsAffected = stmt.executeUpdate();  // Execute the delete statement
                if (rowsAffected > 0) {
                    System.out.println("User removed from the database: " + userId);
                } else {
                    System.out.println("User not found.");
                }

            } catch (SQLException e) {
                System.out.println("Error while removing user: " + e.getMessage());
            } finally {
                db.closeConnection(conn);  // Close the connection after the operation
            }
        } else {
            System.out.println("Failed to connect to the database.");
        }
    }

    // Method to display all users from the database
    public void displayUsers() {
        String query = "SELECT * FROM users";  // Query to fetch all users
        Connection conn = db.connect();

        if (conn != null) {  // Check if the connection was successful
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                ResultSet rs = stmt.executeQuery();

                System.out.println("Users List:");
                while (rs.next()) {
                    int userId = rs.getInt("user_id");
                    String name = rs.getString("name");

                    // Display user info
                    System.out.println("User ID: " + userId + ", Name: " + name);
                }

            } catch (SQLException e) {
                System.out.println("Error while displaying users: " + e.getMessage());
            } finally {
                db.closeConnection(conn);
            }
        } else {
            System.out.println("Connection to the database failed.");
        }
    }
}
