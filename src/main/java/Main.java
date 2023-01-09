import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/testdb",
                "postgres", "MyPassword123");

        connection.createStatement().execute("create table test(num int);");

        // "jdbc:mysql://localhost:3306/sonoo","root","root"
    }
}
