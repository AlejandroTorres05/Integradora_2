package exceptions;

public class ThereIsNotProductsByTheFilterException extends RuntimeException{

    public ThereIsNotProductsByTheFilterException (){
        super("There are not search matches");
    }

    public ThereIsNotProductsByTheFilterException (String reason){
        super(reason);
    }
}
