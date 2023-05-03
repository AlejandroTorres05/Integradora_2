package model;

import exceptions.NonNaturalNumberException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class just validates the basic
 * behavior of an inventory. To see the
 * tests of the product search engine,
 * go to ProductSearchEngineTest class
 * @see ProductSearchEngineTest
 * */
public class InventoryTest {

    public static Inventory inventory;

    /**
     * This setup stage generates an empty
     * inventory object
     * */
    public void setupStage1 (){
        inventory = new Inventory();
    }

    /**
     * This stage makes an Inventory object that
     * contains a Product object. The product object
     * is a Harry Potter book and its amount is ten
     * */
    public void setupStage2 () throws NonNaturalNumberException{
        inventory = new Inventory();
        inventory.saveProduct(
                new Product("Harry Potter 1", "The first installment of the saga", 10, 10, 0)
        );
    }

    /**
     * This is a positive test that add a new Inventory
     * object to an inventory object. To validate the
     * test, this method saves a new element in the
     * inventory and asserts if the inventory is empty
     * and if the inventory really contains the saved
     * element
     * */
    @Test
    public void saveProductMethodCanConstructsAndSavesANewElementCorrectlyTest() throws NonNaturalNumberException{

        setupStage1();
        Product book = new Product("Harry Potter 1", "The first installment of the saga", 10, 0, 0);

        inventory.saveProduct(book);

        assertFalse(inventory.isEmpty());
        assertTrue(inventory.contains(book));
    }

    /**
     * This test validates if the saveProduct method,
     * from Inventory class, can throw the
     * NonNaturalNumberException exception
     * */
    @Test
    public void saveProductMethodThrowsNonNaturalNumberExceptionExceptionTest(){

        setupStage1();

        assertThrows(NonNaturalNumberException.class, ()->{
            inventory.saveProduct(
                    new Product("", "", 0, 0, 0)
            );
        });
    }

    /**
     * This test validates if the saveProduct method,
     * from inventory class, can throw NonNaturalNumberException exception
     * when a user type an incorrect value for the price. For
     * that is necessary to use two assertsThrows because the exception
     * can be thrown with the price zero or with a negative price
     * */
    @Test
    public void saveProductMethodThrowsNonNaturalNumberExceptionExceptionToThePriceTest (){

        setupStage1();

        assertThrows(NonNaturalNumberException.class, ()->{
            inventory.saveProduct(
                    new Product("", "", 0, 0, 0)
            );
        });

        assertThrows(NonNaturalNumberException.class, ()->{
            inventory.saveProduct(
                    new Product("", "", -1, 0, 0)
            );
        });

    }

    /**
     * This test validates if the saveProduct method,
     * from Inventory class, can throw
     * NonNaturalNumberException exception when the
     * user types a negative number for the amount
     * */
    @Test
    public void saveProductMethodThrowsNonNaturalNumberExceptionExceptionToANegativeAmountTest (){
        setupStage1();

        assertThrows(NonNaturalNumberException.class, ()->{
            inventory.saveProduct(
                    new Product("", "", 1, -1, 0)
            );
        });
    }

    /**
     * This method validates if the addToInventory method,
     * from Inventory class, makes
     * the correct operations and really add extra items to
     * the actual saved product
     * */
    @Test
    public void addToInventoryMethodCanAddMoreUnitsToASavedProductTest ()
            throws NonNaturalNumberException{

        setupStage2();
        Product product = new Product("Harry Potter 1","", 10, 0, 0);;

        inventory.addToInventory(product, 10);

        assertEquals(20, inventory.unitsOf("Harry Potter 1"));
    }

    /**
     * This test validates if the addToInventory method from
     * Inventory class can throw NonNaturalNumberException exception
     * when a user tries to add a negative number of elements to a
     * saved product
     * */
    @Test
    public void addToInventoryMethodCanThrowNonNaturalNumberExceptionExceptionTest ()
            throws NonNaturalNumberException{
        setupStage1();
        Product book = new Product("Harry Potter 1", "The first installment of the saga", 10, 0, 0);

        assertThrows(NonNaturalNumberException.class, ()->{
           inventory.addToInventory(book, -10);
        });
    }
}
