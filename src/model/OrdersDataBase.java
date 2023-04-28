package model;

import java.util.ArrayList;

public class OrdersDataBase {
    ArrayList<Order> orders;

    public OrdersDataBase(){
        orders = new ArrayList<>();
    }

    public void addOrder(Order newOrder){
        orders.add(newOrder);
    }

    public void addProductToOrder(int index, Product product, int amount){
        orders.get(index).addProduct(product, amount);
    }
}
