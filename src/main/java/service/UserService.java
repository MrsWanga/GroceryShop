package service;

import Exceptions.EmptyStockException;
import model.DocumentWZ;
import model.SalesDocument;
import model.Stock;
import model.User;

import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class UserService {
    private static final UserService INSTANCE =new UserService();
    public static UserService getInstance(){return INSTANCE;};

    SalesDocumentService salesDocumentService = SalesDocumentService.getInstance();
    StockService stockService = StockService.getInstance();

    public void scanning (SalesDocument sd, User user, Stock s) throws InterruptedException {
        int i;
        Scanner in = new Scanner(System.in);
        System.out.print("Podaj kod kreskowy: ");
        long code = in.nextLong();
        try {
            Stock.isBookStock(StockService.getInstance().searchStock(code),s);
            System.out.println("Dodano produkt: ");
            System.out.println( StockService.getInstance().searchStock(code));
            salesDocumentService.addProduct(StockService.getInstance().searchStock(code), sd.products);
            salesDocumentService.addPrices(sd,StockService.getInstance().searchStock(code).getPrice());
            System.out.println("Zakupy: " + sd.products + "\n"+
                    "Cena produktów:" + sd.prices);
            scanningOrConfirmQuestion(sd, user, s);
        } catch (NoSuchElementException e) {
            System.out.println("Nie ma takiego produktu");
            scanningOrConfirmQuestion(sd, user, s);
        } catch (EmptyStockException e) {
            System.out.println("Nie można dodać tego produktu, gdyż nie ma go na stanie.");
            scanningOrConfirmQuestion(sd, user, s);
        }
    }

    public void scanningOrConfirmQuestion(SalesDocument sd, User user, Stock s) throws InterruptedException {
        Scanner in = new Scanner(System.in);
        System.out.println("Wybierz opcję: \n" +
                "1. Dodanie kolejnego produktu \n" +
                "2. Zatwierdź sprzedaż \n");
        int userSelect2 = in.nextInt();
        if (userSelect2==1){
            UserService.getInstance().scanning(sd, user, s);
        }else if (userSelect2==2){
            confirmSale(sd, user);
        }else {
            System.out.println("Podano niepoprawny numer opcji.");
        }
    }

    public void confirmSale(SalesDocument sd, User user) throws InterruptedException {
        if(UserService.getInstance().isConfirmPay()){
            DocumentWZ.getInstance().createWZ(sd, user);
            System.out.println("Drukowanie paragonu...");
            TimeUnit.SECONDS.sleep(5);
            System.out.println(sd.printReceipt());
        }else {
            stockService.upgradeStock(sd.products);
            System.out.println("Anulowano sprzedaż");
        }
    }

    public boolean isConfirmPay (){
        Scanner in = new Scanner(System.in);
        boolean isConfirm =false;
        boolean isCorrect=false;
        while (isCorrect==false){
            System.out.println("Wybierz opcję: \n" +
                    "1. Potwierdzam płatność \n" +
                    "2. Anulowanie płatności");
            int choice=in.nextInt();
            if(choice==1){
                isConfirm=true;
                isCorrect=true;
            }else if(choice==2){
                System.out.println("Anulowano transakcję z powodu braku płatności");
                isCorrect=true;
            }else{
                System.out.println("Podano niepoprawny numer. "+"Wybierz opcję: \n" +
                        "1. Potwierdzam płatność \n" +
                        "2. Anulowanie płatności");
                isCorrect=false;
            }
        }
        return isConfirm;
    }
}
