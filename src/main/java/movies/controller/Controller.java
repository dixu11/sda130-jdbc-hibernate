package movies.controller;

import movies.exception.MovieServiceException;
import movies.model.Movie;
import movies.service.MovieService;

import javax.swing.*;
import java.sql.SQLException;
import java.util.List;

public abstract class Controller {
    private boolean running = true;
    private MovieService movieService;

    public Controller(MovieService movieService) {
        this.movieService = movieService;
    }

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
        } catch (MovieServiceException e){
            System.out.println(e.getMessage());
        }
    }

    private void executeOption(int input) throws MovieServiceException {
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

    private void addMovie() throws MovieServiceException {
        Movie movie = readMovieData();
        movieService.save(movie);
    }

    private void showMovies() throws MovieServiceException {
        List<Movie> movies = movieService.findAllMovies();
        showMovies(movies);
    }

    private Movie readMovieData() {
        String title = readString("Podaj tytuł:");
        int premiereYear = readInt("Podaj rok premiery:");
        String genre = readString("Podaj gatunek:");
        int rate = readInt("Podaj ocenę (1-5):");
        return new Movie(title, premiereYear, genre, rate);
    }

    private void showMovies(List<Movie> movies) {
        String allFilms = "";
        for (Movie movie : movies) {
            allFilms += movie + "\n";
        }
        showMessage(allFilms);
    }

    private void end() throws MovieServiceException {
        showMessage("Koniec");
        running = false;
        movieService.closeAllResources();
    }

    abstract void showMessage(String message);

    abstract int readInt(String message);

    abstract String readString(String message);
}

//WARSTWY:
//CONTROLLER>SERVICE>REPOSITIORY
