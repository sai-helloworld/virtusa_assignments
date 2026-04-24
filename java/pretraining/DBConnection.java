import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/JavaBank";
    private static final String USER = "root"; 
    private static final String PASS = "pass123";

    public static Connection getConnection() throws SQLException {
    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
    } catch (ClassNotFoundException e) {
        System.out.println("Driver not found in Classpath!");
    }
    return DriverManager.getConnection(URL, USER, PASS);
}
}