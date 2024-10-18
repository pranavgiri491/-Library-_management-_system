import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class bookdb {
    private final String url = "jdbc:postgresql://localhost:5432/libraryManagement"; // Replace with your database name
    private final String user = "postgres"; // Replace with your PostgreSQL username
    private final String password = "1234"; // Replace with your PostgreSQL password
    public Connection connect() {
        Connection conn = null;
        try {
            // Establish connection
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

// Close the connection
public void closeConnection(Connection conn) {
    if (conn != null) {
        try {
            conn.close();
            System.out.println("Connection closed.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}}
