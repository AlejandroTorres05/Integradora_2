package model;

import com.google.gson.Gson;
import exceptions.NonNaturalNumberException;
import exceptions.ProductIsNotRegisteredException;
import exceptions.ThereIsNotProductsByTheFilterException;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
  * @author Silem Nabib Villa Contreras
  * @author Alejandro Torres Soto
  * */
public class Inventory{

    static String path = "inventoryData/data.txt";
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

    /**
     * This method must find the position
     * of the object that contains the entered
     * characteristics. If the product isn't
     * registered yet, this method will throw
     * the ProductIsNotRegisteredException
     * exception.
     * @param name a String is the name of the product
     *             that the user is searching for
     *             his index
     * @param price a double, is the name of the product
     *             that the user is searching for
     *             his index
     * @param category an int is the reference of the
     *                 category of the product that
     *                 the user is searching for
     * @param sales an int that represents the sales
     *              of the product that the user is
     *              searching for
     * @Post: this method will return the required index
     *              or will throw an exception
     * */
    public int searchAnElement (String name, double price, int category, int sales)
            throws ProductIsNotRegisteredException, NonNaturalNumberException {

        Product temporal = new Product(name, "", price, 0, category);
        temporal.setSales(sales);
        int index = indexOf(temporal);

        if(index == -1)
            throw new ProductIsNotRegisteredException();
        lastSearch = new ArrayList<>();
        lastSearch.add(this.products.get(index));

        return this.products.get(index).getAmount();
    }

    /**
     * This method is the main filter method
     * when the user is searching products
     * using a numeric range. If there aren't
     * products according to the filter, this
     * method throws ThereIsNotProductsByTheFilterException
     * exception
     * @param option an int that represents
     *               the selected option
     *               to make the filter
     * @param beginning a double is the start
     *                  or minor value of the
     *                  products that the user is
     *                  searching for
     * @param end a double is the end or mayor value
     *            of the products that the user
     *            is searching for
     * @return an ArrayList with the founded products
     * @Post this method must return an arraylist or
     *              throw ThereIsNotProductsByTheFilterException
     *              exception
     * */
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

    /**
     * This method filter products by
     * their prices using a numeric range
     * @param beginning a double is the start
     *                  or minor value of the
     *                  products that the user is
     *                  searching for
     * @param end a double is the end or mayor value
     *            of the products that the user
     *            is searching for
     * @return an ArrayList with the founded products
     * @Post this method must return an arraylist
     *              with the founded products
     * */
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

    /**
     * This method filter products by
     * their sales number using a numeric range
     * @param beginning an int is the start
     *                  or minor value of the
     *                  products that the user is
     *                  searching for
     * @param end an int is the end or mayor value
     *            of the products that the user
     *            is searching for
     * @return an ArrayList with the founded products
     * @Post this method must return an arraylist
     *              with the founded products
     * */
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

    /**
     * This method filter products by
     * their amount quantity using a numeric range
     * @param beginning an int is the start
     *                  or minor value of the
     *                  products that the user is
     *                  searching for
     * @param end an int is the end or mayor value
     *            of the products that the user
     *            is searching for
     * @return an ArrayList with the founded products
     * @Post this method must return an arraylist
     *              with the founded products
     * */
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

     /**
      * This method can filter the products
      * that contains the entered Strings. If
      * there aren't products according to
      * the filter, this method will throw
      * ThereIsNotProductsByTheFilterException
      * exception
      * @param beginning a String, is the
      *                  start that a product
      *                  must have in his name
      * @param end a String, is the end that
      *            a product must have in his
      *            name
      * @return an ArrayList with the founded
      *             products
      * @Post this method must return an arraylist
      *              with the founded products  or
      *              throw the exception
      * */
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

     /**
      * This method can save the last
      * saved data in the system
      * @Post the data will be saved
      *             out of the program
      * */
     public void save () throws IOException{
         File file = new File(path);
         FileOutputStream fos = new FileOutputStream(file);
         Gson gson = new Gson();
         String data = gson.toJson(this.products);
         BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos));
         writer.write(data);
         writer.flush();
         fos.close();
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
         File file = new File(path);

         if (file.exists()){
             FileInputStream fis = new FileInputStream(file);
             BufferedReader reader = new BufferedReader(new InputStreamReader(fis));

             String content = "";
             String line;

             while ((line = reader.readLine()) != null){
                 content += line + "\n";
             }
             Gson gson = new Gson();
             Product[] array = gson.fromJson(content, Product[].class);
             this.products.addAll(Arrays.asList(array));
             fis.close();

         }else {
             File f = new File("inventoryData");
             if (!f.exists()){
                 f.mkdirs();
             }
             file.createNewFile();
         }
     }
 }
