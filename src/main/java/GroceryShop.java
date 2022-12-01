import service.UserOptions;

import java.util.Scanner;

public class GroceryShop {
    public static void main(String[] args) {
        System.out.println("Witaj w Grocery Shop!");
        System.out.println("Wybierz opcję: \n" +
                "1. Sprzedaż produktów \n" +
                "2. Sprawdzenie stanu magazynowego \n" +
                "3. Przyjęcie towaru \n" +
                "4. Utylizacja towaru \n");

        Scanner in = new Scanner(System.in);
        int userSelect = in.nextInt();
    }
}
