package exceptions;

public class InvalidCategoryException extends RuntimeException{
    public InvalidCategoryException (){
        super(
                "The entered number of category doesn't exits"
        );
    }

    public InvalidCategoryException (String reason){
        super(reason);
    }
}
