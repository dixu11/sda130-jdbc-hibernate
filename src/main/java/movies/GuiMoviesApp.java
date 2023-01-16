package movies;

import movies.controller.GuiController;
import movies.exception.MovieServiceException;
import movies.repository.JDBCMoviesRepository;
import movies.service.MovieService;

public class GuiMoviesApp {
    public static void main(String[] args) throws MovieServiceException {
        GuiController guiController = new GuiController(new MovieService(new JDBCMoviesRepository()));
        guiController.startMenu();
    }
}
