package model;

public class MercadoLibreController {
    private Inventory inventory;
    private Orders orders;

    public MercadoLibreController(){
        inventory = new Inventory();
        orders = new Orders();
    }

}
