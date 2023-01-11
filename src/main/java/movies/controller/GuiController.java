package movies.controller;

import movies.model.Movie;
import movies.service.MovieService;

import javax.swing.*;
import java.sql.SQLException;
import java.util.List;

public class GuiController {
    private boolean running = true;
    private MovieService movieService = new MovieService();  //tylko jeden service na kontroler!
    private static final String OPTIONS = """
                Wybierz jedną z opcji:
                1. Dodaj nowy film
                2. Wyświetl filmy
                3. Koniec""";

    public void startMenu() {
        do {
            menuAction();
        } while (running);
    }

    private void menuAction() {
        int input = readInt(OPTIONS);
        handleOption(input);
    }

    private void handleOption(int input) {
        try {
            executeOption(input);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Błąd zapytania do BD");
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
        movieService.save(movie);
    }

    private void showMovies() throws SQLException {
        List<Movie> movies = movieService.findAllMovies();
        showMovies(movies);
    }

    private Movie readMovieData() {
        String title = readString("Podaj tytuł:");
        int premiereYear = readInt("Podaj rok premiery:");
        if (premiereYear < 1800 || premiereYear > 2100) {
            JOptionPane.showMessageDialog(null,"Podano nierealną datę premiery. " +
                    "Powinien być przedział: 1800 - 2100");
            return readMovieData();
        }
        String genre = readString("Podaj gatunek:");
        int rate = readInt("Podaj ocenę (1-5):");
        return new Movie(title, premiereYear, genre, rate);
    }

    private void showMovies(List<Movie> movies) {
        String allFilms = "";
        for (Movie movie : movies) {
            allFilms += movie + "\n";
        }
        JOptionPane.showMessageDialog(null,allFilms);
    }

    private void end() throws SQLException {
        JOptionPane.showMessageDialog(null,"Koniec");
        running = false;
        movieService.closeAllResources();
    }

    private int readInt(String message) {
      try{
          String input = readString(message);
          return Integer.parseInt(input);
      }catch (NumberFormatException e){
          JOptionPane.showMessageDialog(null, "Należy podać liczbę!");
          return readInt(message);
      }
    }
    private String readString(String message) {
        return JOptionPane.showInputDialog(message);
    }
}
