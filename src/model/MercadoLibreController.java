package model;

import java.time.LocalDate;

public class MercadoLibreController {
    private Inventory inventory;
    private OrdersDataBase orders;

    public MercadoLibreController(){
        inventory = new Inventory();
        orders = new OrdersDataBase();
    }

    public LocalDate createLocalDate(int year, int month, int day){
        return LocalDate.of(year, month, day);
    }

    public Order createOrder(String purchaserName){
        return new Order(purchaserName);
    }

    public Order createOrder(String purchaserName, LocalDate purchaseDate){
        return new Order(purchaserName, purchaseDate);
    }


}
