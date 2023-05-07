package model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import exceptions.ThereIsNotProductsByTheFilterException;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

/**
 * @author Silem Nabib Villa Contreras
 * @author Alejandro Torres Soto
 * */
public class OrderStorage {
    static String path = "resources/OrderStorageData.json";
    ArrayList<Order> orderStorage;

    /**
     * Creates a ordersDataBase.
     */
    public OrderStorage() {
        orderStorage = new ArrayList<>();
    }

    public void makeOrder(Order newOrder, ArrayList<Product> orderProducts, ArrayList<Integer> amount) {
        ArrayList<ProductOrder> productOrders = addProductsToOrder(newOrder, orderProducts, amount);

        for (ProductOrder x : productOrders) {
            newOrder.addProduct(x);
        }

        orderStorage.add(newOrder);
        sortByTotalPrice();
    }

    private ArrayList<ProductOrder> addProductsToOrder(Order order, ArrayList<Product> orderProducts, ArrayList<Integer> amount) {
        ArrayList<ProductOrder> productOrders = new ArrayList<>();

        for (int i = 0; i < orderProducts.size(); i++) {
            Product tempProduct = orderProducts.get(i);
            int tempAmount = amount.get(i);

            orderProducts.get(i).decreaseAmount(tempAmount);
            orderProducts.get(i).addSales(tempAmount);

            productOrders.add(new ProductOrder(tempProduct, tempAmount));
        }

        return productOrders;
    }

    public Order getOrderStorage(int index) {
        return orderStorage.get(index);
    }

    private ArrayList<Order> filterByTotalPrice(double beginning, double end) {

        ArrayList<Order> filtered = new ArrayList<>();

        for (int i = 0; i < orderStorage.size(); i++) {
            if (orderStorage.get(i).getTotalPrice() >= beginning && orderStorage.get(i).getTotalPrice() <= end) {
                filtered.add(orderStorage.get(i));
            }
        }

        return filtered;
    }

    private ArrayList<Order> filterByOrderDate(LocalDate beginningYear, LocalDate endYear) {
        ArrayList<Order> tempArr = orderStorage;
        Collections.sort(tempArr);

        ArrayList<Order> filtered = new ArrayList<>();

        for (int i = 0; i < orderStorage.size(); i++) {
            if (beginningYear.isBefore(tempArr.get(i).getOrderDate()) &&
                    endYear.isAfter(tempArr.get(i).getOrderDate())) {

                filtered.add(orderStorage.get(i));
            }
        }

        return filtered;
    }

    private void sortByTotalPrice() {
        Collections.sort(orderStorage, Comparator.comparingDouble(Order::getTotalPrice));
    }

    public ArrayList<Order> filterOrderByInterval(String beginning, String end)
            throws ThereIsNotProductsByTheFilterException {

        ArrayList<Order> filtered = new ArrayList<>();

        for (Order order : orderStorage) {
            if (order.getCustomerName().startsWith(beginning) &&
                    order.getCustomerName().endsWith(end)) {
                filtered.add(order);
            }
        }

        if (filtered.isEmpty()) throw new ThereIsNotProductsByTheFilterException();

        return filtered;
    }

    /**
     * This method can save the last saved data in the system
     * @Post the data will be saved in resources folder
     */
    public void save() throws IOException {
        FileOutputStream fos = new FileOutputStream(path);
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .create();

        String orderStorageDataToJson = gson.toJson(orderStorage);

        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos));
        writer.write(orderStorageDataToJson);
        writer.flush();
        fos.close();
    }

    /**
     * This method loads the registered
     * data to the program to keep
     * the changes made in the last
     * execution.
     * @Post the last saved data will
     *             be load
     * */
    public void loadData () throws IOException {
        File file = new File(path);

        if (file.exists()){
            FileInputStream fis = new FileInputStream(file);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));

            String content = "";
            String line;

            while ((line = reader.readLine()) != null){
                content += line + "\n";
            }

            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                    .create();

            Order[] tempArrayOrderStorage = gson.fromJson(content, Order[].class);
            orderStorage.addAll(Arrays.asList(tempArrayOrderStorage));
            fis.close();

        }else {
            File f = new File("OrderStorageData");
            if (!f.exists()){
                f.mkdirs();
            }
            file.createNewFile();
        }
    }
}
