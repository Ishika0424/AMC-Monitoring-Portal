import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    public static Connection getConnection() throws Exception {
        // Adjust host, port, database name, username and password as per your setup:
        String url = "jdbc:mysql://localhost:3306/amc_monitoring_db"; 
        String user = "root";
        String password = "Rtyui@3456"; 
        Class.forName("com.mysql.cj.jdbc.Driver");  // Load MySQL driver
        return DriverManager.getConnection(url, user, password);
    }
}
