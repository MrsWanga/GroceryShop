import model.SalesDocument;
import model.Stock;
import model.User;

import java.util.Scanner;

public class GroceryShop {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Stock defaultStock = new Stock();
        //logowanie domyślnego użytkownika
        User a = new User("Jan", "Kowalski", "admin", "admin");
        System.out.print("Podaj login: ");
        String log = in.nextLine();
        System.out.print("Podaj hasło: ");
        String pas = in.nextLine();
        if(a.logIN(log,pas)){
            //powitanie
            System.out.println("Witaj w Grocery Shop!");
            System.out.println("Wybierz opcję: \n" +
                    "1. Sprzedaż produktów \n" +
                    "2. Sprawdzenie stanu magazynowego \n" +
                    "3. Przyjęcie towaru \n" +
                    "4. Utylizacja towaru \n");

            //sprawdzenie dostępności opcji
            int userSelect = in.nextInt();
            if(userSelect!=1){
                System.out.println("Opcja ta jest jeszcze niedostępna! Spróbuj ponownie później.");
                System.exit(0);
            }else {
                //wyświetlenie stanu magazynowego
                System.out.println("Aktualny stan magazynowy:");
                System.out.println(defaultStock);
                SalesDocument salesDocument = new SalesDocument();
                System.out.print("Podaj kod kreskowy: ");
                long code = in.nextLong();
//                salesDocument.addProduct();
            }

        }else {
            System.exit(0);
        }


    }
}
