import exceptions.NonNaturalNumberException;
import model.Inventory;
import model.Product;

public class MainTwo {

    public static Inventory inventory;
    public static void main (String[] args) throws NonNaturalNumberException {
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



        Product two = new Product("Beer barrel", "",100, 3, 3);

        Product one = new Product("Beer barrel", "Beer barrel", 100, 15, 3);

        boolean index = inventory.contains(new Product("Red T-shirt", "Red T-shirt", 15, 100, 2));



        System.out.println(index);
    }
}
