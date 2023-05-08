package ui;

import exceptions.*;
import model.MercadoLibreController;

import java.io.IOException;
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
                     1. Register New Product.
                     2. Search a Specific Product.
                     3. Make a Order.
                     4. Search Product Menu.
                     5. Search Order Menu.
                     6. Save Data.
                     7. Load Data.
                     0. Close Menu.
                    """);

            System.out.print("Select an option: ");
            option = sc.nextInt();
            sc.nextLine();

        try {
            executeMenu(option);

        } catch (ProductIsNotRegisteredException ex){
            System.out.println("There was an error. May be the entered characters and the real characters are not equals or the product is not registered yet");
        } catch (NonNaturalNumberException ex){
            //Esta no se como manejarla
        } catch (ThereIsNotProductsByTheFilterException ex){
            System.out.println("There was an error. There aren't products according to the last range");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        } while (option != 0);
    }

    public void executeMenu(int option)
            throws NonNaturalNumberException, ProductIsNotRegisteredException, IOException {
        switch (option){
            case 0:
                System.out.println("Closing menu, Goodbye...");
                break;

            case 1:
                System.out.println("¬ | Registering New Product");
                registerProduct();
                break;

            case 2:
                try {
                    searchAProduct();
                } catch (NonNaturalNumberException | ProductIsNotRegisteredException e) {
                    System.out.println(e.getMessage());
                }
                break;

            case 3:
                registerOrder();
                break;

            case 4:
                searchProductMenu();
                break;

            case 5:
                searchOrderMenu();
                break;

            case 6:
                controller.save();
                break;

            case 7:
                controller.loadData();
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
        System.out.print("Enter Customer Name: ");
        String customerName = sc.nextLine();

        System.out.println("Please enter the date in the following format yy mm dd. If the date is today, enter 0 0 0.");
        int yy = sc.nextInt();
        int mm = sc.nextInt();
        int dd = sc.nextInt();
        sc.nextLine();

        int productSelection = 0;
        int amountSelection = 0;
        do{

            searchProductMenu();
            System.out.print("Select a product: ");

            productSelection = sc.nextInt();
            sc.nextLine();

            if (productSelection != 0){
                System.out.print("Enter the required quantity: ");
                amountSelection = sc.nextInt();
                sc.nextLine();

                if (amountSelection != 0){
                    try {
                        controller.selectProductInInventory(productSelection-1, amountSelection);
                    } catch (OutOfStockException | IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                }
            }

        } while(productSelection != 0 && amountSelection != 0);

        if (yy == 0 || mm == 0 || dd == 0){
            controller.addOrder(controller.createOrder(customerName));
        } else {
            controller.addOrder(controller.createOrder(customerName, controller.createLocalDate(yy,mm,dd)));

        }
    }

    public void searchOrderMenu(){

        System.out.println("""
                ¬ | Search Order Menu
                 1. Search Order by Total Price Interval
                 2. Search Order by Customer Name Prefix
                 3. Search Order by Date Interval
                 0. Close menu.
                """);

        System.out.print("Select an option: ");
        int selection = sc.nextInt();
        sc.nextLine();

        switch (selection){
            case 0:
                System.out.println("Closing Search Order Menu...");
                break;

            case 1:
                try {
                    searchAOrderByPriceInterval();
                } catch (EmptyOrderStorageException | NonNaturalNumberException | IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
                break;

            case 2:
                try {
                    searchAOrderByPrefix();
                } catch (EmptyOrderStorageException | IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
                break;

            case 3:
                try {
                    searchAOrderByDate();
                } catch (EmptyOrderStorageException | IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
                break;

            default:
                System.out.println("Error, Option out of range.");
                break;
        }
    }

    public void searchAOrderByPriceInterval()
            throws EmptyOrderStorageException, NonNaturalNumberException, IllegalArgumentException {

        System.out.println("¬ | Search Order by total price range.");

        System.out.print("Enter the initial range: ");
        double beginning = sc.nextDouble();
        sc.nextLine();

        System.out.print("Enter the final range: ");
        double end = sc.nextDouble();
        sc.nextLine();

        System.out.println(controller.showOrder(controller.orderByTotalPriceInterval(beginning, end)));
    }

    public void searchAOrderByPrefix()
            throws EmptyOrderStorageException, IllegalArgumentException {

        System.out.println("¬ | Search Order by Customer Prefix Range.");

        System.out.print("Enter the initial prefix: ");
        String beginning = sc.nextLine();

        System.out.print("Enter the final prefix: ");
        String end = sc.nextLine();

        System.out.println(controller.showOrder(controller.orderByPrefixInterval(beginning, end)));
    }

    public void searchAOrderByDate()
            throws EmptyOrderStorageException, IllegalArgumentException {
        System.out.println("¬ | Search Order by Date range.");

        System.out.print("Enter the initial date (yy mm dd): ");
        int yyBeginning = sc.nextInt();
        int mmBeginning = sc.nextInt();
        int ddBeginning = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter the final prefix (yy mm dd): ");
        int yyEnd = sc.nextInt();
        int mmEnd = sc.nextInt();
        int ddEnd = sc.nextInt();
        sc.nextLine();

        System.out.println(controller.showOrder(controller.orderByDateInterval(
                controller.createLocalDate(yyBeginning, mmBeginning, ddBeginning),
                controller.createLocalDate(yyEnd, mmEnd, ddEnd)
        )));
    }

    public void searchAProduct ()
            throws NonNaturalNumberException, ProductIsNotRegisteredException {
        System.out.println("¬ | Searching Specific Product");
        System.out.print("Type the name of the product: ");
        String name = sc.nextLine();

        System.out.print("Please type the price of " + name + ": ");
        double price = sc.nextDouble();

        System.out.println("The available categories are: "
                + "\n1. BOOKS"
                + "\n2. ELECTRONICS"
                + "\n3. CLOTHING_AND_ACCESSORIES"
                + "\n4. FOOD_AND_DRINKS"
                + "\n5. STATIONERY"
                + "\n6. SPORTS"
                + "\n7. BEAUTY_AND_PERSONAL_CARE"
                + "\n8. TOYS_AND_GAMES");
        System.out.print("Please type the number of the category for " + name + ": ");
        int category = sc.nextInt();
        System.out.print("Please type the sales of the product: ");
        int sales = sc.nextInt();

        int amount = controller.searchAProduct(name, price, category-1, sales);

        System.out.println("There are " + amount + " units of " + name);
    }

    public void filterByRange() throws ThereIsNotProductsByTheFilterException {
        System.out.println("Yo can filter the products by "
                + "\n1. Price"
                + "\n2. Sales"
                + "\n3. Amount");
        System.out.print("Select an option: ");
        int option = sc.nextInt();

        System.out.print("Type the minor value of the range: ");
        double beginning = sc.nextDouble();
        sc.nextLine();

        System.out.print("Type the mayor value of the range: ");
        double end = sc.nextDouble();
        sc.nextLine();

        controller.filterByRange(option, beginning, end);
        System.out.println("What order do you want to see the filtered products: "
                + "\n1. By their name"
                + "\n2. By their price"
                + "\n3. By their category"
                + "\n4. By their sales");
        System.out.print("Select an option: ");
        int filter = sc.nextInt();
        sc.nextLine();

        System.out.println("Do yo want to see the products ordered in:"
                + "\n1. Upward order"
                + "\n2. Falling order");

        System.out.print("Select an option: ");
        int order = sc.nextInt();
        sc.nextLine();
        System.out.println(controller.showAByRangeSearch(filter, order));
    }

    public void filterByInterval (){
        System.out.print("Please type the first letter or prefix: ");
        String beginning = sc.nextLine();

        System.out.print("Please type the last letter suffix: ");
        String end = sc.nextLine();

        controller.filterByInterval(beginning, end);

        System.out.println("Do yo want to see the products ordered in:"
                + "\n1. Upward order"
                + "\n2. Falling order");
        System.out.print("Select an option: ");
        int order = sc.nextInt();
        sc.nextLine();

        System.out.println(controller.showByIntervalSearch(order));
    }

    private void searchProductMenu() {
        System.out.println("""
                ¬ | Search Product Menu
                 1. Search Product By Range
                 2. Search Product By Interval
                 0. Close menu.
                """);

        System.out.print("Select an option: ");
        int selection = sc.nextInt();
        sc.nextLine();

        switch (selection){
            case 0:
                System.out.println("Closing Search Product Menu...");
                break;

            case 1:
                try {
                    filterByRange();
                } catch (ThereIsNotProductsByTheFilterException e) {
                    System.out.println(e.getMessage());
                }
                break;

            case 2:
                filterByInterval();
                break;

            default:
                System.out.println("Error, Option out of range.");
                break;
        }
    }
}