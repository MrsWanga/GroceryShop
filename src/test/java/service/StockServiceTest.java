package service;


import model.Product;
import model.ProductItem;
import model.SalesDocument;
import model.Stock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class StockServiceTest {

    @Test
    void searchStock() {
        //Setup
        StockService stockService = new StockService();

        //Do Something
        Product searchedProduct = stockService.searchStock(5900820000257L);

        //Make assertion
        assertNotNull(searchedProduct);
        Assertions.assertEquals(5900820000257L, searchedProduct.getCode());
        Assertions.assertEquals("masło 200g", searchedProduct.getName());
    }

    @Test
    void upgradeStock() {
        //Setup
        StockService stockService = new StockService();
        Stock stock = stockService.getStock();
        Product searchedProduct = stockService.searchStock(5900820000257L);
        ArrayList <Product> products=new ArrayList<Product>();
        ProductItem item = stock.getStock()
                .stream()
                .filter(productItem -> productItem.getProduct().getCode() == searchedProduct.getCode())
                .findFirst()
                .orElseThrow();
        products.add(item.getProduct());

        //Do Something
        stockService.upgradeStock(products);

        //Make assertion
        Assertions.assertEquals(16,item.getAmount());
    }
}