package model;

import java.util.ArrayList;

public class SalesDocument {
    static int id;
    ArrayList<Product> products;

    public SalesDocument() {
        id++;
        products= new ArrayList<>();
    }


    public void addProduct(Product in){
        this.products.add(in);
    }


}
