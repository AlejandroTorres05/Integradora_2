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

    public void scenario1(){
        orderStorage = new OrderStorage();

        Order order = new Order("Pedro Pascal");
        products = new ArrayList<>();
        ArrayList<Integer> amount = new ArrayList<>();

        try {
            products.add(new Product(
                    "Carro",
                    "CarroDescription",
                    1000,
                    10,
                    2
                    )
            );

            products.add(new Product(
                            "Carro2",
                            "CarroDescription2",
                            2000,
                            20,
                            3
                    )
            );
        } catch (NonNaturalNumberException e) {
            throw new RuntimeException(e);
        }

        amount.add(4);
        amount.add(12);

        orderStorage.makeOrder(order, products, amount);
    }

    @Test
    public void testNameOrderStorage(){
        scenario1();

        assertEquals("Pedro Pascal", orderStorage.getOrderStorage().get(0).getCustomerName());
    }

    @Test
    public void testDecreaseAmount(){
        scenario1();

        assertEquals(6, products.get(0).getAmount());
    }
}
