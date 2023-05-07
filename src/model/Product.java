package model;

import exceptions.NonNaturalNumberException;
/**
 * @author Silem Nabib Villa Contreras
 * @author Alejandro Torres Soto
 * */
public class Product implements Comparable<Product>{

    private String name;
    private String description;
    private double price;
    private int amount;
    private int sales;
    private Category category;

    /**
     *
     * @param name the name of the product
     * @param description the description of the product
     * @param price the price of the product
     * @param amount the amount of the product
     * @param category the index Category of the product
     * @throws NonNaturalNumberException if the amount or price is negative or if the category is out of range
     */
    public Product(String name, String description, double price, int amount, int category)
            throws NonNaturalNumberException{
        if (amount < 0) throw new NonNaturalNumberException("Cannot add negative stock");
        if (price <= 0) throw new NonNaturalNumberException("The price cannot be equal or minor than zero");
        if (category < 0 || category > 7) throw new NonNaturalNumberException("Invalid number of category");

        this.name = name;
        this.description = description;
        this.price = price;
        this.amount = amount;
        this.category = Category.values()[category];
        sales = 0;
    }

    /**
     * Returns the name of the product.
     * @return The name of the product.
     */
    public String getName() {
        return name;
    }
    /**
     * Sets the name of the product.
     * @param name The new name of the product.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the description of the product.
     * @return The description of the product.
     */
    public String getDescription() {
        return description;
    }
    /**
     * Sets the description of the product.
     * @param description The new description of the product.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returns the price of the product.
     * @return The price of the product.
     */
    public double getPrice() {
        return price;
    }
    /**
     * Sets the price of the product.
     * @param price The new price of the product.
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Returns the description of the product.
     * @return The description of the product.
     */
    public int getAmount() {
        return amount;
    }
    /**
     * Decreases to the product the specified amount to the current amount.
     * @param amount the amount to decrease to the current amount of the product.
     */
    public void decreaseAmount(int amount) {
        this.amount -= amount;
    }
    /**
     * Adds to the product the specified amount to the current amount.
     * @param amount the amount to add to the current amount of the product.
     */
    public void addAmount(int amount) {
        this.amount += amount;
    }

    /**
     * Returns the sales of the product.
     * @return The sales of the product.
     */
    public int getSales() {
        return sales;
    }
    /**
     * Sets the sales of the product.
     * @param sales The new sales of the product.
     */
    public void setSales(int sales) {
        this.sales = sales;
    }

    /**
     * Adds to the product the specified sales to the current sales.
     * @param sales the sales to add to the current sales of the product.
     */
    public void addSales(int sales) {
        this.sales += sales;
    }

    /**
     Returns the index of the Category enum based on the category of the product.
     @return the index of the Category.
     */
    public int getCategory (){
        return category.ordinal();
    }

    @Override
    public int compareTo(Product o) {
        return this.getName().compareTo(o.getName());
    }

    @Override
    public boolean equals(Object obj) {
        Product product = (Product) obj;
        if(this.getName().equals((product.getName()))
                && this.price == product.price && this.category == product.category){
            return true;
        } else {
            return false;
        }
    }
}
