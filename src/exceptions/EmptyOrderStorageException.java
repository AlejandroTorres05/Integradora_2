package exceptions;

public class EmptyOrderStorageException extends Exception{

    public EmptyOrderStorageException (){
        super("No orders at the moment");
    }

    public EmptyOrderStorageException (String reason){
        super(reason);
    }
}
