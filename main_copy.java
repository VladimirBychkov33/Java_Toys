import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class main_copy {
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

        System.out.println("Список имеющихся игрушек для розыгрыша: ");
        showToys(toys);
        menuToy(toys);
    }

    static void showToys(List<shop_toys> toys) {
        for (shop_toys thing : toys) {
            System.out.println(thing);
        }
    }

    static void menuToy(List<shop_toys> toys) {
        Scanner inputToy = new Scanner(System.in);
        Boolean menu = true;
        String choice;

        String name = "";
        int weight = 0;

        while (menu) {

            System.out.println("Введите число:");
            System.out.println("1. Добавить игрушку для розыгрыша");
            System.out.println("2. Список оставшихся игрушек");
            System.out.println("3. Разыграть игрушку");
            System.out.println("Для выхода - ввести любой символ");

            choice = inputToy.nextLine();

            switch (choice) {
                
                case "1": 
                    System.out.print("Введите название новой игрушки: ");
                    name = inputToy.nextLine();
                    System.out.println("Введите вес игрушки (вероятность выпадения): " );
                    weight = inputToy.nextInt();
                    inputToy.nextLine();
                    shop_toys newToy = new shop_toys(name, weight);
                    toys.add(newToy);
                    
                    System.out.println("\nСписок игрушек на данный момент: ");
                    showToys(toys);
                    break;

                case "2":
                    if (toys.size() == 0) System.out.println("\nИгрушки кончились\n");
                    else {
                        System.out.println("\nСписок игрушек: ");
                        showToys(toys);
                    }
                    break;

                case "3": 
                    Random random = new Random();
                    List<shop_toys> copyToys = new ArrayList<>(toys);
                    shop_toys temp;
                    shop_toys tempNext;
                    for (int i = 0; i < copyToys.size(); i++) {
                        for (int j = 1; j < copyToys.size(); j++) {
                            if (copyToys.get(j).getWeights(copyToys) > copyToys.get(j - 1).getWeights(copyToys)) {
                                temp = copyToys.get(j);
                                tempNext = copyToys.get(j - 1);
                                copyToys.set(j, tempNext);
                                copyToys.set(j - 1, temp);
                            }
                        }
                    }

                    List<shop_toys> toysCopy = new ArrayList<>();
                    for (int i = 0; i < copyToys.size() / 3 * 2; i++) {
                        toysCopy.add(copyToys.get(i));
                    }

                    shop_toys prize; 
                    if (toysCopy.size() == 1) prize = toysCopy.get(0);
                    if (toysCopy.size() == 0) {
                        System.out.println("\nИгрушки кончились :(\n");
                        toys.clear();
                    }
                    else {
                        prize = toysCopy.get(random.nextInt(toysCopy.size())); 
                        System.out.println("\nВыпала игрушка:");
                        System.out.println(prize);

                        try (FileWriter fw = new FileWriter("listpriz.txt", true)) {
                            fw.write(prize.toString());
                            fw.write("\n");
                            fw.close();
                        } catch (Exception ex) {
                            System.out.println("Что-то пошло не так");
                        }
                    
                        for (shop_toys thing : toys) {
                            if (thing.containsID(prize.getID())) {
                                int w = thing.getWeightToy();
                                w--;
                                thing.setWeight(w);
                                if (thing.getWeightToy() == 0) toys.remove(thing);
                                break;
                            }
                        }

                        System.out.println("\nОстались игрушки: ");
                        showToys(toys);
                    }

                    break;
                
                default:
                    menu = false;
                    inputToy.close();
                    break;
            }
        }
    }
}
