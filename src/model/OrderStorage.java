package model;

import java.util.ArrayList;
/**
 * @author Silem Nabib Villa Contreras
 * @author Alejandro Torres Soto
 * */
public class OrderStorage {
    ArrayList<Order> orders;

    /**
     * Creates a ordersDataBase.
     */
    public OrderStorage(){
        orders = new ArrayList<>();
    }

    /**
     * adds a new order to orders.
     * @param newOrder The order to be added.
     */
    public void addOrder(Order newOrder){
        orders.add(newOrder);
    }

    /**
     * add a product to the last order in orders.
     * @param product the product to be added.
     * @param amount the amount of the product to be added.
     */
    public void addProductToOrder(Product product, int amount){
        int index = orders.size()-1;
        orders.get(index).addProduct(product, amount);
    }
}
