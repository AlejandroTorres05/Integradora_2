package exceptions;

public class OutOfStockException extends Exception{
    public OutOfStockException(){
        super("We're sorry, the product you requested is currently out of stock.");
    }
}
