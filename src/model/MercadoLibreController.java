package model;

import exceptions.NonNaturalNumberException;

import java.time.LocalDate;
import java.util.ArrayList;

public class MercadoLibreController {
    private Inventory inventory;
    private OrderStorage orderStorage;

    private static ArrayList<Product> productsToOrder;

    public MercadoLibreController(){
        inventory = new Inventory();
        orderStorage = new OrderStorage();
    }

    public Product createProduct(String name, String description, double price, int amount, int category)
            throws NonNaturalNumberException {
        return new Product(name, description, price, amount, category);
    }

    public String showCategories(){
        Category category[] = Category.values();

        String categories = "1. " + category[0];
        for (int i = 1; i<category.length; i++){
            categories += "\n"+ (i+1) + ". " + category[i];
        }

        return  categories;
    }

    public void addProduct(Product newProduct)
            throws NonNaturalNumberException {
        inventory.saveProduct(newProduct);
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

    public String showInventory(){
        String inventoryToString = "";
        ArrayList<Product> tempProducts = inventory.getLastSearch();

        inventoryToString += tempProducts.get(0);
        for (int i = 1; i < tempProducts.size(); i++){
            inventoryToString += "\n" + (i+1) + ". " + tempProducts.get(i).getName() + " Amount: " + tempProducts.get(i).getAmount();
        }

        return inventoryToString;
    }

    public void addOrder(Order newOrder, ArrayList<Product> orderProducts, ArrayList<Integer> amount){
        orderStorage.makeOrder(newOrder, orderProducts, amount);
    }


}
