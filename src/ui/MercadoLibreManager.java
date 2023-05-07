package ui;

import exceptions.NonNaturalNumberException;
import exceptions.ProductIsNotRegisteredException;
import exceptions.ThereIsNotProductsByTheFilterException;
import model.MercadoLibreController;

import java.util.Scanner;

public class MercadoLibreManager {
    private MercadoLibreController controller;
    private Scanner sc;

    public MercadoLibreManager(){
        controller = new MercadoLibreController();
        sc = new Scanner(System.in);
    }

    public static void main(String[] args)  {
        MercadoLibreManager manager = new MercadoLibreManager();
        manager.menu();
    }

    public void menu(){
        int option = 0;
        do{
            System.out.println("""
                    ¬ | Menu
                     1. Register New Product
                     2. View amount of an specific product
                     3. View products by range 
                     4. Option
                     5. Option
                    """);

            option = sc.nextInt();

        try {
            executeMenu(option);

        } catch (ProductIsNotRegisteredException ex){
            System.out.println("There was an error. May be the entered characters and the real characters are not equals or the product is not registered yet");
        } catch (NonNaturalNumberException ex){
            //Esta no se como manejarla
        } catch (ThereIsNotProductsByTheFilterException ex){
            System.out.println("There was an error. There aren't products according to the last range");
        }
        } while (option != 0);
    }

    public void executeMenu(int option) throws
            NonNaturalNumberException, ProductIsNotRegisteredException{
        switch (option){
            case 0:
                System.out.println("Closing menu, Goodbye...");
                break;

            case 1:
                System.out.println("¬ | Registering New Product");
                registerProduct();
                break;

            case 2:
                searchAProduct();
                break;

            case 3:
                filterByRange();
                break;

            default:
                System.out.println("Error, Option out of range.");
                break;
        }
    }

    public void registerProduct(){

        System.out.print("Register Product name: ");
        String name = sc.nextLine();

        System.out.print("Register Product description: ");
        String description = sc.nextLine();

        System.out.print("Register Product price: ");
        double price = sc.nextDouble();
        sc.nextLine();

        System.out.print("Register Product amount: ");
        int amount = sc.nextInt();
        sc.nextLine();

        System.out.println(controller.showCategories());
        System.out.print("Choice a category: ");
        int category = sc.nextInt();
        sc.nextLine();

        try {
            controller.addProduct(
                    controller.createProduct(
                            name,
                            description,
                            price,
                            amount,
                            category
                    )
            );
        } catch (NonNaturalNumberException exception) {
            System.out.println("\nProduct registration failed, " + exception.getMessage());
        }

    }

    public void registerOrder(){

    }

    public void searchAProduct ()
            throws NonNaturalNumberException, ProductIsNotRegisteredException {
        System.out.println("Type the name of the product");
        sc.nextLine();
        String name = sc.nextLine();
        System.out.println("Please type the price of " + name);
        Double price = sc.nextDouble();
        System.out.println("The available categories are: "
                + "\n1. BOOKS"
                + "\n2. ELECTRONICS"
                + "\n3. CLOTHING_AND_ACCESSORIES"
                + "\n4. FOOD_AND_DRINKS"
                + "\n5. STATIONERY"
                + "\n6. SPORTS"
                + "\n7. BEAUTY_AND_PERSONAL_CARE"
                + "\n8. TOYS_AND_GAMES");
        System.out.println("Please type the number of the category for " + name);
        int category = sc.nextInt();
        System.out.println("Please type the sales of the product");
        int sales = sc.nextInt();

        int amount = controller.searchAProduct(name, price, category-1, sales)

        System.out.println("There are " + amount + " units of " + name);

        /*
        * Aqui podes meter la opcion a agregar de una vez a un pedido
        * */
    }

    public void filterByRange() throws ThereIsNotProductsByTheFilterException {
        System.out.println("Yo can filter the products by "
                + "\n1. Price"
                + "\n2. Sales"
                + "\n3. Amount");
        int option = sc.nextInt();
        System.out.println("Type the minor value of the range");
        double beginning = sc.nextDouble();
        System.out.println("Type the mayor value of the range");
        double end = sc.nextDouble();
        controller.filterByRange(option, beginning, end);
        System.out.println("What order do you want to see the filtered products: "
                + "\n1. By their name"
                + "\n2. By their price"
                + "\n3. By their category"
                + "\n4. By their sales");
        int filter = sc.nextInt();
        System.out.println("Do yo want to see the products ordered in:"
                + "\n1. Upward order"
                + "\n2. Falling order");
        int order = sc.nextInt();
        System.out.println(controller.showAByRangeSearch(filter, order));

        /*
        * Aqui podes meter la opcion de comprar de una
        * */
    }
}