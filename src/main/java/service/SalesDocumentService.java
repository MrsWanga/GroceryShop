package service;

import model.Product;
import model.SalesDocument;

import java.util.ArrayList;

public class SalesDocumentService {
    private static final SalesDocumentService INSTANCE = new SalesDocumentService();
    public static SalesDocumentService getInstance(){return INSTANCE;};
    public void addProduct(Product in, ArrayList<Product> products){
        products.add(in);
    }

    public double addPrices (SalesDocument sd, double price){
        sd.prices= sd.prices+price;
        return sd.prices;
    }

}
