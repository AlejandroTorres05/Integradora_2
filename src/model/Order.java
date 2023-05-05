package model;

import java.time.LocalDate;
import java.util.ArrayList;
/**
 * @author Silem Nabib Villa Contreras
 * @author Alejandro Torres Soto
 * */
public class Order {

    private String customerName;
    private double totalPrice;
    private LocalDate orderDate;

    private ArrayList<Product> order;

    /**
     Creates an order with the specified customer name and current date.
     @param customerName the name of the customer who placed the order
     */
    public Order(String customerName){
        this(customerName, LocalDate.now());
    }

    /**
     Creates an order with the specified customer name and current date.
     @param customerName the name of the customer who placed the order
     @param orderDate the date on which the order was placed
     */
    public Order(String customerName, LocalDate orderDate){
        this.customerName = customerName;
        this.orderDate = orderDate;
        totalPrice = 0;

        order = new ArrayList<>();
    }

    /**
     * Returns the customer name of the order.
     * @return The customer name of the order.
     */
    public String getCustomerName() {
        return customerName;
    }
    /**
     * Sets the customer name of the order.
     * @param customerName The new customer name of the order.
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     * Returns the total price of the order.
     * @return The total price of the order.
     */
    public double getTotalPrice() {
        return totalPrice;
    }
    /**
     * Sets the total price of the order.
     * @param totalPrice The new total price of the order.
     */
    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    /**
     * Returns the date on which the order was placed
     * @return the date on which the order was placed
     */
    public LocalDate getOrderDate() {
        return orderDate;
    }
    /**
     * Sets the order date of the order.
     * @param orderDate The new order date of the order.
     */
    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    /**
     * Adds the product to the order. If the product already exists,
     * it edits the existing quantity in the inventory.
     * @param product The product to be added.
     * @param amount The amount of the product to be added.
     */
    public void addProduct(Product product, int amount){
        int index = searchProductInOrder(product);

        if (index != -1){
            order.get(index).editAmount(amount);
        } else{
            product.setSales(0);
            product.setAmount(amount);
            order.add(product);
        }
    }

    /**
     * Searches for the index of the product in the Order. If it is not found, it returns -1.
     * @param product the product to be searched.
     * @return The index of the product, or -1 if it's not found.
     */
    private int searchProductInOrder(Product product){
        for (int i = 0; i<order.size(); i++){
            String productToCompare = order.get(i).getName();
            if (product.getName().equalsIgnoreCase(productToCompare)) return i;
        }

        return -1;
    }

}
