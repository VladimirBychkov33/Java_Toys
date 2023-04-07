import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        
        shop_toys toyOne = new shop_toys("Ship", 4);
        shop_toys toyTwo = new shop_toys("Soldier", 3);
        shop_toys toyThree = new shop_toys("Rabbit", 2);
        shop_toys toyFour = new shop_toys("Train", 3);

        List<shop_toys> toys = new ArrayList<>();
        toys.add(toyOne);
        toys.add(toyTwo);
        toys.add(toyThree);
        toys.add(toyFour);

        System.out.println(toys);

    }

    static void showToys(List<shop_toys> toys) {
        for (shop_toys thing : toys) {
            System.out.println(thing);
        }
    }
}