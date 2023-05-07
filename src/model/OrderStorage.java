package model;

import java.util.ArrayList;
/**
 * @author Silem Nabib Villa Contreras
 * @author Alejandro Torres Soto
 * */
public class OrderStorage {
    ArrayList<Order> orderStorage;

    /**
     * Creates a ordersDataBase.
     */
    public OrderStorage(){
        orderStorage = new ArrayList<>();
    }

    public void makeOrder(Order newOrder, ArrayList<Product> orderProducts, ArrayList<Integer> amount){
        ArrayList<ProductOrder> productOrders = addProductsToOrder(newOrder, orderProducts, amount);

        for (ProductOrder x: productOrders){
            newOrder.addProduct(x);
        }

        orderStorage.add(newOrder);
    }

    private ArrayList<ProductOrder> addProductsToOrder(Order order, ArrayList<Product> orderProducts, ArrayList<Integer> amount){
        ArrayList<ProductOrder> productOrders = new ArrayList<>();

        for (int i = 0; i < orderProducts.size(); i++){
            Product tempProduct = orderProducts.get(i);
            int tempAmount = amount.get(i);

            orderProducts.get(i).decreaseAmount(tempAmount);

            productOrders.add(new ProductOrder(tempProduct, tempAmount));
        }

        return productOrders;
    }

    public Order getOrderStorage(int index) {
        return orderStorage.get(index);
    }

    private ArrayList<Order> filterByTotalPrice (double beginning, double end){

        ArrayList<Order> filtered = new ArrayList<>();

        for (int i = 0; i< orderStorage.size(); i++){
            if (orderStorage.get(i).getTotalPrice() >= beginning && orderStorage.get(i).getTotalPrice() <= end){
                filtered.add(orderStorage.get(i));
            }
        }

        return filtered;
    }
}
