package model;

import exceptions.NonNaturalNumberException;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class OrderSearchEngineTest {
    public static OrderStorage orderStorage;

    public void setupStage1() throws NonNaturalNumberException {
        ArrayList<Product> products1 = new ArrayList<>();
        ArrayList<Product> products2 = new ArrayList<>();
        ArrayList<Product> products3 = new ArrayList<>();
        ArrayList<Product> products4 = new ArrayList<>();

        products1.add(new Product("Harry Potter Collection", "A book", 10, 10, 0));
        products1.add(new Product("VacuumCleaner", "VacuumCleaner", 5, 20, 1));

        products2.add(new Product("Red T-shirt", "Red T-shirt", 15, 100, 2));
        products2.add(new Product("Beer barrel", "Beer barrel", 100, 15, 3));
        products2.add(new Product("Pencil box", "Pencil box", 2, 200, 4));

        products3.add(new Product("Golty Ball", "To play soccer", 20, 60, 5));
        products3.add(new Product("Red lip", "Red lip", 2, 500, 6));

        products4.add(new Product("Play Station 5", "Ps is better than xbox", 500, 100, 7));
        products4.add(new Product("How to make money?", "To make money", 5, 20, 0));
        products4.add(new Product("Fridge", "Fridge", 500, 20, 1));

        ArrayList<Integer> amount1 = new ArrayList<>();
        ArrayList<Integer> amount2 = new ArrayList<>();
        ArrayList<Integer> amount3 = new ArrayList<>();
        ArrayList<Integer> amount4 = new ArrayList<>();

        amount1.add(5); // 10*5
        amount1.add(20); // 5*20
        // 150

        amount2.add(50); // 15*50
        amount2.add(7); // 100*7
        amount2.add(120); // 120*2
        // 1690

        amount3.add(30); // 20*30
        amount3.add(250); // 2*250
        // 1100

        amount4.add(1); // 500*1
        amount4.add(5); // 5*5
        amount4.add(2); // 500*2
        // 1525

        orderStorage = new OrderStorage();
        Order order1 = new Order("Angelica", LocalDate.of(2016, 2, 11));
        Order order2 = new Order("Angela", LocalDate.of(2018, 11, 8));
        Order order3 = new Order("Federico", LocalDate.of(2021, 5, 18));
        Order order4 = new Order("Fernando", LocalDate.of(2023, 7, 27));

        orderStorage.makeOrder(order1, products1, amount1);
        orderStorage.makeOrder(order2, products2, amount2);
        orderStorage.makeOrder(order3, products3, amount3);
        orderStorage.makeOrder(order4, products4, amount4);
    }

    @Test
    public void searchTotalPriceIntervalTest1() throws NonNaturalNumberException {
        setupStage1();
        ArrayList<Order> organized = orderStorage.filterByTotalPrice(10,200);

        assertEquals(150, organized.get(0).getTotalPrice());
    }

    @Test
    public void searchTotalPriceIntervalTest2() throws NonNaturalNumberException {
        setupStage1();
        ArrayList<Order> organized = orderStorage.filterByTotalPrice(1000,1200);

        assertEquals(1100, organized.get(0).getTotalPrice());
    }

    @Test
    public void searchCustomerNameIntervalTest1() throws NonNaturalNumberException {
        setupStage1();
        ArrayList<Order> organized = orderStorage.filterOrderByPrefixInterval("Fe", "o");

        assertEquals("Federico", organized.get(0).getCustomerName());
        assertEquals("Fernando", organized.get(1).getCustomerName());
    }

    @Test
    public void searchCustomerNameIntervalTest2() throws NonNaturalNumberException {
        setupStage1();
        ArrayList<Order> organized = orderStorage.filterOrderByPrefixInterval("A", "a");

        assertEquals("Angelica", organized.get(0).getCustomerName());
        assertEquals("Angela", organized.get(1).getCustomerName());
    }

    @Test
    public void searchDateIntervalTest1() throws NonNaturalNumberException {
        setupStage1();
        ArrayList<Order> organized = orderStorage.filterByOrderDate(LocalDate.of(2015, 1, 1),LocalDate.of(2019, 1, 1));

        assertEquals(LocalDate.of(2018, 11, 8), organized.get(0).getOrderDate());
        assertEquals(LocalDate.of(2016, 2, 11), organized.get(1).getOrderDate());
    }

    @Test
    public void searchDateIntervalTest2() throws NonNaturalNumberException {
        setupStage1();
        ArrayList<Order> organized = orderStorage.filterByOrderDate(LocalDate.of(2020, 1, 1),LocalDate.of(2025, 1, 1));

        assertEquals(LocalDate.of(2023, 7, 27), organized.get(0).getOrderDate());
        assertEquals(LocalDate.of(2021, 5, 18), organized.get(1).getOrderDate());

    }
}
