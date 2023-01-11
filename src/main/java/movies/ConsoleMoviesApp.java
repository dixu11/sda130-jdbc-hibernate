package movies;

import movies.controller.ConsoleController;

//rozruch
public class ConsoleMoviesApp {
    public static void main(String[] args) {
      //Menu.startMenu(); - tak gdyby metoda była statyczna
        ConsoleController consoleController = new ConsoleController();
        consoleController.startMenu();
    }
}














//Single responsibility - pojedyncza odpowiedzialność
//Open-closed - kod powinien być otwarty na rozszerzanie i
// zamknięty na modyfikacje