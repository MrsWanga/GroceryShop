package model;

public class Product {
    private String name;
    private double price;
    private long code;

    public Product(String name, double price, long code) {
        this.name = name;
        this.price = price;
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public long getCode() {
        return code;
    }

    @Override
    public String toString() {
        return name +
                ", cena: " + price+
                ", kod kreskowy: " + code;
    }
}
