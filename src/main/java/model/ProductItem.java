package model;

public class ProductItem {
    private Product product;
    private int amount;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public ProductItem(Product product, int i) {
        this.setProduct(product);
        this.setAmount(i);
    }
}
