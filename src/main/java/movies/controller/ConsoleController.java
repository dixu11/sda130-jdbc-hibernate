package movies.controller;
import movies.service.MovieService;

import javax.swing.*;
import java.util.Scanner;

//pozwala na interakcję z aplikacją (wprowadzanie danych, prezentacja rezultatów)
public class ConsoleController extends Controller{
    public ConsoleController(MovieService movieService) {
        super(movieService);
    }

    void showMessage(String message) {
        System.out.println(message);
    }

    int readInt(String message) {
        try{
            String input = readString(message);
            return Integer.parseInt(input);
        }catch (NumberFormatException e){
            showMessage( "Należy podać liczbę!");
            return readInt(message);
        }
    }
    String readString(String message) {
        Scanner scanner = new Scanner(System.in);
        showMessage(message);
        return scanner.nextLine();
    }
}
