package model;

import java.util.ArrayList;
import java.util.Date;

/**
 * @author Silem Nabib Villa Contreras
 * @author Alejandro Torres Soto
 * */
public class Order {

    private String customerName;
    private double totalPrice;
    private Date orderDate;

    private ArrayList<ProductOrder> orderProducts;

    /**
     Creates an order with the specified customer name and current date.
     @param customerName the name of the customer who placed the order
     */
    public Order(String customerName){
        this(customerName, new Date());
    }

    /**
     Creates an order with the specified customer name and current date.
     @param customerName the name of the customer who placed the order
     @param orderDate the date on which the order was placed
     */
    public Order(String customerName, Date orderDate){
        this.customerName = customerName;
        this.orderDate = orderDate;
        totalPrice = 0;

        orderProducts = new ArrayList<>();
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
     * Returns the date on which the order was placed.
     * @return the date on which the order was placed.
     */
    public Date getOrderDate() {
        return orderDate;
    }
    /**
     * Sets the order date of the order.
     * @param orderDate The new order date of the order.
     */
    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    /**
     * Returns the list of products in the order.
     * @return the list of products in the order.
     */
    public ProductOrder getOrderProducts(int index) {
        return orderProducts.get(index);
    }

    /**
     * Adds the product order to the order
     * @param product The product order to be added.
     */
    public void addProduct(ProductOrder product){
        totalPrice += product.getTotalPrice();
        orderProducts.add(product);
    }


}
