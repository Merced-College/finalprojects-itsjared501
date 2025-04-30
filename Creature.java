import java.util.ArrayList;

public class Creature {
    private String name;
    private String userGender;
    private String userClass;
    private String userRace;
    private int level = 1;
    private int attackPower;
    
    //private ArrayList<Treasure> inventory = new ArrayList<Treasure>()[10];

    public Creature(String name, String userGender) {
        this.name = name;
        this.userGender = userGender;
    }

    public String getName() {
        return name;
    }

    public String getUserGender() {
        return userGender;
    }

    public String getUserClass() {
        return userClass;
    }

    public String getUserRace() {
        return userRace;
    }

    public int getLevel() {
        return level;
    }

    public int getAttackPower() {
        return attackPower;
    }

    /*public ArrayList<Treasure> getInventory() {
        return inventory;
    }*/

    public selectClass() {
        //display all classes and their abilities
        //ask the user to select a class
        System.out.println("Classes -");
        System.out.println("1. Cleric");
        System.out.println("");
        System.out.println("");
        System.out.println(" ");
        System.out.println("2. Thief");
        System.out.println("Expertise:   You gain an extra treasure when looting the room.");
        System.out.println("Slippery Feet:   You gain +1 to run away.");
        System.out.println(" ");
        System.out.println("3. Warrior");
        System.out.println("Hard-Hitter:   You gain +1 to attack power.");
        System.out.println("Duelist:   You now win ties in combat.");
        System.out.println(" ");
        System.out.println("4. Wizard");
        System.out.println("Charm:   While in combat, you may discard your entire inventory to automatically beat the monster. You obtain all treasures but do not level up.");
        System.out.println("Potion Master:   ");
        System.out.println(" ");
        System.out.println("Select a class by entering the number of the class you want to select.");
        String classSelection = scanner.nextLine();
        userClass = classSelection;
    }

    public selectRace() {
        //display all races and their advantages and disadvantages
        //ask the user to select a race
    }
}