package movies;

import java.util.Scanner;

//menu
public class Menu {


    //metoda powinna operować na jednym poziomie abstrakcji
    public  void startMenu() {
        showOptions();
        int input = readDecision();
        executeOption(input);
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

    private void executeOption(int input) {
        switch (input){
            case 1:
                System.out.println("Dodawanie filmów");
                break;
            case 2:
                System.out.println("Wyświetlanie");
                break;
            case 3:
                System.out.println("Koniec");
                break;
        }
    }










}
