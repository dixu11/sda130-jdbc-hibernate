package movies;


//Single responsibility - pojedyncza odpowiedzialność
//Open-closed - kod powinien być otwarty na rozszerzanie i
// zamknięty na modyfikacje
//rozruch
public class MoviesApp {
    public static void main(String[] args) {
      //Menu.startMenu(); - tak gdyby metoda była statyczna
        Menu menu = new Menu();
        menu.startMenu();
    }
}
