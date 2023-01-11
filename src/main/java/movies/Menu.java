package movies;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//menu
public class Menu {
    private boolean running = true;
    private Connection connection;
    private static final String DB_URL = "jdbc:mysql://localhost:3306/movies";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "MyPassword123";
    private static final String CREATE_MOVIES_TABLE_SQL = """
            CREATE TABLE IF NOT EXISTS movies (
            id int AUTO_INCREMENT PRIMARY KEY,
            title varchar(255) NOT NULL,
            premiere_year int NOT NULL,
            genre varchar(255) NOT NULL,
            rate int
            );""";

    private static final String INSERT_MOVIE_SQL = "INSERT INTO movies VALUES (0,?,?,?,?);";
    private static final String SELECT_ALL_MOVIES_SQL = "SELECT * FROM movies;";

    public Menu() {
        try {
            initConnection();
            initTable();
        } catch (SQLException e) {
            System.out.println("Wysypała się baza danych :(");
        }
    }

    private void initConnection() throws SQLException {
        connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
    }

    private void initTable() throws SQLException {
        PreparedStatement statement = connection.prepareStatement(CREATE_MOVIES_TABLE_SQL);
        statement.execute();
    }


    public void startMenu() {
        do {
            menuAction();
        } while (running);
    }

    private void menuAction() {
        showOptions();
        int input = readDecision();
        handleOption(input);
    }

    private void showOptions() {
        System.out.println("""
                Wybierz jedną z opcji:
                1. Dodaj nowy film
                2. Wyświetl filmy
                3. Koniec""");
    }

    private int readDecision() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    private void handleOption(int input) {
        try {
           executeOption(input);
        } catch (SQLException e) {
            System.out.println("Błąd zapytania do BD");
        }

    }

    private void executeOption(int input) throws SQLException{
        switch (input) {
            case 1:
                addMovie();
                break;
            case 2:
                showMovies();
                break;
            case 3:
                end();
                break;
        }
    }

    private void addMovie() throws SQLException {
        Movie movie = readMovieData();
        save(movie);
    }

    private Movie readMovieData() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Podaj tytuł:");
        String title = scanner.nextLine();
        System.out.print("Podaj rok premiery:");
        int premiereYear = scanner.nextInt();
        if (premiereYear < 1800 || premiereYear > 2100) {
            System.out.println("Podano nierealną datę premiery. " +
                    "Powinien być przedział: 1800 - 2100");
            return readMovieData();
        }
//        scanner = new Scanner(System.in);
        scanner.nextLine();
        System.out.print("Podaj gatunek:");
        String genre = scanner.nextLine();
        System.out.print("Podaj ocenę (1-5):");
        int rate = scanner.nextInt();
        return new Movie(title, premiereYear, genre, rate);
    }

    private void save(Movie movie) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(INSERT_MOVIE_SQL);
        statement.setString(1, movie.getTitle());
        statement.setInt(2, movie.getPremiereYear());
        statement.setString(3, movie.getGenre());
        statement.setInt(4, movie.getRate());
        statement.execute();
    }

    private void showMovies() throws SQLException {
        List<Movie> movies = new ArrayList<>();
        PreparedStatement statement = connection.prepareStatement(SELECT_ALL_MOVIES_SQL);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            int id = resultSet.getInt(1);
            String title = resultSet.getString(2);
            int year = resultSet.getInt(3);
            String genre = resultSet.getString(4);
            int rate = resultSet.getInt(5);
            Movie movie = new Movie(id, title, year, genre, rate);
            movies.add(movie);
        }

        for (Movie movie : movies) {
            System.out.println(movie);
        }
    }

    private void end() throws SQLException {
        System.out.println("Koniec");
        running = false;
        connection.close();
    }


    //każda metoda powinna operować na jednym poziomie abstrakcji
}
