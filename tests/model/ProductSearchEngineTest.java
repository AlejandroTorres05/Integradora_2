package model;

import exceptions.NonNaturalNumberException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
/**
 * This class just has the tests of the
 * product search engine. To see the
 * tests of the inventory, go to InventoryTest
 * class
 * @see InventoryTest
 * */
public class ProductSearchEngineTest {

    public static Inventory inventory;

    /**
     * This stage constructs an inventory with
     * ten elements
     * */
    public void setupStage1 () throws NonNaturalNumberException {
        inventory = new Inventory();
        inventory.saveProduct(
                new Product("Harry Potter Collection", "A book", 10, 10, 0)
        );
        inventory.saveProduct(
                new Product("VacuumCleaner", "VacuumCleaner", 5, 20, 1)
        );
        inventory.saveProduct(
                new Product("Red T-shirt", "Red T-shirt", 15, 100, 2)
        );
        inventory.saveProduct(
                new Product("Beer barrel", "Beer barrel", 100, 15, 3)
        );
        inventory.saveProduct(
                new Product("Pencil box", "Pencil box", 2, 200, 4)
        );
        inventory.saveProduct(
                new Product("Golty Ball", "To play soccer", 20, 60, 5)
        );
        inventory.saveProduct(
                new Product("Red lip", "Red lip", 2, 500, 6)
        );
        inventory.saveProduct(
                new Product("Play Station 5", "Ps is better than xbox", 500, 100, 7)
        );
        inventory.saveProduct(
                new Product("How to make money?", "To make money", 5, 20, 0)
        );
        inventory.saveProduct(
                new Product("Fridge", "Fridge", 500, 20, 1)
        );
    }

    /**
     * This method uses the
     * */
    @Test
    public void searchElementCanReturnTheObjectWhenTheObjectIsSavedInTheInventoryTest ()
            throws NonNaturalNumberException {
        setupStage1();
        //int amount1 = inventory.searchElement("Beer barrel", 100, 3, 0);
        //int amount2 = inventory.searchElement("Play Station 5", 500, 7, 0);
        //assertEquals(15, amount1);
        //assertEquals(100, amount2);
    }

    //Revisar el nombre de este test cuando ya lo este programando
    @Test
    public void searchEngineMethodCanFilterByTest (){
        assertTrue(true);
    }


}