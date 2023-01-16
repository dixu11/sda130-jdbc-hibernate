package movies;

import movies.controller.ConsoleController;
import movies.repository.HibernateMoviesRepository;
import movies.service.MovieService;

//rozruch
public class ConsoleMoviesApp {
    public static void main(String[] args) {
      //Menu.startMenu(); - tak gdyby metoda była statyczna
        ConsoleController consoleController = new ConsoleController(new MovieService(new HibernateMoviesRepository()));
        consoleController.startMenu();
    }
}














//Single responsibility - pojedyncza odpowiedzialność
//Open-closed - kod powinien być otwarty na rozszerzanie i
// zamknięty na modyfikacje