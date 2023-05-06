package ui;

import exceptions.NonNaturalNumberException;
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
                     2. Option
                     3. Option
                     4. Option
                     5. Option
                    """);

            option = sc.nextInt();
            sc.nextLine();

            executeMenu(option);

        } while (option != 0);
    }

    public void executeMenu(int option){
        switch (option){
            case 0:
                System.out.println("Closing menu, Goodbye...");
                break;

            case 1:
                System.out.println("¬ | Registering New Product");
                registerProduct();
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
}