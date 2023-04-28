package ui;

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

    }

    public void menu(){
        int option = 0;
        do{
            System.out.println("""
                    ¬ | Menu
                     1. Option
                     2. Option
                     3. Option
                     4. Option
                     5. Option
                    """);
        } while (option != 0);
    }

    public void executeMenu(int option){
        switch (option){
            case 0:
                System.out.println("Closing menu, Goodbye...");
                break;

            case 1:
                break;

            default:
                System.out.println("Error, Option out of range.");
                break;
        }
    }

    public void registerOrder(){

    }
}