package model;

import exceptions.NonNaturalNumberException;
import exceptions.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

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
                new Product("Harry Potter Collection", "A book", 10, 10, 0)//23  10
        );
        inventory.saveProduct(
                new Product("VacuumCleaner", "VacuumCleaner", 5, 20, 1)//13  7
        );
        inventory.saveProduct(
                new Product("Red T-shirt", "Red T-shirt", 15, 100, 2)//11  5
        );
        inventory.saveProduct(
                new Product("Beer barrel", "Beer barrel", 100, 15, 3)//11  6
        );
        inventory.saveProduct(
                new Product("Pencil box", "Pencil box", 2, 200, 4)//10  3
        );
        inventory.saveProduct(
                new Product("Golty Ball", "To play soccer", 20, 60, 5)//10  4
        );
        inventory.saveProduct(
                new Product("Red lip", "Red lip", 2, 500, 6)//7  2
        );
        inventory.saveProduct(
                new Product("Play Station 5", "Ps is better than xbox", 500, 100, 7)//14  8
        );
        inventory.saveProduct(
                new Product("How to make money?", "To make money", 5, 20, 0)//17  9
        );
        inventory.saveProduct(
                new Product("Fridge", "Fridge", 500, 20, 1)//6  1
        );
    }

    /**
     * This method uses the searchAnElement
     * method to verify the amount of
     * two products.
     * */
    @Test
    public void searchAnElementCanReturnTheObjectAmountWhenTheObjectIsSavedInTheInventoryTest ()
            throws NonNaturalNumberException {
        setupStage1();
        int amount1 = inventory.searchAnElement("Beer barrel", 100, 3, 0);
        int amount2 = inventory.searchAnElement("Play Station 5", 500, 7, 0);
        assertEquals(15, amount1);
        assertEquals(100, amount2);
    }

    /**
     * This method verifies if the
     * searchAnElement method can throw
     * ProductIsNotRegisteredException Exception
     * when a searched product isn't registered
     * yet in the inventory
     * */
    @Test
    public void searchAnElementCanThrowProductIsNotRegisteredExceptionExceptionTest ()
            throws NonNaturalNumberException{
        setupStage1();
        assertThrows(ProductIsNotRegisteredException.class, ()->{
            inventory.searchAnElement("Non-existent", 2, 3, 7);
        });
    }

    /**
     * This method verifies if the filter
     * can get all elements in an
     * entered range correctly when the
     * user is searching by the price
     * */
    @Test
    public void filterByRangeMethodCanGetAllElementsWhenTheUserFilterThemByPriceTest()
            throws NonNaturalNumberException{
        setupStage1();

        ArrayList<Product> filtered = inventory.filterByRange(1,5,15);
        String filteredProducts = "";

        for (int i = 0; i < filtered.size(); i++){
            filteredProducts += filtered.get(i).getName() + " ";
        }

        String expectedProducts = "Harry Potter Collection How to make money? Red T-shirt VacuumCleaner ";

        assertEquals(filteredProducts, expectedProducts);
    }

    /**
     * This method verifies if the filter
     * can get all elements in an
     * entered range correctly when the
     * user is searching by the sales
     * number
     * */
    @Test
    public void filterByRangeMethodCanGetAllElementsWhenTheUserFilterThemBySalesTest()
            throws NonNaturalNumberException{
        setupStage1();

        ArrayList<Product> filtered = inventory.filterByRange(2,0,2);
        String filteredProducts = "";

        for (int i = 0; i < filtered.size(); i++){
            filteredProducts += filtered.get(i).getName() + " ";
        };

        String expectedProducts = "Beer barrel Fridge Golty Ball Harry Potter Collection How to make money? Pencil box Play Station 5 Red T-shirt Red lip VacuumCleaner ";

        assertEquals(expectedProducts, filteredProducts);
    }

    /**
     * This method verifies if the filter
     * can get all elements in an
     * entered range correctly when the
     * user is searching by the amount
     * */
    @Test
    public void filterByRangeMethodCanGetAllElementsWhenTheUserFilterThemByAmountTest()
            throws NonNaturalNumberException{
        setupStage1();

        ArrayList<Product> filtered = inventory.filterByRange(3,10,60);
        String filteredProducts = "";

        for (int i = 0; i < filtered.size(); i++){
            filteredProducts += filtered.get(i).getName() + " ";
        };

        String expectedProducts = "Beer barrel Fridge Golty Ball Harry Potter Collection How to make money? VacuumCleaner ";

        assertEquals(expectedProducts, filteredProducts);
    }

    /**
     * This method verifies if the program
     * can throw ThereIsNotProductsByTheFilterException
     * exception when there aren't
     * products by an entered filter
     * */
    @Test
    public void filterByRangeMethodCanThrowThereIsNotProductsByTheFilterExceptionExceptionTest(){
        assertThrows(ThereIsNotProductsByTheFilterException.class, ()->{
            inventory.filterByRange(2,10,100);
        });
    }

    /**
     * This method verifies if the program can filter
     * products by interval using just letters
     * */
    @Test
    public void filterByIntervalMethodCanFilterTheProductsByLettersTest ()
            throws NonNaturalNumberException{
        setupStage1();

        ArrayList<Product> filtered = inventory.filterByInterval("F","e");
        Product expected = new Product("Fridge", "Fridge", 500, 20, 1);

        assertEquals(filtered.get(0), expected);
    }

    /**
     * This method verifies if the program can filter
     * products by interval using just prefixes
     * */
    @Test
    public void filterByIntervalMethodCanFilterTheProductsByPrefixTest ()
            throws NonNaturalNumberException{
        setupStage1();

        ArrayList<Product> filtered = inventory.filterByInterval("Har","ion");
        Product expected = new Product("Harry Potter Collection", "A book", 10, 10, 0);

        assertEquals(filtered.get(0), expected);
    }

    /**
     * This method verifies if the program
     * can throw ThereIsNotProductsByTheFilterException
     * exception when there aren't
     * products by the entered prefixes
     * */
    @Test
    public void filterByIntervalMethodCanThrowThereIsNotProductsByTheFilterExceptionExceptionTest(){
        assertThrows(ThereIsNotProductsByTheFilterException.class, ()->{
            inventory.filterByInterval("NON", "NON");
        });
    }
}