package model;

import java.util.ArrayList;

public class Orders {
    ArrayList<Order> orders;

    public Orders(){
        orders = new ArrayList<>();
    }

    public void addOrder(Order newOrder){
        orders.add(newOrder);
    }

    public void addProductToOrder(int index, Product product, int amount){
        orders.get(index).addProduct(product, amount);
    }
}
