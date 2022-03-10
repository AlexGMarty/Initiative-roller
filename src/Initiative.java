import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class Initiative {
    // Scanner object for getting user input
    static Scanner keyboard = new Scanner(System.in);

    // Create HashMap for holding combatants
    static HashMap<String, Combatant> initMap = new HashMap<String, Combatant>();

    public static void main(String[] args) {
        // Create example combatants and put them into the map
        Combatant kaali = new Combatant("Ka'ali", "Half-Elf Rogue", 8);
        Combatant donkules = new Combatant("Donkules", "Donkey Barbarian", 1);
        Combatant puck = new Combatant("Puck", "Satyr Bard", 4);
        Combatant invi = new Combatant("Invi", "\"Elf\" Cleric", 2);
        Combatant rinnor = new Combatant("Rinnor", "Half-Elf Paladin", -1);
        Combatant bomiz = new Combatant("Bomiz", "Vedalken Artificer", 2);
        initMap.put("Ka'ali", kaali);
        initMap.put("Donkules", donkules);
        initMap.put("Puck", puck);
        initMap.put("Invi", invi);
        initMap.put("Rinnor", rinnor);
        initMap.put("Bomiz", bomiz);

        // Get Arraylist of combatants sorted by initiative roll
        ArrayList<Combatant> initList = dumpSort(initMap);

        // Introduction
        System.out.println("Welcome to Alex's Awesome Initiative Roller!\n\n" +
                "This application allows you add, remove, or modify combatants in the initiative list.\n" +
                "It also lets you re-roll the initiative order.\n" +
                "Some combatants have been pre-loaded. Here is the current initiative list:");
        // Print initiative list
        printInit(initList);
        displayMenu();

        String user = "";
        while (!user.equals("X")){
            switch (user){
                case "A":
                    addCombatant();
                    break;
                case "B":
                    removeCombatant();
                    break;
                case "C":
                    modifyCombatant();
                    break;
                case "D":
                    reroll(initMap);
                    break;
                case "F":
                    initList = dumpSort(initMap);
                    printInit(initList);
                    break;
                case "?":
                    displayMenu();
                    break;
            }
            System.out.print("\nWhat would you like to do? (? to display menu): ");
            user = keyboard.nextLine().toUpperCase();
        }

        //End of program
        System.out.println("\nGoobladon says: Thanks for using the program!");
    }

    // A - addCombatant method to add a combatant to initiative
    public static void addCombatant(){
        System.out.print("What is the new combatant's name? ");
        String name = keyboard.nextLine();
        System.out.print("What is the new combatant's creature type?\n" +
                "You can say something like \"Red Dragon\" or \"Gnome Fighter\". ");
        String type = keyboard.nextLine();
        System.out.print("What is the new combatant's initiative bonus? ");
        int bonus = keyboard.nextInt();

        // Create Combatant object and add to map
        Combatant noob = new Combatant(name, type, bonus);
        initMap.put(name, noob);
    }

    // B - removeCombatant method to remove a combatant
    public static void removeCombatant(){
        System.out.print("Which combatant would you like to remove? ");
        String remove = keyboard.nextLine();
        if (initMap.keySet().contains(remove)){
            initMap.remove(remove);
            System.out.println(remove + " was removed from combat!");
        }
        else{
            System.out.println("Sorry! That combatant could not be found.");
        }
    }

    // C - modifyCombatant method to modify a combatant
    public static void modifyCombatant(){
        System.out.print("Which combatant would you like to modify? ");
        String user = keyboard.nextLine();
        if (initMap.keySet().contains(user)){
            System.out.print("Which attribute would you like to modify?\n" +
                    "A - Name\n" +
                    "B - Creature Type\n" +
                    "C - Initiative Bonus\n" +
                    "Choice: ");
            String attribute = keyboard.nextLine().toUpperCase();
            switch (attribute){
                case "A":
                    System.out.println(user + "'s current name is " + initMap.get(user).getName() + ".");
                    System.out.print("What would you like their new name to be? ");
                    String name = keyboard.nextLine();
                    initMap.get(user).setName(name);
                    System.out.println("Name changed to " + name + " successfully!");
                    break;
                case "B":
                    System.out.println(user + "'s current creature type is " + initMap.get(user).getType() + ".");
                    System.out.print("What would you like their new creature type to be? ");
                    String type = keyboard.nextLine();
                    initMap.get(user).setType(type);
                    System.out.println("Creature type changed to " + type + " successfully!");
                    break;
                case "C":
                    System.out.println(user + "'s current initiative bonus is " + initMap.get(user).getBonus() + ".");
                    System.out.print("What would you like their new initiative bonus to be? ");
                    int bonus = keyboard.nextInt();
                    initMap.get(user).setBonus(bonus);
                    System.out.println("Initiative bonus changed to " + bonus + " successfully!");
                    break;
                default:
                    System.out.println("Sorry! That is not a valid choice.");
            }
        }
        else{
            System.out.println("Sorry! That combatant could not be found.");
        }
    }

    // D - reroll method to reroll initiative for all combatants
    public static void reroll(HashMap<String, Combatant> map){
        for (Combatant i : map.values()){
            i.roll();
        }
        ArrayList<Combatant> initList = dumpSort(initMap);
        printInit(initList);
    }

    // displayMenu method to display the menu of possible actions
    public static void displayMenu(){
        System.out.println("\n-MAIN MENU-\n" +
                "A - Add a combatant\n" +
                "B - Remove a combatant\n" +
                "C - Modify a combatant\n" +
                "D - Re-roll initiative for all combatants\n" +
                "E - Remove ALL combatants from initiative\n" +
                "F - Show the current initiative list\n" +
                "X - Exit the program\n" +
                "? - Show this menu\n");
    }

    // printInit method to print the current initiative list
    public static void printInit(ArrayList initList){
        System.out.println("\n-CURRENT INITIATIVE-");
        for (Object each : initList){
            System.out.println(each);
        }
    }

    // dumpSort method for dumping the Combatant objects from the HashMap into an ArrayList and sorting
    public static ArrayList<Combatant> dumpSort(HashMap initMap){
        // Create ArrayList for holding Combatant objects
        ArrayList<Combatant> initList = new ArrayList<>();

        // Dump Combatants from initMap to initList
        for (Object i : initMap.values())
            initList.add((Combatant) i);

        // Sort by initiative roll, descending
        Collections.sort(initList);
        Collections.reverse(initList);

        return initList;
    }
}
