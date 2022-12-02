package service;

import model.DocumentWZ;
import model.SalesDocument;
import model.User;

import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class UserService {
    private static final UserService INSTANCE =new UserService();
    public static UserService getInstance(){return INSTANCE;};

    SalesDocumentService salesDocumentService = SalesDocumentService.getInstance();
    StockService stockService = StockService.getInstance();

    public void scanning (SalesDocument sd, User user) throws InterruptedException {
        int i;
        Scanner in = new Scanner(System.in);
        System.out.print("Podaj kod kreskowy: ");
        long code = in.nextLong();
        try {
            if(!StockService.getInstance().isEmptyStock(code)) {
                StockService.getInstance().searchStock(code);
            }else{
                System.out.println("Nie można dodać tego produktu, gdyż nie ma go na stanie.");
            }
        } catch (NoSuchElementException e) {
            System.out.println("Nie ma takiego produktu");
            UserService.getInstance().scanning(sd, user);
        }
        if(!StockService.getInstance().isEmptyStock(code)){
            System.out.println("Dodano produkt: ");
            System.out.println( StockService.getInstance().searchStock(code));
            salesDocumentService.addProduct(StockService.getInstance().searchStock(code), sd.products);
            salesDocumentService.addPrices(sd,StockService.getInstance().searchStock(code).getPrice());
            System.out.println("Zakupy: " + sd.products + "\n"+
                    "Cena produktów:" + sd.prices);
            System.out.println("Wybierz opcję: \n" +
                    "1. Dodanie kolejnego produktu \n" +
                    "2. Zatwierdź sprzedaż \n");
            int userSelect2 = in.nextInt();
            if (userSelect2==1){
                UserService.getInstance().scanning(sd, user);
            }else if (userSelect2==2){
                if(UserService.getInstance().isConfirmPay()){
                    DocumentWZ.getInstance().createWZ(sd, user);
                    stockService.downgradeStock(sd.products);
                    System.out.println("Drukowanie paragonu...");
                    TimeUnit.SECONDS.sleep(5);
                    System.out.println(sd.printReceipt());
                }else {
                    System.out.println("Anulowano sprzedaż");
                }
            }else {
                System.out.println("Podano niepoprawny numer opcji.");
            }
        }else{
            System.out.println("Nie można dodać tego produktu, gdyż nie ma go na stanie.");
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
