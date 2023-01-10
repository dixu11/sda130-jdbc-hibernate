package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) throws SQLException{ //Obsługa wyjątku!
        //Pamiętać o Sterowniku - pom
        //Trzeba najpierw przez SQL zrobić bazę
        //create database books;

        //Nawiązanie połączenia: (tylko raz)

        //Wersja dla MySql
       Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/books","root","MyPassword123");

        //Wersja dla Postgresql
     // Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/testdb",
       //         "postgres", "MyPassword123");



      //Stworzenie tabeli:
        String createTableSql = """
                CREATE TABLE books (
                id int AUTO_INCREMENT PRIMARY KEY,
                title varchar(255) NOT NULL,
                author varchar(255) NOT NULL,
                pages int NOT NULL
                );
                """;

        // connection.createStatement().execute(createTableSql);


        //Dodanie książki:
        Book book = new Book("Ubik", "Philip Dick", 250);
        Statement statement = connection.createStatement();
        //formatowany String - patrz reminder
        String insertBookSql = String.format(" INSERT INTO books VALUES (0,'%s','%s',%d);",
                book.getTitle(), book.getAuthor(), book.getPages());

        System.out.println(insertBookSql);
        //statement.execute(insertBookSql);

       /* żeby nie trzeba było podawać ID można taką składnię:
        "INSERT INTO books(title, author, pages) VALUES ('%s','%s',%d);"*/

        //update:
        String updateBook = """
                UPDATE books
                SET author='Philip K. Dick'
                WHERE title='Ubik';
                """;

        statement.execute(updateBook);

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
    //uwaga SQLException
//3. Hibernate - zapisujemy OBIEKTY w bazie danych (ORM)
//4. Spring JPA - automatyzacja pracy z Hibernate przy Spring


