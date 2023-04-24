package exceptions;

public class NonNaturalNumberException extends Exception{

    public NonNaturalNumberException(String reason){
        super(reason);
    }
    public NonNaturalNumberException(){
        super("Negative numbers and decimals are not valid.");
    }
}
