package model;

public class ProductOrder {
    private String name;
    private double price;
    private int amount;

    private Product originalProduct;

    public ProductOrder(Product originalProduct, int amount) {
        this.originalProduct = originalProduct;

        this.name = originalProduct.getName();
        this.price = originalProduct.getPrice();
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getAmount() {
        return amount;
    }

    public Product getOriginalProduct() {
        return originalProduct;
    }

    public double getTotalPrice() {
        return amount * price;
    }
}

