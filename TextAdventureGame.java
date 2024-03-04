import java.util.Scanner;

public class TextAdventureGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the  Game!");

        System.out.println("You wake up on a deserted island after surviving a shipwreck.");
        System.out.println("In front of you, there are two paths: one leading towards the jungle and another towards the beach.");
        System.out.println("Which path do you choose? (jungle/beach)");

        String choice = scanner.nextLine().toLowerCase();

        switch (choice) {
            case "jungle":
                jungleScenario();
                break;
            case "beach":
                beachScenario();
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                break;
        }

        scanner.close();
    }

    public static void jungleScenario() {
        System.out.println("You chose to explore the jungle.");
        System.out.println("As you venture deeper into the dense foliage, you encounter a wild animal.");
        System.out.println("Do you try to fight the animal or run away? (fight/run)");

        Scanner scanner = new Scanner(System.in);
        String choice = scanner.nextLine().toLowerCase();

        if (choice.equals("fight")) {
            System.out.println("You bravely confront the animal and manage to scare it away.");
            System.out.println("Afterwards, you find a hidden cave with a stash of supplies.");
            System.out.println("Congratulations! You survived and found valuable resources.");
        } else if (choice.equals("run")) {
            System.out.println("You quickly turn around and run away from the animal.");
            System.out.println("Luckily, you escape and find a small stream where you can quench your thirst.");
            System.out.println("Congratulations! You survived and found water.");
        } else {
            System.out.println("Invalid choice. Please try again.");
        }

        
    }

    public static void beachScenario() {
        System.out.println("You chose to head towards the beach.");
        System.out.println("You come across a stranded boat with a sail torn apart.");
        System.out.println("Do you attempt to repair the sail or search for other means of escape? (repair/search)");

        Scanner scanner = new Scanner(System.in);
        String choice = scanner.nextLine().toLowerCase();

        if (choice.equals("repair")) {
            System.out.println("You spend hours repairing the sail and manage to make it seaworthy.");
            System.out.println("After setting sail, you eventually spot a passing ship and get rescued.");
            System.out.println("Congratulations! You successfully escaped the island.");
        } else if (choice.equals("search")) {
            System.out.println("You explore the beach and find a hidden cave with a map inside.");
            System.out.println("The map leads you to a stash of food and a radio for calling for help.");
            System.out.println("Congratulations! You found the necessary supplies to signal for rescue.");
        } else {
            System.out.println("Invalid choice. Please try again.");
        }

    }
}
