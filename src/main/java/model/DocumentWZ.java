package model;

import service.SalesDocumentService;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

public class DocumentWZ extends Document{
    private static final DocumentWZ INSTANCE = new DocumentWZ();
    public static DocumentWZ getInstance(){return INSTANCE;};
    public void createWZ(SalesDocument sd, User user){
        dayCreateThisDocument = new Date();
        userCreatedDocument = user;
        UUID uuid = UUID.randomUUID();
        try {
            FileWriter myWriter = new FileWriter("documentWZ"+uuid+".txt");
            myWriter.write("DOCUMENT WYDANIA ZEWNĘTRZNEGO\n");
            myWriter.write("Wystawił: ");
            myWriter.write(userCreatedDocument.name+" "+userCreatedDocument.lastname);
            myWriter.write("\nData wystawienia: ");
            myWriter.write(dayCreateThisDocument.toString() + "\n");
            myWriter.write(sd.printReceipt());
            myWriter.close();
            System.out.println("Wystawiono dokument WZ");
        } catch ( IOException e) {
            System.out.println("Błąd! Nie wystawiono dokumentu WZ");
            e.printStackTrace();
        }
    }
}
