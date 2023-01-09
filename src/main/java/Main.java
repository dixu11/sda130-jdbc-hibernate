import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        //create database books;
        //Wersja dla MySql
        DriverManager.getConnection("jdbc:mysql://localhost:3306/books","root","TUTAJ HASLO");
      // DriverManager.getConnection("jdbc:postgresql://localhost:5432/testdb",
     //           "postgres", "MyPassword123");
        // "jdbc:mysql://localhost:3306/sonoo","root","root"


        /*Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/testdb",
                "postgres", "MyPassword123");

        connection.createStatement().execute("create table test(num int);");*/


    }
}

//Bazy danych:
// SQL / NoSQL (np. MongoDB)

//1. Samodzielny SQL
//2. JDBC - java wysyła zapytania SQL
    //zainstalowana i odpalona baza
    //sterownik (maven)
    //-miejsce na pisanie kodu - main?
    //Uzyskujemy Connection przez DriverManager
//3. Hibernate - zapisujemy OBIEKTY w bazie danych (ORM)
//4. Spring JPA - automatyzacja pracy z Hibernate przy Spring


