package model;

import exceptions.NonNaturalNumberException;

public class Product {

    private String name;
    private String description;
    private double price;
    private int amount;
    private int purchases_count;
    private Category category;

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
    public void setAmount(int amount) {
        this.amount = amount;
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
    public int getCategory (){

        if (this.category == Category.BOOKS){
            return 0;
        }else if (this.category == Category.ELECTRONICS){
            return 1;
        }else if (this.category == Category.CLOTHING_AND_ACCESSORIES){
            return 2;
        }else if (this.category == Category.FOOD_AND_DRINKS){
            return 3;
        }else if (this.category == Category.STATIONERY){
            return 4;
        }else if (this.category == Category.SPORTS){
            return 5;
        }else if (this.category == Category.BEAUTY_AND_PERSONAL_CARE){
            return 7;
        }else {
            return 8;
        }
    }
}
