import java.util.LinkedList;
import java.util.Scanner;

public class Creature {
    private String name;
    private String userClass;
    private String userRace;
    private int level = 1;
    private int attackPower = 1;
    private boolean isAlive = true;

    private LinkedList<Treasure> inventory = new LinkedList<Treasure>();
    private LinkedList<Treasure> equipment = new LinkedList<Treasure>();
    
    Scanner scanner = new Scanner(System.in);
    
    
    //Constructor
    public Creature(String name) {
        this.name = name;
        //this.userGender = userGender;
    }

    //The getters
    public String getName() {
        return name;
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

    public boolean isAlive() {
        return isAlive;
    }

    //This method accesses the user's inventory and displays all the items in it.
    public void accessInventory() {
        //for loop to display all the items in the inventory
        System.out.println("* * * * * * * * * * * * * * * * * * * * * * * * * * * * ");
        for (int i = 0; i < inventory.size(); i++) {
            System.out.println(inventory.get(i).toString() + "\n");
        }
        System.out.println("* * * * * * * * * * * * * * * * * * * * * * * * * * * * \n");
    }

    public void accessEquipment() {
        //for loop to display all the items in the equipment
        System.out.println("* * * * * * * * * * * * * * * * * * * * * * * * * * * * ");
        for (int i = 0; i < equipment.size(); i++) {
            System.out.println(equipment.get(i).toString());
        }
        System.out.println("* * * * * * * * * * * * * * * * * * * * * * * * * * * * ");
    }

    public void setClass (String chosenClass) {
        if (chosenClass.equalsIgnoreCase("cleric")) {
            userClass = "Cleric";
        } 
        else if (chosenClass.equalsIgnoreCase("thief")) {
            userClass = "Thief";
        } 
        else if (chosenClass.equalsIgnoreCase("warrior")) {
            userClass = "Warrior";
        } 
        else if (chosenClass.equalsIgnoreCase("wizard")) {
            userClass = "Wizard";
        }
        else {
            System.out.println("I believe you misheard me. Please select a class from the list above.");
            selectClass();
        }
    }

    public void setRace (String chosenRace) {
        if (chosenRace.equalsIgnoreCase("dwarf")) {
            userRace = "Dwarf";
        } 
        else if (chosenRace.equalsIgnoreCase("elf")) {
            userRace = "Elven";
        } 
        else if (chosenRace.equalsIgnoreCase("halfling")) {
            userRace = "Halfling";
        }
        else {
            System.out.println("I believe you misheard me. Please select a race from the list above.");
            selectRace();
        }
    }

    //This method increases the user's level when they defeat a monster.
    public void levelUp(Monster monster) {
        if (monster.getLevel() >= 14) {
            level = Math.min(level + 2, 10);
            System.out.println("You are now level " + level + "!");
        } else {
            level = Math.min(level + 1, 10);
            System.out.println("You are now level " + level + "!");
        }
    }

    //this method decreases the user's level due to bad stuff
    public void decreaseLevel(int amount) {

    }

    //This method icreases the user's attack power from items and will immediately deactivate after the fight.
    public void tempAPAdd(int power) {

    }

    //This method will be used to increase the user's attack power from weapons, armor, and footgear.
    public void addAttackPower(int power) {
        attackPower = level + power;
        System.out.println("Attack power increased by " + power + ". Total attack power: " + attackPower);
    }

    //This method ensures that the inventory size is checked before adding items to the inventory.
    public int inventorySizeCheck() {
        if (userRace.equalsIgnoreCase("Dwarf")) {
            return 12;
        }
        else {
            return 10;
        }
    }

    //This method adds treasures gained from the fight to the user's inventory.
    public void addToInventory(Treasure newItem) {
        if (inventory.size() >= inventorySizeCheck()) {
            System.out.println("Inventory is full. Cannot add more items.");
            return;
        }
        else {
            inventory.add(newItem);
        }
    }

    //This method removes treasures from the user's inventory.
    public void removeFromInventory(Treasure item) {
        if (inventory.contains(item)) {
            inventory.remove(item);
        } else {
            System.out.println("Item not found in inventory.");
        }
    }

    //the method should iterate through the inventory and find the item with the matching name; if found, then use. else, cannot find
    public void useItem(Treasure item) {
        /*if (item.getName().equalsIgnoreCase("item")) {
            System.out.println("You have used " + item.getName() + ".");
            addAttackPower(item.getAttackPower());
            removeFromInventory(item);
        } else {
            System.out.println("This item cannot be used in combat.");
        }*/
        System.out.println("You have used " + item.getName() + ".");
        if (item.getType().equalsIgnoreCase("armor") || item.getType().equalsIgnoreCase("hand") || item.getType().equalsIgnoreCase("two hands") || item.getType().equalsIgnoreCase("footgear")) {
            addAttackPower(item.getAttackPower());
            removeFromInventory(item);
        }
        else if (item.getType().equalsIgnoreCase("go up a level")) {
            level++;
        }
        else if (item.getType().equalsIgnoreCase("item")) {
            //tempAPAdd(item.getAttackPower());
            addAttackPower(item.getAttackPower());
            removeFromInventory(item);
        }
        else {
            System.out.println("Item cannot be found in inventory.");
        }
    }

    //this method will search the user's inventory for a specific item and use it and remove it from the inventory
    public void searchInventory(String itemName) {
        for (int i = 0; i < inventory.size(); i++) {
            if (inventory.get(i).getName().equalsIgnoreCase(itemName.trim())) {
                useItem(inventory.get(i));
                return;
            }
        }
        System.out.println("Item not found in inventory.");
    }

    //this method will search the user's equipment for a specific item and remove it from the equipment
    public void discardEquipped(String itemName) {
        for (int i = 0; i < equipment.size(); i++) {
            if (equipment.get(i).getName().equalsIgnoreCase(itemName)) {
                equipment.remove(i);
                return;
            }
        }
        System.out.println("Item not found in equipment.");
    }

    //This method equips armor, two hand, hands, and footgear cards to the user.
    public void equip(Treasure item) {
        if (item.getType().equalsIgnoreCase("armor")) {
            //add armor to the user if applicable
             for (int i = 0; i < equipment.size(); i++) {
                if (equipment.get(i).getType().equalsIgnoreCase("armor")) {
                    System.out.println("You already have armor equipped. Would you like to replace it? (yes/no)");
                    String response = scanner.nextLine();
                    if (response.equalsIgnoreCase("yes")) {
                        equipment.remove(i);
                        equipment.add(item);
                        removeFromInventory(item);
                        System.out.println(item.getName() + "has been equipped.");
                    } else {
                        System.out.println("Armor not replaced.");
                    }
                }
                else {
                    equipment.add(item);
                    removeFromInventory(item);
                }
            }
        } else if (item.getType().equalsIgnoreCase("hand")) {
            //add hand to the user
            for (int i = 0; i < equipment.size(); i++) {
                if (equipment.get(i).getType().equalsIgnoreCase("hand") || equipment.get(i).getType().equalsIgnoreCase("two hands")) {
                    System.out.println("You already have a hand equipped. Would you like to replace it? (yes/no)");
                    String response = scanner.nextLine();
                    if (response.equalsIgnoreCase("yes")) {
                        equipment.remove(i);
                        equipment.add(item);
                        removeFromInventory(item);
                        System.out.println(item.getName() + " has been equipped.");
                    } else {
                        System.out.println("Hand not replaced.");
                    }
                }
                else {
                    equipment.add(item);
                    removeFromInventory(item);
                }
            }
        } else if (item.getType().equalsIgnoreCase("two hands")) {
            //add hands to the user
            for (int i = 0; i < equipment.size(); i++) {
                if (equipment.get(i).getType().equalsIgnoreCase("hand") || equipment.get(i).getType().equalsIgnoreCase("two hands")) {
                    System.out.println("You already have a hand equipped. Would you like to replace it? (yes/no)");
                    String response = scanner.nextLine();
                    if (response.equalsIgnoreCase("yes")) {
                        equipment.remove(i);
                        equipment.add(item);
                        removeFromInventory(item);
                        System.out.println(item.getName() + " has been equipped.");
                    } else {
                        System.out.println("Hands not replaced.");
                    }
                }
                else {
                    equipment.add(item);
                    removeFromInventory(item);
                }
            }
        } else if (item.getType().equalsIgnoreCase("footgear")) {
            //add footgear to the user
            for (int i = 0; i < equipment.size(); i++) {
                if (equipment.get(i).getType().equalsIgnoreCase("footgear")) {
                    System.out.println("You already have footgear equipped. Would you like to replace it? (yes/no)");
                    String response = scanner.nextLine();
                    if (response.equalsIgnoreCase("yes")) {
                        equipment.remove(i);
                        equipment.add(item);
                        removeFromInventory(item);
                        System.out.println(item.getName() + " has been equipped.");
                    } else {
                        System.out.println("Footgear not replaced.");
                    }
                }
                else {
                    equipment.add(item);
                    removeFromInventory(item);
                }
            }
        } else {
            System.out.println("This item cannot be equipped.");
        }
    }

    public void selectClass() {
        //display all classes and their passives
        System.out.println(" ");
        System.out.println("Classes * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");
        System.out.println("");
        System.out.println("1. Cleric - The divine warrior of a worshipped deity. Capable of using their faith and devotion to defend,");
        System.out.println("            smite, and call upon their god.");
        System.out.println("Divine Intervention:    Monsters are blinded by the holy light (it's a stagelight).");
        System.out.println("                        Monsters receive -1 to their attack power.");
        System.out.println("Holy Hands:   Your god has given you the power to smite your foes with a light touch.");
        System.out.println("              When you do not have a weapon equipped, you gain +2 to your attack power.");
        System.out.println(" ");
        System.out.println("2. Thief - A nimble and witty rogue who uses their cunning and agility to sneak around and get what they want.");
        System.out.println("Expertise:   A good fight comes with greater rewards, whether its given or stolen.");
        System.out.println("             You gain an extra treasure when looting the room.");
        System.out.println("Slippery Feet:   Your perspiration has left pools of sweat behind you.");
        System.out.println("                 You gain +1 to run away.");
        System.out.println(" ");
        System.out.println("3. Warrior - A tarnished warrior fighting to find their grace and maiden. They spew some nonsense about becoming");
        System.out.println("             some Elden Lord or something.");
        System.out.println("Hard-Hitter:   Years of training have given you the advantage in combat.");
        System.out.println("               You gain +1 to attack power.");
        System.out.println("Duelist:   You don't go down without a fight.");
        System.out.println("           You now win ties in combat.");
        System.out.println(" ");
        System.out.println("4. Wizard - A master of the mystic arts. Drawing power from the elements and arcane knowledge to cast spells.");
        System.out.println("Charm Spell:   Decades of academic study combined with your killer looks come in handy every now and then.");
        System.out.println("               While in combat, you may discard your entire inventory to automatically beat the monster. You obtain");
        System.out.println("               all treasures but do not level up.");
        System.out.println("Arcane Immunity:   Those sleepless nights studying Defense Against the Dark Arts have paid off.");
        System.out.println("                   Monsters with a level 10 or higher are -1 against you.");
        System.out.println("* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");
        System.out.println(" ");
        System.out.print("Select a class by entering the class you want to select. "); //ask for int (class)
        String classSelection = scanner.nextLine();
        setClass(classSelection); //this will be used to set the class of the user
    }

    public void selectRace() {
        //display all races and their advantages and disadvantages
        System.out.println(" ");
        System.out.println("Races * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");
        System.out.println(" ");
        System.out.println("1. Dwarf - Short but mighty and bulky, these creatures are very capable and skilled in their crafts.");
        System.out.println("Swag Bag:    Mighty though tiny, you're used to carrying a lot of stuff.");
        System.out.println("             You can carry 2 extra items in your inventory.");
        System.out.println("Worker of Metal:    You've got a knack for metalwork and smithing.");
        System.out.println("                    When using a big item, you receive a +1 to your attack power.");
        System.out.println(" ");
        System.out.println("2. Elf - Tall, slender, and graceful. They're known for their keen senses and beautiful features that catches the eye.");
        System.out.println("Pointy-Ears:   AHHHHHH! You-your ears are so pointy! So intimidating!");
        System.out.println("               Monsters receive -1 to their attack power.");
        System.out.println("Beautacious and Vivacious:   You are the most beautiful person in the world. (In the game at least.)");
        System.out.println("                             You gain +1 to run away.");
        System.out.println(" ");
        System.out.println("3. Halfling - Small humanoids who are known for their ability to sneak into small spaces. Plus,");
        System.out.println("              they got luck on their side. P.S. They are not hobbits.");
        System.out.println("My Brother-in-law is a Leprechaun:   You're naturally pretty lucky. But people really just think it's");
        System.out.println("                                     because of your brother-in-law.");
        System.out.println("                                     You have 2 attempts to run away from a monster.");
        System.out.println("Small body, Big Heart:   Empathy goes a long way. Tell your exes that.");
        System.out.println("                         You are immune to the bad stuff of Monsters level 1-10.");
        System.out.println("* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");
        System.out.println(" ");
        System.out.print("Select a race by entering the race you want to select. "); //ask for int (race)
        String raceSelection = scanner.nextLine();
        setRace(raceSelection); //this will be used to set the race of the user
    }
}