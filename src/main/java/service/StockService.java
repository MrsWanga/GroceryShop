package service;

import jdk.jfr.Category;
import model.Product;
import model.ProductItem;
import model.Stock;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class StockService {

    public Stock getStock() {
        return stock;
    }

    private Stock stock = new Stock();
    private static final StockService INSTANCE = new StockService();

    public static StockService getInstance(){return INSTANCE;};

    public Product searchStock(long code) throws NoSuchElementException {
        ProductItem product = stock.getStock()
                .stream()
                .filter(productItem -> productItem.getProduct().getCode() == code)
                .findFirst()
                .orElseThrow();
        return product.getProduct();
    }

    public boolean isEmptyStock(long code) throws NoSuchElementException {
        ProductItem product = stock.getStock()
                .stream()
                .filter(productItem -> productItem.getProduct().getCode() == code)
                .findFirst()
                .orElseThrow();
        return product.getAmount()==0;
    }

    public void downgradeStock(ArrayList<Product> products){
        products.forEach(product -> {
            ProductItem item = stock.getStock()
                    .stream()
                    .filter(productItem -> productItem.getProduct().getCode() == product.getCode())
                    .findFirst()
                    .orElseThrow();
            item.setAmount(item.getAmount() - 1);
        });
    }


}
