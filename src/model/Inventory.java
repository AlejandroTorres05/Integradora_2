package model;

import exceptions.InvalidCategoryException;
import exceptions.NonNaturalNumberException;

import java.util.ArrayList;
 /**
  * @author Silem Nabid Villa Contreras
  * @author Alejandro Torres Soto
  * */
public class Inventory {


    private ArrayList<Product> books;
    private ArrayList<Product> electronics;
    private ArrayList<Product> clothing;
    private ArrayList<Product> food;
    private ArrayList<Product> stationery;
    private ArrayList<Product> sports;
    private ArrayList<Product> personalCare;
    private ArrayList<Product> games;

    public Inventory (){
        this.books = new ArrayList<>();
        this.clothing = new ArrayList<>();
        this.electronics = new ArrayList<>();
        this.food = new ArrayList<>();
        this.games = new ArrayList<>();
        this.stationery = new ArrayList<>();
        this.sports = new ArrayList<>();
        this.personalCare = new ArrayList<>();
    }

    /**
     * This method can save a new product or its new
     * amount. For that, the method verify if the product
     * is registered in system. When the product is saved
     * already in the system, the system must add the entered
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
    public void saveProduct(Product product){
        int cat = product.getCategory();

        if (!contains(product)){
            switch (cat) {
                case 0 -> books.add(product);
                case 1 -> electronics.add(product);
                case 2 -> clothing.add(product);
                case 3 -> food.add(product);
                case 4 -> stationery.add(product);
                case 5 -> sports.add(product);
                case 6 -> personalCare.add(product);
                case 7 -> games.add(product);
            }
        }
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
     * @param category an Integer that refers to
     *                 category of the product, using the
     *                 value that represents in the Category
     *                 enumeration
     * @param name a String that represents the name
     *             or ID of the product that will have
     *             more amount
     * @Pre The product must be saved in the system already
     * @Post The referenced product will have more amount
     * */
    public void addToInventory (String name, int amount, int category)
            throws NonNaturalNumberException{

        int index;

        switch (category) {
            case 0 -> {
                index = indexOf(this.books, name);
                this.books.get(index).editAmount(amount);
            }
            case 1 -> {
                index = indexOf(this.electronics, name);
                this.electronics.get(index).editAmount(amount);
            }
            case 2 -> {
                index = indexOf(this.clothing, name);
                this.clothing.get(index).editAmount(amount);
            }
            case 3 -> {
                index = indexOf(this.food, name);
                this.food.get(index).editAmount(amount);
            }
            case 4 -> {
                index = indexOf(this.stationery, name);
                this.stationery.get(index).editAmount(amount);
            }
            case 5 -> {
                index = indexOf(this.sports, name);
                this.sports.get(index).editAmount(amount);
            }
            case 6 -> {
                index = indexOf(this.personalCare, name);
                this.personalCare.get(index).editAmount(amount);
            }
            case 7 -> {
                index = indexOf(this.games, name);
                this.games.get(index).editAmount(amount);
            }
            default -> {
                throw new NonNaturalNumberException("Cannot add negative stock");
            }
        }
    }

    /**
     * This method searches the position of
     * a product saved in an Arraylist
     * @param arrayList an Arraylist, is the
     *                  array that contains the
     *                  goal that system is
     *                  searching for
     * @param goal a String that is the name of
     *             the product tha the system is
     *             searching for
     * @return an Integer that is the index or the
     *             value of the position tha the
     *             system was searching for
     * @Pre There must be an arraylist instanced
     *          in the system
     * @Post If the goal exits in the Arraylist, the
     *              method must return its index. Else,
     *              the method will return -1
     * */
    private int indexOf (ArrayList<Product> arrayList, String goal){

        for (int i = 0; i < arrayList.size(); i++){
            if (goal.equals(arrayList.get(i).getName())){
                return i;
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
        if (this.books.isEmpty() && this.electronics.isEmpty() && this.clothing.isEmpty() && this.food.isEmpty()
                            && this.stationery.isEmpty() && this.sports.isEmpty() && this.personalCare.isEmpty() && this.games.isEmpty()){
            return true;
        } else {
            return false;
        }
    }

    /**
     * This method verifies if an Inventory
     * has or not a product saved at itself.
     * For that, checks all arraylists searching
     * for the goal
     * @param goal a Product that represents the
     *             element tha the method is
     *             searching for
     * @return a boolean that indicates if the
     *             is saved in the system or not
     * @Pre There must be an inventory instanced in the system
     * @Post This method will return a boolean
     * */
    public boolean contains (Product goal) throws InvalidCategoryException{
        return switch (goal.getCategory()) {
            case 0 -> this.books.contains(goal);
            case 1 -> this.electronics.contains(goal);
            case 2 -> this.clothing.contains(goal);
            case 3 -> this.food.contains(goal);
            case 4 -> this.stationery.contains(goal);
            case 5 -> this.sports.contains(goal);
            case 6 -> this.personalCare.contains(goal);
            case 7 -> this.games.contains(goal);
            default -> throw new InvalidCategoryException();
        };
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
     * @param category an Integer, is the numeric
     *                 representation of the category
     *                 of the product
     * @return an Integer that represents the amount of
     *                  a searched product
     * @Pre There must be an Inventory object with the
     *                searched product saved.
     * @Post The method will return the amount of
     *                  the searched product
     * */
    public int unitsOf (String name, int category){
        int index = -1;
        if (category == 0){
            index = indexOf(this.books, name);
            return this.books.get(index).getAmount();
        }else if (category == 1){
            index = indexOf(this.electronics, name);
            return this.electronics.get(index).getAmount();
        }else if (category == 2){
            index = indexOf(this.clothing, name);
            return this.clothing.get(index).getAmount();
        }else if (category == 3){
            index = indexOf(this.food, name);
            return this.food.get(index).getAmount();
        }else if (category == 4){
            index = indexOf(this.stationery, name);
            return this.stationery.get(index).getAmount();
        }else if (category == 5){
            index = indexOf(this.sports, name);
            return this.sports.get(index).getAmount();
        }else if (category == 6){
            index = indexOf(this.personalCare, name);
            return this.personalCare.get(index).getAmount();
        }else {
            index = indexOf(this.games, name);
            return this.games.get(index).getAmount();
        }
    }
}
