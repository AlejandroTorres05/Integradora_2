package exceptions;

public class EmptyInventoryException extends Exception{
    public EmptyInventoryException (){
        super("No products at the moment");
    }

    public EmptyInventoryException (String reason){
        super(reason);
    }
}
