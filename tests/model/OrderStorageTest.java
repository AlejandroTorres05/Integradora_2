package model;

import exceptions.NonNaturalNumberException;

import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;



/**
 * This class just validates the basic
 * behavior of OrdersStorage. To see the
 * tests of the order search engine,
 * go to OrderSearchEngineTest class
 * @see OrderSearchEngineTest
 * */
public class OrderStorageTest {

    private OrderStorage orderStorage;
    private ArrayList<Product> products;
    private ArrayList<Integer> amount;

    public void setupStage1(){

        products = new ArrayList<>();
        try {
            Product product1 = new Product(
                    "ProductoUno",
                    "DescripcionUno",
                    100,
                    10,
                    1
            );
            Product product2 = new Product(
                    "ProductoDos",
                    "DescripcionDos",
                    200,
                    20,
                    2
            );
            Product product3 = new Product(
                    "ProductoTres",
                    "DescripcionTres",
                    330,
                    35,
                    3
            );
            Product product4 = new Product(
                    "ProductoCuatro",
                    "DescripcionCuatro",
                    430,
                    43,
                    4
            );
            Product product5 = new Product(
                    "ProductoCinco",
                    "DescripcionCinco",
                    560,
                    70,
                    6
            );

            products.add(product1);
            products.add(product2);
            products.add(product3);
            products.add(product4);
            products.add(product5);
        } catch (NonNaturalNumberException e) {
            throw new RuntimeException(e);
        }

        orderStorage = new OrderStorage();
        amount = new ArrayList<>();

        Order order = new Order("Pedro Pascal");

        amount.add(10);
        amount.add(14);
        amount.add(20);
        amount.add(31);
        amount.add(54);

        orderStorage.makeOrder(order, products, amount);
    }

    @Test
    public void saveOrderInOrderStorageTest1(){
        setupStage1();

        assertEquals("Pedro Pascal", orderStorage.getOrderStorage(0).getCustomerName());
    }

    @Test
    public void saveOrderInOrderStorageTest2(){
        setupStage1();

        String productAdded = products.get(0).getName();
        String productInStorage = orderStorage.getOrderStorage(0).getOrderProducts(0).getName();

        assertEquals(productAdded, productInStorage);
    }

    @Test
    public void requiredAmountSuccessfullyStoredTest1(){
        setupStage1();

        int amountRequired = amount.get(0);
        int amountStored = orderStorage.getOrderStorage(0).getOrderProducts(0).getAmount();

        assertEquals(amountRequired, amountStored);
    }

    @Test
    public void requiredAmountSuccessfullyStoredTest2(){
        setupStage1();

        int amountRequired = amount.get(3);
        int amountStored = orderStorage.getOrderStorage(0).getOrderProducts(3).getAmount();

        assertEquals(amountRequired, amountStored);
    }

    @Test
    public void orderedQuantitiesSubtractedFromInventoryTest1(){
        setupStage1();

        int productAmountExpected = 0;
        int productAmount = products.get(0).getAmount();

        assertEquals(productAmountExpected, productAmount);
    }

    @Test
    public void orderedQuantitiesSubtractedFromInventoryTest2(){
        setupStage1();

        int productAmountExpected = 12;
        int productAmount = products.get(3).getAmount();

        assertEquals(productAmountExpected, productAmount);
    }

}
