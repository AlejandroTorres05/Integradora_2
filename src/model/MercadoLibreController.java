package model;

import exceptions.NonNaturalNumberException;
import exceptions.OutOfStockException;
import exceptions.ProductIsNotRegisteredException;
import exceptions.ThereIsNotProductsByTheFilterException;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
import java.util.ArrayList;
import java.util.Collections;

public class MercadoLibreController {
    private Inventory inventory;
    private OrderStorage orderStorage;

    private static ArrayList<Product> productsToOrder;
    private static ArrayList<Integer> productsAmount;

    public MercadoLibreController(){
        inventory = new Inventory();
        orderStorage = new OrderStorage();

        productsToOrder = new ArrayList<>();
        productsAmount = new ArrayList<>();
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

    public LocalDate createLocalDate(int year, int month, int day) {
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

    public void selectProductInInventory(int choice, int amount)
            throws IllegalArgumentException, OutOfStockException {

        if (choice >= inventory.getLastSearch().size()){
            throw new IllegalArgumentException("Index out of range.");
        }

        Product tempProduct = inventory.getLastSearch().get(choice);
        int extraAmount = searchProductAmountInProductsToOrder(choice);

        if (tempProduct.getAmount() == 0 || (amount + extraAmount) > tempProduct.getAmount()){
            throw new OutOfStockException();
        }

        if (extraAmount != 0){
            Product productPointer = inventory.getLastSearch().get(choice);
            int index = productsToOrder.indexOf(productPointer);
            productsAmount.add(index, productsAmount.get(index) + amount);
        } else {
            productsToOrder.add(tempProduct);
            productsAmount.add(amount);
        }
    }

    private int searchProductAmountInProductsToOrder(int choice){
        Product productPointer = inventory.getLastSearch().get(choice);

        if (productsToOrder.contains(productPointer)){
            return productsAmount.get(productsToOrder.indexOf(productPointer));
        }

        return 0;
    }

    public void addOrder(Order newOrder){
        if (!productsToOrder.isEmpty()){
            orderStorage.makeOrder(newOrder, productsToOrder, productsAmount);
            productsToOrder = new ArrayList<>();
            productsAmount = new ArrayList<>();
        }
    }

    /**
     *
     * */
    public int searchAProduct(String name, double price, int category, int sales)
            throws NonNaturalNumberException, ProductIsNotRegisteredException {
        return inventory.searchAnElement(name, price, category, sales);
    }

    /**
     *
     * */
    public void filterByRange (int option, double beginning, double end)
            throws ThereIsNotProductsByTheFilterException {
        inventory.filterByRange(option, beginning, end);
    }

    public String showAByRangeSearch (int filter, int order){
        ArrayList<Product> lasSearch = inventory.getLastSearch();
        String message = "";
        switch (order){
            case 1:
                switch (filter){
                    case 1:
                        Collections.sort(lasSearch);
                        break;
                    case 2:
                        Collections.sort(lasSearch, (a,b)->{
                            return (int)(a.getPrice() - b.getPrice());
                        });
                        break;
                    case 3:
                        Collections.sort(lasSearch, (a,b)->{
                            return a.getCategory() - b.getCategory();
                        });
                        break;
                    case 4:
                        Collections.sort(lasSearch, (a,b)->{
                            return a.getSales() - b.getSales();
                        });
                        break;
                }
                break;
            case 2:
                switch (filter){
                    case 1:
                        Collections.sort(lasSearch, (a,b)->{
                            return b.getName().compareTo(a.getName());
                        });
                        break;
                    case 2:
                        Collections.sort(lasSearch, (a,b)->{
                            return (int)(b.getPrice() - a.getPrice());
                        });
                        break;
                    case 3:
                        Collections.sort(lasSearch, (a,b)->{
                            return b.getCategory() - a.getCategory();
                        });
                        break;
                    case 4:
                        Collections.sort(lasSearch, (a,b)->{
                            return b.getSales() - a.getSales();
                        });
                        break;
                }
                break;
        }

        for (int i = 0; i<lasSearch.size(); i++){
            Product temporal = lasSearch.get(i);
            message += (i+1) + ". " + temporal.getName() + " ,price " + temporal.getPrice() + " ,amount " + temporal.getAmount() + "\n";
        }

        return message;
    }

    public void filterByInterval (String beginning, String end)
            throws ThereIsNotProductsByTheFilterException{
        inventory.filterByInterval(beginning, end);
    }

    public String showByIntervalSearch(int order){
        String message = "";
        ArrayList<Product> lastSearch = inventory.getLastSearch();

        switch (order){
            case 1:
                Collections.sort(lastSearch);
                break;
            case 2:
                Collections.sort(lastSearch, (a,b)->{
                    return b.getName().compareTo(a.getName());
                });
                break;
        }

        for (int i = 0; i<lastSearch.size(); i++){
            Product temporal = lastSearch.get(i);
            message += (i+1) + ". " + temporal.getName() + " ,price " + temporal.getPrice() + " ,amount " + temporal.getAmount() + "\n";
        }

        return message;
    }

    /**
     * This method can save the last
     * saved data in the system to
     * Inventory and OrderStorage
     * method
     * @Post the data will be saved
     *             out of the program
     * */
    public void save () throws IOException {
        inventory.save();
        orderStorage.save();
    }

    /**
     * This method loads the registered
     * data to the program to keep
     * the changes made in the last
     * execution.
     * @Post the last saved data will
     *             be load
     * */
    public void loadData () throws IOException {
        inventory.loadData();
        orderStorage.loadData();
    }

}
