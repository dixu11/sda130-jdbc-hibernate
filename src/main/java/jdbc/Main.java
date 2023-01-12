package jdbc;

import java.sql.*;

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

        //CRUD
        //Create
        //Dodanie książki:
        Book book = new Book("Ubik", "Philip Dick", 250);
        //SQL injection - rodzaj ataku hakerskiego
        //Book book = new Book("a','a',1); drop database books; --", "Philip Dick", 250);
        Statement statement = connection.createStatement();
        //formatowany String - patrz reminder
        String insertBookSql = String.format(" INSERT INTO books VALUES (0,'%s','%s',%d);",
                book.getTitle(), book.getAuthor(), book.getPages());

        //System.out.println(insertBookSql);
        //statement.execute(insertBookSql);

       /* żeby nie trzeba było podawać ID można taką składnię:
        "INSERT INTO books(title, author, pages) VALUES ('%s','%s',%d);"*/

        //Update:
        String updateBookSql = """
                UPDATE books
                SET author='Philip K. Dick'
                WHERE title='Ubik';
                """;

        //statement.execute(updateBookSql);

        //Read
        String selectAllSql = "SELECT * FROM books WHERE pages<275";

       ResultSet resultSet = statement.executeQuery(selectAllSql);
      while ( resultSet.next()){
          int id = resultSet.getInt("id");
        //  System.out.println(id );
          String title = resultSet.getString("title");
         // System.out.println(title);
          String author = resultSet.getString("author");
       //   System.out.println(author);
          int pages = resultSet.getInt("pages");
       //   System.out.println(pages);
          Book dbBook = new Book(id, title, author, pages);
          System.out.println(dbBook);
      }

      //Delete
        String deleteSql = "DELETE FROM books WHERE id = 2";
        //statement.execute(deleteSql);



        //aby uniknąć SQL injection attack - zawsze należy używać PreparedStatement a nie Statement
        String insertSql = "INSERT INTO books VALUES(0,?,?,?);";
        PreparedStatement preparedStatement = connection.prepareStatement(insertSql);
        preparedStatement.setString(1,"Czysta Architektura");
        preparedStatement.setString(2, "Robert Martin");
        preparedStatement.setInt(3, 350);
        preparedStatement.execute();
       // preparedStatement.executeUpdate();


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


