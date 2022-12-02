package model;

import java.util.ArrayList;

public class SalesDocument {
    static int id;
    public ArrayList<Product> products;
    public double prices=0.0;

    public SalesDocument() {
        id++;
        products= new ArrayList<>();
    }

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        SalesDocument.id = id;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public String printReceipt(){
        StringBuilder sb = new StringBuilder();
        sb.append("Sklep ABC " +
                "Aleja Jana Pawła II 32\n"+
                "00-141 Warszawa\n");
        this.products.forEach((p)-> sb.append(p.getName()+"   "+p.getPrice()+"\n"));
        sb.append("Razem: "+ prices);
        return sb.toString();
    }







}
