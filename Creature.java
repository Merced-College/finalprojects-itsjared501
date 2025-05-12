import java.util.LinkedList;
import java.util.Scanner;

public class Creature {
    private String name;
    private String userClass;
    private String userRace;
    private int level = 1;
    private int attackPower = 1;
    private int tempAttackPower = 0;
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

    public LinkedList<Treasure> getInventory() {
        return inventory;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public boolean setLivability(boolean livability) {
        isAlive = livability;
        return isAlive;
    }

    //This method accesses the user's inventory and displays all the items in it.
    public void accessInventory() {
        //for loop to display all the items in the inventory
        System.out.println("Inventory * * * * * * * * * * * * * * * * * * * * * * * * * * * * \n");
        if (inventory.size() == 0) {
            System.out.println("Inventory is empty. \n");
        }
        /*for (int i = 0; i < inventory.size(); i++) {
            System.out.println(inventory.get(i).toString() + "\n");
        }*/
        for (Treasure item : inventory) {
            System.out.println(item.toString() + "\n");
        }
        System.out.println("Inventory Size: " + inventorySizeCheck() +" * * * * * * * * * * * * * * * * * * * * * * * \n");
    }

    public void accessEquipment() {
        //for loop to display all the items in the equipment
        System.out.println("Equipment * * * * * * * * * * * * * * * * * * * * * * * * * * * * \n");
        if (equipment.size() == 0) {
            System.out.println("Equipment is empty. \n");
        }
        /*for (int i = 0; i < equipment.size(); i++) {
            System.out.println(equipment.get(i).toString() + "\n");
        }*/
        for (Treasure item : equipment) {
            System.out.println(item.toString() + "\n");
        }
        System.out.println("* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * \n");
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
            attackPower += 2;
            System.out.println("You are now level " + level + "!");
        } else {
            level = Math.min(level + 1, 10);
            attackPower++;
            System.out.println("You are now level " + level + "!");
        }
    }

    //this method decreases the user's level and attack power due to bad stuff
    public void decreaseLevel() {
        level--;
        attackPower--;
    }

    //This method icreases the user's attack power from items and will immediately deactivate after the fight.
    public void tempAPAdd(int power) {
        tempAttackPower = power;
        attackPower += tempAttackPower;
    }

    public void resetTempAP() {
        attackPower -= tempAttackPower;
        tempAttackPower = 0;
    }

    //This method will be used to increase the user's attack power from weapons, armor, and footgear.
    public void addAttackPower(int power) {
        attackPower += power;
        System.out.println("Attack power increased by " + power);
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
        System.out.println("\nYou have used " + item.getName() + ".");
        if (item.getType().equalsIgnoreCase("armor") || item.getType().equalsIgnoreCase("hand") || item.getType().equalsIgnoreCase("two hands") || item.getType().equalsIgnoreCase("footgear")) {
            addAttackPower(item.getAttackPower());
            removeFromInventory(item);
            equipment.add(item);
        }
        else if (item.getType().equalsIgnoreCase("go up a level")) {
            level++;
            attackPower++;
            removeFromInventory(item);
        }
        else if (item.getType().equalsIgnoreCase("item")) {
            tempAPAdd(item.getAttackPower());
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
            if (equipment.get(i).getName().equalsIgnoreCase(itemName.trim())) {
                Treasure item = equipment.get(i);
                attackPower -= item.getAttackPower();
                equipment.remove(i);
                System.out.println(item.getName() + " has been discarded.");
                return;
            }
        }
        System.out.println("Item not found in equipment.");
    }

    //This method equips armor, two hand, hands, and footgear cards to the user.
    /*public void equip(Treasure item) {
        if (item.getType().equalsIgnoreCase("armor")) {
            // Check if armor is already equipped
            for (int i = 0; i < equipment.size(); i++) {
                if (equipment.get(i).getType().equalsIgnoreCase("armor")) {
                    System.out.println("You already have armor equipped. Would you like to replace it? (yes/no)");
                    String response = scanner.nextLine();
                    if (response.equalsIgnoreCase("yes")) {
                        Treasure oldItem = equipment.get(i); // Remove the old armor
                        //attackPower -= oldItem.getAttackPower(); // Subtract the old item's attack power
                        discardEquipped(oldItem.getName());
                        equipment.add(item); // Add the new armor
                        attackPower += item.getAttackPower(); // Add the new item's attack power
                        removeFromInventory(item); // Remove the item from inventory
                        System.out.println(item.getName() + " has been equipped.");
                        return; // Exit after replacing the item
                    } else {
                        System.out.println("Armor not replaced.");
                        return; // Exit without replacing the item
                    }
                }
                else {
                    // If no footgear is equipped, add the new footgear
                    equipment.add(item);
                    attackPower += item.getAttackPower(); // Add the item's attack power
                    removeFromInventory(item);
                    System.out.println(item.getName() + " has been equipped.");
                }
            }
            System.out.println(item.getName() + " has been equipped.");
        } else if (item.getType().equalsIgnoreCase("hand")) {
            // Check if a hand item is already equipped
            for (int i = 0; i < equipment.size(); i++) {
                if (equipment.get(i).getType().equalsIgnoreCase("hand") || equipment.get(i).getType().equalsIgnoreCase("two hands")) {
                    System.out.println("You already have a hand equipped. Would you like to replace it? (yes/no)");
                    String response = scanner.nextLine();
                    if (response.equalsIgnoreCase("yes")) {
                        Treasure oldItem = equipment.get(i); // Remove the old hand item
                        discardEquipped(oldItem.getName());
                        //attackPower -= oldItem.getAttackPower(); // Subtract the old item's attack power
                        equipment.add(item); // Add the new hand item
                        attackPower += item.getAttackPower(); // Add the new item's attack power
                        removeFromInventory(item); // Remove the item from inventory
                        System.out.println(item.getName() + " has been equipped.");
                        return; // Exit after replacing the item
                    } else {
                        System.out.println("Hand not replaced.");
                        return; // Exit without replacing the item
                    }
                }
                else {
                    // If no footgear is equipped, add the new footgear
                    equipment.add(item);
                    attackPower += item.getAttackPower(); // Add the item's attack power
                    removeFromInventory(item);
                    System.out.println(item.getName() + " has been equipped.");
                }
            }
        } else if (item.getType().equalsIgnoreCase("two hands")) {
            // Check if a two-hand item is already equipped
            for (int i = 0; i < equipment.size(); i++) {
                if (equipment.get(i).getType().equalsIgnoreCase("hand") || equipment.get(i).getType().equalsIgnoreCase("two hands")) {
                    System.out.println("You already have a hand equipped. Would you like to replace it? (yes/no)");
                    String response = scanner.nextLine();
                    if (response.equalsIgnoreCase("yes")) {
                        Treasure oldItem = equipment.get(i); // Remove the old hand item
                        discardEquipped(oldItem.getName());                    
                        //attackPower -= oldItem.getAttackPower(); // Subtract the old item's attack power
                        equipment.add(item); // Add the new two-hand item
                        attackPower += item.getAttackPower(); // Add the new item's attack power
                        removeFromInventory(item); // Remove the item from inventory
                        System.out.println(item.getName() + " has been equipped.");
                        return; // Exit after replacing the item
                    } else {
                        System.out.println("Two-hand item not replaced.");
                        return; // Exit without replacing the item
                    }
                }
                else {
                    // If no footgear is equipped, add the new footgear
                    equipment.add(item);
                    attackPower += item.getAttackPower(); // Add the item's attack power
                    removeFromInventory(item);
                    System.out.println(item.getName() + " has been equipped.");
                }
            }
        } else if (item.getType().equalsIgnoreCase("footgear")) {
            // Check if footgear is already equipped
            for (int i = 0; i < equipment.size(); i++) {
                if (equipment.get(i).getType().equalsIgnoreCase("footgear")) {
                    System.out.println("You already have footgear equipped. Would you like to replace it? (yes/no)");
                    String response = scanner.nextLine();
                    if (response.equalsIgnoreCase("yes")) {
                        Treasure oldItem = equipment.get(i); // Remove the old footgear
                        attackPower -= oldItem.getAttackPower(); // Subtract the old item's attack power
                        discardEquipped(oldItem.getName());                       
                        //equipment.add(item); // Add the new footgear
                        attackPower += item.getAttackPower(); // Add the new item's attack power
                        removeFromInventory(item); // Remove the item from inventory
                        System.out.println(item.getName() + " has been equipped.");
                        return; // Exit after replacing the item
                    } else {
                        System.out.println("Footgear not replaced.");
                        return; // Exit without replacing the item
                    }
                }
                else {
                    // If no footgear is equipped, add the new footgear
                    equipment.add(item);
                    attackPower += item.getAttackPower(); // Add the item's attack power
                    removeFromInventory(item);
                    System.out.println(item.getName() + " has been equipped.");
                }
            }
        } else {
            System.out.println("This item cannot be equipped.");
        }
        equipment.add(item);
    } */

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