import model.DocumentWZ;
import model.SalesDocument;
import model.Stock;
import model.User;
import service.SalesDocumentService;
import service.StockService;
import service.UserService;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class GroceryShop {
    public static void main(String[] args) throws InterruptedException {

        Scanner in = new Scanner(System.in);
        StockService stockService = StockService.getInstance();
        Stock stock = StockService.getInstance().getStock();
        SalesDocumentService salesDocumentService = SalesDocumentService.getInstance();
        //logowanie domyślnego użytkownika
        User a = new User("Jan", "Kowalski", "admin", "admin");
        System.out.print("Podaj login: ");
        String log = in.nextLine();
        System.out.print("Podaj hasło: ");
        String pas = in.nextLine();
        if(a.logIN(log,pas)){
            //powitanie
            System.out.println("Witaj w Sklepie ABC!");
            System.out.println("Wybierz opcję: \n" +
                    "1. Sprzedaż produktów \n" +
                    "2. Sprawdzenie stanu magazynowego \n" +
                    "3. Przyjęcie towaru \n" +
                    "4. Utylizacja towaru \n");

            //sprawdzenie dostępności opcji
            int userSelect1 = in.nextInt();
            if(userSelect1!=1){
                System.out.println("Opcja ta jest jeszcze niedostępna! Spróbuj ponownie później.");
                System.exit(0);
            }else {
                //wyświetlenie stanu magazynowego
                System.out.println("Aktualny stan magazynowy:");
                System.out.println(stock);
                //stworzenie dokumentu sprzedaży
                SalesDocument salesDocument = new SalesDocument();
                //skanowanie
                UserService.getInstance().scanning(salesDocument, a, stock);
                System.out.println("\nAktualny stan magazynowy:");
                System.out.println(stock);
            }

        }else {
            System.exit(0);
        }


    }
}
