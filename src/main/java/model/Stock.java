package model;

import java.util.ArrayList;

public class Stock {
    private ArrayList<ProductItem> stock;

    public Stock(){
        stock = new ArrayList<>();
        createStock();
    }

    public ArrayList<ProductItem> getStock() {
        return stock;
    }

    public void setStock(ArrayList<ProductItem> stock) {
        this.stock = stock;
    }

    private void createStock(){
        ProductItem flour = new ProductItem(new Product("mąka pszenna 1kg", 2.5, 5908215500207L),10);
        ProductItem eggs =new ProductItem( new Product("jaja kurze 10szt", 12, 5908235955469L),1);
        ProductItem water = new ProductItem(new Product("woda niegazowana 1L",2.0, 5900635001364L),20);
        ProductItem milk = new ProductItem(new Product("mleko 1L", 4.0, 5900512320359L ),50);
        ProductItem butter =new ProductItem( new Product("masło 200g", 9.0, 5900820000257L),15);
        ProductItem bread = new ProductItem(new Product("chleb 500g", 4.0, 5906489239182L),30);
        stock.add(flour);
        stock.add(eggs);
        stock.add(water);
        stock.add(milk);
        stock.add(butter);
        stock.add(bread);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i=0; i < stock.size(); i++) {
            sb.append(stock.get(i).getProduct());
            sb.append(" ilość: ");
            sb.append(stock.get(i).getAmount());
            sb.append("\n");
        }
        return sb.toString();
    }
}
