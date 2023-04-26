package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Order {
    private String purchaserName;
    private double totalPrice;
    private LocalDate purchaseDate;

    private ArrayList<Product> order;

    public Order(String purchaserName){
        this(purchaserName, LocalDate.now());
    }

    public Order(String purchaserName, LocalDate purchaseDate){
        this.purchaserName = purchaserName;
        this.purchaseDate = purchaseDate;
        totalPrice = 0;

        order = new ArrayList<>();
    }

    public String getPurchaserName() {
        return purchaserName;
    }
    public void setPurchaserName(String purchaserName) {
        this.purchaserName = purchaserName;
    }

    public double getTotalPrice() {
        return totalPrice;
    }
    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }
    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public void addProduct(Product product, int amount){
        int index = searchProductInOrder(product);

        if (index != -1){
            order.get(index).editAmount(amount);
        } else{
            product.setPurchases_count(0);
            product.setAmount(amount);
            order.add(product);
        }

    }

    private int searchProductInOrder(Product product){
        for (int i = 0; i<order.size(); i++){
            String productToCompare = order.get(i).getName();
            if (product.getName().equalsIgnoreCase(productToCompare)) return i;
        }

        return -1;
    }

}
