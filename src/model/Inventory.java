package model;

import exceptions.NonNaturalNumberException;
import exceptions.ProductIsNotRegisteredException;
import exceptions.ThereIsNotProductsByTheFilterException;

import java.util.ArrayList;
import java.util.Collections;

/**
  * @author Silem Nabib Villa Contreras
  * @author Alejandro Torres Soto
  * */
public class Inventory{


    private static ArrayList<Product> lastSearch = new ArrayList<>();
    private ArrayList<Product> products;

    public Inventory (){
        this.products = new ArrayList<>();
    }

    /**
     * This method can save a new product or add its new
     * amount. For that, the method verify if the product
     * is registered in system. When the product is saved
     * already in the system, it must add the entered
     * amount to the product. If the product isn't registered
     * yet, the system must save the product in the
     * corresponding arraylist
     * @param product a Product object that can be saved or
     *                add more amount to a registered product
     * @Post If the product exists in the system, this
     *                 product will have more amount. Else,
     *                 the system will save the new product in
     *                 the system
     * */
    public void saveProduct(Product product) throws NonNaturalNumberException{
        if (!contains(product)){
            saveNewProduct(product);
        }else {
            addToInventory(product, product.getAmount());
        }
    }

    /**
     * Returns the last search performed.
     * @return The last search performed.
     */
    public ArrayList<Product> getLastSearch() {
        return lastSearch;
    }

    /**
     * This method is called when the method
     * saveProduct gets a product that isn't
     * registered in the system, and it's necessary
     * to save the new element. For save the new element,
     * the system in the most of the cases must
     * use a binary search algorithm tho find the
     * right position to save the new element, following
     * the ascending order by the name.
     * @param newProduct a Product, is the new product to
     *              be saved at the inventory
     * @Pre there must be an Inventory object instanced
     *              at the system
     * @Post the new product will be saved
     * */
    private void saveNewProduct(Product newProduct){
        this.products.add(newProduct);
        Collections.sort(this.products);
    }

    /**
     * This method increases the amount of a
     * saved product. For that searches the
     * position of the product in the array
     * and calls edit amount method from
     * Product class
     * @param amount an Integer which represents
     *               the amount that will be added
     *               to a saved product
     * @Pre The product must be saved in the system already
     * @Post The referenced product will have more amount
     * */
    public void addToInventory (Product goal, int amount)
            throws NonNaturalNumberException {
        if (amount <= 0){
            throw new NonNaturalNumberException("Cannot add negative or null amount to a product");
        }
        int index = indexOf(goal);
        this.products.get(index).addAmount(amount);
    }


    /**
     * This method searches the position of
     * a product saved in the products ArrayList
     * @param goal a Product, is the product
     *             that the system is searching for
     *             its position
     * @return an Integer that is the index or the
     *             value of the position tha the
     *             system was searching for
     * @Pre There must be an arraylist instanced
     *          in the system
     * @Post If the goal exits in the Arraylist, the
     *              method must return its index. Else,
     *              the method will return -1
     * */
    private int indexOf (Product goal){
        int i = 0;
        int j = this.products.size() - 1;

        while (i <= j){
            int mid = (i+j)/2;
            Product midVal = this.products.get(mid);

            if (midVal.equals(goal)){
                return mid;
            }else if (goal.compareTo(midVal) > 0){
                i = mid+1;
            }else {
                j = mid-1;
            }
        }
        return -1;
    }

    /**
     * This method verifies if there are
     * any elements saved in the Inventory
     * and returns a boolean that indicates
     * if the inventory is empty or not
     * @return a boolean that represent if
     *          the Inventory has elements
     *          or not
     * @Pre There must be an inventory instanced
     * @Post This method will return a boolean
     * */
    public boolean isEmpty (){
        return this.products.isEmpty();
    }

    /**
     * This method verifies if the Inventory
     * has or not a product saved at itself.
     * For that, checks the arraylists searching
     * for the goal
     * @param goal a Product that represents the
     *             element tha the method is
     *             searching for
     * @return a boolean that indicates if the
     *             is saved in the system or not
     * @Pre There must be an inventory instanced in the system
     * @Post This method will return a boolean
     * */
    public boolean contains (Product goal) {
        return this.products.contains(goal);
    }

    /**
     * This method searches for the units of
     * a product. For that, this method must
     * search the product and call the
     * getAmount method from Product class,
     * that returns the amount of a founded
     * product
     * @param name a String, is the name or
     *             ID of the product that the method
     *             is searching for
     * @return an Integer that represents the amount of
     *                  a searched product
     * @Pre There must be an Inventory object with the
     *                searched product saved.
     * @Post The method will return the amount of
     *                  the searched product
     * */
    public int unitsOf (String name){
        int i = 0;
        int j = this.products.size() - 1;

        while (i <= j){
            int mid = (i+j)/2;
            String midVal = this.products.get(mid).getName();

            if (midVal.equals(name)){
                return this.products.get(mid).getAmount();
            }else if (name.compareTo(midVal) > 0){
                i = mid+1;
            }else {
                j = mid-1;
            }
        }
        return -1;
    }

    public int searchAnElement (String name, double price, int category, int sales)
            throws ProductIsNotRegisteredException, NonNaturalNumberException {

        Product temporal = new Product(name, "", price, 0, category);
        int index = indexOf(temporal);

        if(index == -1)
            throw new ProductIsNotRegisteredException();

        return this.products.get(index).getAmount();
    }

    public ArrayList<Product> filterByRange (int option, double beginning, double end)
            throws ThereIsNotProductsByTheFilterException {

        ArrayList<Product> filtered = new ArrayList<>();

        switch (option){
            case 1:
                filtered = filterByPrice(beginning, end);
                break;
            case 2:
                filtered = filterBySales((int) beginning, (int) end);
                break;
            case 3:
                filtered = filterByAmount((int) beginning, (int) end);
                break;
        }
        lastSearch = filtered;

        if (filtered.isEmpty())
            throw new ThereIsNotProductsByTheFilterException();
        return filtered;
    }

    private ArrayList<Product> filterByPrice (double beginning, double end){

        ArrayList<Product> filtered = new ArrayList<>();

        for (int i = 0; i<this.products.size(); i++){
            if (this.products.get(i).getPrice() >= beginning
                    && this.products.get(i).getPrice() <= end){
                filtered.add(this.products.get(i));
            }
        }

        return filtered;
    }

    private ArrayList<Product> filterBySales (int beginning, int end){

        ArrayList<Product> filtered = new ArrayList<>();

        for (int i = 0; i<this.products.size(); i++){
            if(this.products.get(i).getSales() >= beginning
                    && this.products.get(i).getSales() <= end){
                filtered.add(this.products.get(i));
            }
        }

        return filtered;
    }

     private ArrayList<Product> filterByAmount (int beginning, int end){

         ArrayList<Product> filtered = new ArrayList<>();

         for (int i = 0; i<this.products.size(); i++){
             if(this.products.get(i).getAmount() >= beginning
                     && this.products.get(i).getAmount() <= end){
                 filtered.add(this.products.get(i));
             }
         }

         return filtered;
     }

     public ArrayList<Product> filterByInterval (String beginning, String end)
             throws ThereIsNotProductsByTheFilterException{

        ArrayList<Product> filtered = new ArrayList<>();

        for (int i = 0; i<this.products.size(); i++){
            if ( this.products.get(i).getName().startsWith(beginning) && this.products.get(i).getName().endsWith(end)){
                filtered.add(this.products.get(i));
            }
        }

         if (filtered.isEmpty())
             throw new ThereIsNotProductsByTheFilterException();
         return filtered;
     }
 }
