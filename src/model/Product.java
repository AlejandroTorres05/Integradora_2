package model;

public class Product {

    private String name;
    private String description;
    private double price;
    private int amount;
    private int purchases_count;

    private Category category;

    public Product(String name, String description, double price, int amount, int category) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.amount = amount;
        this.category = Category.values()[category];
        purchases_count = 0;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }
    public void editAmount (int amount) {
        this.amount += amount;
    }

    public int getPurchases_count() {
        return purchases_count;
    }
    public void setPurchases_count(int purchases_count) {
        this.purchases_count = purchases_count;
    }
}
