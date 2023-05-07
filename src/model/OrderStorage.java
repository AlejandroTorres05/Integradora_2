package model;

import exceptions.ThereIsNotProductsByTheFilterException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

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
        sortByTotalPrice();
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

    private ArrayList<Order> filterByOrderDate(LocalDate beginningYear, LocalDate endYear){
        ArrayList<Order> tempArr = orderStorage;
        Collections.sort(tempArr);

        ArrayList<Order> filtered = new ArrayList<>();

        for (int i = 0; i< orderStorage.size(); i++){
            if (beginningYear.isBefore(tempArr.get(i).getOrderDate()) &&
                    endYear.isAfter(tempArr.get(i).getOrderDate())){

                filtered.add(orderStorage.get(i));
            }
        }

        return filtered;
    }

    private void sortByTotalPrice(){
        Collections.sort(orderStorage, Comparator.comparingDouble(Order::getTotalPrice));
    }

    public ArrayList<Order> filterOrderByInterval (String beginning, String end)
            throws ThereIsNotProductsByTheFilterException {

        ArrayList<Order> filtered = new ArrayList<>();

        for (Order order : orderStorage) {
            if (order.getCustomerName().startsWith(beginning) &&
                    order.getCustomerName().endsWith(end)) {
                filtered.add(order);
            }
        }

        if (filtered.isEmpty()) throw new ThereIsNotProductsByTheFilterException();

        return filtered;
    }
}
