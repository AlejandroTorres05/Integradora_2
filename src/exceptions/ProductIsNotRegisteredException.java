package exceptions;

public class ProductIsNotRegisteredException extends RuntimeException{

    public ProductIsNotRegisteredException(){
        super("There are not search matches");
    }

    public ProductIsNotRegisteredException(String reason){
        super(reason);
    }
}
