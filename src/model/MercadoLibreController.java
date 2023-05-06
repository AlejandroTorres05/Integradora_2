package model;

import exceptions.NonNaturalNumberException;

import java.time.LocalDate;

public class MercadoLibreController {
    private Inventory inventory;
    private OrderStorage orders;

    public MercadoLibreController(){
        inventory = new Inventory();
        orders = new OrderStorage();
    }

    public Product createProduct(String name, String description, double price, int amount, int category)
            throws NonNaturalNumberException {
        return new Product(name, description, price, amount, category);
    }

    public void addProduct(Product newProduct)
            throws NonNaturalNumberException {
        inventory.saveProduct(newProduct);
    }

    public String showCategories(){
        Category category[] = Category.values();

        String categories = "1. " + category[0];
        for (int i = 1; i<category.length; i++){
            categories += "\n"+ (i+1) + ". " + category[i];
        }

        return  categories;
    }

    public LocalDate createLocalDate(int year, int month, int day){
        return LocalDate.of(year, month, day);
    }

    public Order createOrder(String purchaserName){
        return new Order(purchaserName);
    }

    public Order createOrder(String purchaserName, LocalDate purchaseDate){
        return new Order(purchaserName, purchaseDate);
    }


}
