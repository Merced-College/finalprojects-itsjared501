//import java.util.ArrayList;
import java.util.Scanner;

public class Creature {
    private String name;
    private String userGender;
    private int userClass;
    private int userRace;
    private int level = 1;
    private int attackPower;
    private Scanner scanner = new Scanner(System.in);
    
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

    public int getUserClass() {
        return userClass;
    }

    public int getUserRace() {
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

    public void selectClass() {
        //display all classes and their abilities
        //ask the user to select a class
        System.out.println(" ");
        System.out.println("Classes -");
        System.out.println("");
        System.out.println("1. Cleric");
        System.out.println("Divine Intervention:    Monsters are blinded by the holy light (it's a stagelight).");
        System.out.println("                        Monsters receive -1 to their attack power.");
        System.out.println("Holy Hands:   Your god has given you the power to smite your foes with a light touch.");
        System.out.println("              When you do not have a weapon equipped, you gain +2 to your attack power.");
        System.out.println(" ");
        System.out.println("2. Thief");
        System.out.println("Expertise:   A good fight comes with greater rewards, whether its given or stolen.");
        System.out.println("             You gain an extra treasure when looting the room.");
        System.out.println("Slippery Feet:   Your perspiration has left pools of sweat behind you.");
        System.out.println("                 You gain +1 to run away.");
        System.out.println(" ");
        System.out.println("3. Warrior");
        System.out.println("Hard-Hitter:   Years of training have given you the advantage in combat.");
        System.out.println("               You gain +1 to attack power.");
        System.out.println("Duelist:   You don't go down without a fight.");
        System.out.println("           You now win ties in combat.");
        System.out.println(" ");
        System.out.println("4. Wizard");
        System.out.println("Charm Spell:   Decades of academic study combined with your killer looks come in handy every now and then.");
        System.out.println("               While in combat, you may discard your entire inventory to automatically beat the monster. You obtain");
        System.out.println("               all treasures but do not level up.");
        System.out.println("Flight Spell:   Those couple of weeks in beastial studies have taught you that you can't win every fight.");
        System.out.println("                While in combat, you may discard 1 item from your inventory to gain +1 to your Run Away roll.");
        System.out.println(" ");
        System.out.print("Select a class by entering the number of the class you want to select.");
        int classSelection = scanner.nextInt();
        userClass = classSelection;
    }

    /* public selectRace() {
        //display all races and their advantages and disadvantages
        //ask the user to select a race
    } */
}