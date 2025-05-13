import java.util.LinkedList;
import java.util.Scanner;

/**
 * Creature.java
 * @author Jared Lee
 * Object-class that represents the user in the game
 *      contains the user's name, class, race, level, attack power, inventory, and equipment
 */
public class Creature {
    private String name;
    private String userClass;
    private String userRace;
    private int level = 1;
    private int attackPower = 1;
    private int tempAttackPower = 0;
    private boolean isAlive = true;

    private boolean clericBonus = false; //cleric's +2 bonus to AP passive
    
    public boolean isClericBonusApplied() { //getter for cleric bonus
        return clericBonus;
    }

    public void setClericBonus(boolean applied) { //setter for cleric bonus
        clericBonus = applied;
    }

    private LinkedList<Treasure> inventory = new LinkedList<Treasure>();
    private LinkedList<Treasure> equipment = new LinkedList<Treasure>();
    
    Scanner scanner = new Scanner(System.in);
    
    
    //Constructor
    public Creature(String name) {
        this.name = name;
        //this.userGender = userGender;
    }

    //The getters and setters
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

    public LinkedList<Treasure> getEquipment() {
        return equipment;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public boolean setLivability(boolean livability) {
        isAlive = livability;
        return isAlive;
    }

    //sets the class to the user and applies certain passives
    public void setClass (String chosenClass) {
        if (chosenClass.equalsIgnoreCase("cleric")) {
            userClass = "Cleric";
        } 
        else if (chosenClass.equalsIgnoreCase("thief")) {
            userClass = "Thief";
        } 
        else if (chosenClass.equalsIgnoreCase("warrior")) {
            userClass = "Warrior";
            attackPower++; //warriors get a permanent +1 to AP
        } 
        else if (chosenClass.equalsIgnoreCase("wizard")) {
            userClass = "Wizard";
        }
        else {
            System.out.println("I believe you misheard me. Please select a class from the list above.");
            selectClass(); //user selects the class again
        }
    }

    //sets the race to the user and applies certain passives
    public void setRace (String chosenRace) {
        if (chosenRace.equalsIgnoreCase("dwarf")) {
            userRace = "Dwarf";
            attackPower++; //dwarves get a permanent +1 to AP
        } 
        else if (chosenRace.equalsIgnoreCase("elf")) {
            userRace = "Elven";
        } 
        else if (chosenRace.equalsIgnoreCase("halfling")) {
            userRace = "Halfling";
        }
        else {
            System.out.println("I believe you misheard me. Please select a race from the list above.");
            selectRace(); //user selects race again
        }
    }

    //This method accesses the user's inventory and displays all the items in it
    public void accessInventory() {
        //for loop to display all the items in the inventory
        System.out.println("Inventory * * * * * * * * * * * * * * * * * * * * * * * * * * * * \n");
        if (inventory.size() == 0) {//if there isn't anything in the inventory
            System.out.println("Inventory is empty. \n");
        }
        for (Treasure item : inventory) { //enhanced for loop to print each item
            System.out.println(item.toString() + "\n");
        }
        System.out.println("Inventory Size: " + inventorySizeCheck() +" * * * * * * * * * * * * * * * * * * * * * * * \n");
    }

    //this method accesses the user's equipment and displays all the items equipped
    public void accessEquipment() {
        //for loop to display all the items in the equipment
        System.out.println("Equipment * * * * * * * * * * * * * * * * * * * * * * * * * * * * \n");
        if (equipment.size() == 0) { //if there isn't anything in the equipment
            System.out.println("Equipment is empty. \n");
        }
        for (Treasure item : equipment) { //enhanced for loop to print each item
            System.out.println(item.toString() + "\n");
        }
        System.out.println("* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * \n");
    }

    //This method increases the user's level when they defeat a monster.
    public void levelUp(Monster monster) {
        if (monster.getLevel() >= 14) { //if the monster is level 14+, the user levels up twice
            level = Math.min(level + 2, 10); //ensures the user won't go past level 10
            attackPower += 2; //attack power is also increased by two because it increments with level
            System.out.println("You are now level " + level + "!\n");
        } else { //if the monster is level 12 or less, the user levels up once
            level = Math.min(level + 1, 10); //ensures the user won't go past level 10
            attackPower++; //attack power is also increased by two because it increments with level
            System.out.println("You are now level " + level + "!\n");
        }
    }

    //this method decreases the user's level and attack power due to bad stuff
    public void decreaseLevel() {
        level--; //level decrements by 1
        attackPower--; //AP decrements by 1
    }

    //This method icreases the user's attack power from items and will immediately deactivate after the fight.
    public void tempAPAdd(int power) {
        tempAttackPower = power; //temporarily make the parameter the power increase
        attackPower += tempAttackPower; //temporarily increase user's AP
    }

    //this method will reset the temporary AP so that it goes away after combat
    public void resetTempAP() {
        attackPower -= tempAttackPower; //get rid of temporary attack power
        tempAttackPower = 0; //temporary attack power is reset to zero so that the temporary attack power doesn't stack
    }

    //This method will be used to increase the user's attack power from weapons, armor, and footgear.
    public void addAttackPower(int power) {
        attackPower += power; //increases AP based on modifier applied by weapons, armor, and footgear
        System.out.println("Attack power increased by " + power);
    }

    //This method is specifically used to decrease the user's AP when they're a cleric and they have something equipped.
    public void removeAttackPower(int power) {
        attackPower -= power; //decreases AP based on modifier applied by weapons, armor, and footgear
        System.out.println("Attack power decreased by " + power);
    }

    //This method ensures that the inventory size is checked before adding items to the inventory.
    public int inventorySizeCheck() {
        if (userRace.equalsIgnoreCase("Dwarf")) { //dwarf passive gives them a bigger inventory
            return 12;
        }
        else { //regular inventory size
            return 10;
        }
    }

    //This method adds treasures gained from the fight to the user's inventory.
    public void addToInventory(Treasure newItem) {
        if (inventory.size() >= inventorySizeCheck()) { //ensures that the item can fit in the inventory
            System.out.println("Inventory is full. Cannot add more items.");
            return;
        }
        else { //add new item to inventory
            inventory.add(newItem);
        }
    }

    //This method removes treasures from the user's inventory.
    public void removeFromInventory(Treasure item) {
        if (inventory.contains(item)) { //if the inventory contains the item
            inventory.remove(item); //remove it
        } else { //otherwise it cannot be found
            System.out.println("Item not found in inventory.");
        }
    }

    //this method will search the user's inventory for a specific item and use it and remove it from the inventory
    public void searchInventory(String itemName) {
        for (int i = 0; i < inventory.size(); i++) { //searches inventory for chosen item
            if (inventory.get(i).getName().equalsIgnoreCase(itemName.trim())) {
                useItem(inventory.get(i)); //uses and removes item
                return;
            }
        }
        System.out.println("Item not found in inventory.");
    }

    //this method will search the user's equipment for a specific item and remove it from the equipment
    public void discardEquipped(String itemName) {
        for (int i = 0; i < equipment.size(); i++) { //searches the equipped items for specific item
            if (equipment.get(i).getName().equalsIgnoreCase(itemName.trim())) { //if found
                Treasure item = equipment.get(i); //the item gets specified
                attackPower -= item.getAttackPower(); //remove the specified item's AP bonus
                equipment.remove(i); //discard item
                System.out.println(item.getName() + " has been discarded.");
                return;
            }
        }
        //if not found
        System.out.println("Item not found in equipment.");
    }

    /**
     * the method should iterate through the inventory and find the item with the matching name; 
     *      if found, then use. else, cannot find
     * Precondition: searchInventory() has been called and has found the item
     * Postcondition: the item has been used and removed from the inventory
     *      the item's bonuses has been applied to the user
     * @param item
     *      the item to be used
     */
    public void useItem(Treasure item) {
        System.out.println("\nYou have used " + item.getName() + ".");
        //if item is an equipment item, transfer it to equipment 
        if (item.getType().equalsIgnoreCase("armor") || item.getType().equalsIgnoreCase("hand") || item.getType().equalsIgnoreCase("two hands") || item.getType().equalsIgnoreCase("footgear")) {
            addAttackPower(item.getAttackPower()); //apply AP bonus
            removeFromInventory(item); //removes item from inventory
            equipment.add(item); //adds it to equipment
        }
        //if item is a GUAL, make user level up
        else if (item.getType().equalsIgnoreCase("go up a level")) {
            level++; //level up
            attackPower++; //causes AP to increase as well
            removeFromInventory(item); //removes item from inventory
        }
        //if item is a combat item
        else if (item.getType().equalsIgnoreCase("item")) {
            //if the user is a wizard, they get +2 bonus to magical items
            if (getUserClass().equalsIgnoreCase("Wizard")) {
                if (item.getName().equalsIgnoreCase("Cotion of Ponfusion") || item.getName().equalsIgnoreCase("Flaming Poison Potion") ||
                    item.getName().equalsIgnoreCase("Potion of Halitosis") || item.getName().equalsIgnoreCase("Potion of Idiotic Bravery") || 
                    item.getName().equalsIgnoreCase("Sleep Potion") || item.getName().equalsIgnoreCase("Spell Scroll: Mirage") ||
                    item.getName().equalsIgnoreCase("Electric Radioactive Acid Potion") || item.getName().equalsIgnoreCase("Freezing Explosive Potion") ||
                    item.getName().equalsIgnoreCase("Friendship Potion") || item.getName().equalsIgnoreCase("Spell Scroll: Flame Wall") ||
                    item.getName().equalsIgnoreCase("Potion of backstabbery") || item.getName().equalsIgnoreCase("Magic Missile")) {
                    tempAPAdd(item.getAttackPower() + 2); //item temporarily buffs user + wizard bonus
                    removeFromInventory(item); //removes item from inventory
                    return; //return so that the user doesn't get buffed again
                }
            }
            //otherwise, temporarily buff user normally
            tempAPAdd(item.getAttackPower()); //item temporarily buffs user
            removeFromInventory(item); //removes item from inventory
        }
        else {
            System.out.println("Item cannot be used.");
        }
    }

    //called in gameIntro() to allow user to choose a class
    public void selectClass() {
        //display all classes and their passives
        System.out.println(" ");
        System.out.println("CLASSES * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");
        System.out.println("");
        System.out.println("1. CLERIC - The divine warrior of a worshipped deity. Capable of using their faith and devotion to defend,");
        System.out.println("            smite, and call upon their god.");
        System.out.println("Divine Intervention:    Monsters are blinded by the holy light (it's a stagelight).");
        System.out.println("                        Monsters receive -1 to their attack power.");
        System.out.println("Holy Hands:   Your god has given you the power to smite your foes with a light touch.");
        System.out.println("              When you do not have anything equipped, you gain +2 to your attack power.");
        System.out.println(" ");
        System.out.println("2. THIEF - A nimble and witty rogue who uses their cunning and agility to sneak around and get what they want.");
        System.out.println("Expertise:   A good fight comes with greater rewards, whether its given or stolen.");
        System.out.println("             You gain an extra treasure when looting the room.");
        System.out.println("Slippery Feet:   Your perspiration has left pools of sweat behind you.");
        System.out.println("                 You gain +1 to run away.");
        System.out.println(" ");
        System.out.println("3. WARRIOR - A tarnished warrior fighting to find their grace and maiden. They spew some nonsense about becoming");
        System.out.println("             some Elden Lord or something.");
        System.out.println("Hard-Hitter:   Years of training have given you the advantage in combat.");
        System.out.println("               You gain +1 to attack power.");
        System.out.println("Duelist:   You don't go down without a fight.");
        System.out.println("           You now win ties in combat.");
        System.out.println(" ");
        System.out.println("4. WIZARD - A master of the mystic arts. Drawing power from the elements and arcane knowledge to cast spells.");
        System.out.println("Charm Spell:   Decades of academic study combined with your killer looks come in handy every now and then.");
        System.out.println("               Gain an extra +2 when using magical items.");
        System.out.println("Arcane Immunity:   Those sleepless nights studying Defense Against the Dark Arts have paid off.");
        System.out.println("                   Monsters receive -1 to their attack power.");
        System.out.println("* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");
        System.out.println(" ");
        System.out.print("Select a class by entering the class you want to select. "); 
        String classSelection = scanner.nextLine(); //user selects one
        setClass(classSelection); //this will be used to set the class of the user
    }

    //called in gameIntro() to allow user to choose a race
    public void selectRace() {
        //display all races and their advantages and disadvantages
        System.out.println(" ");
        System.out.println("RACES * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");
        System.out.println(" ");
        System.out.println("1. DWARF - Short but mighty and bulky, these creatures are very capable and skilled in their crafts.");
        System.out.println("Swag Bag:    Mighty though tiny, you're used to carrying a lot of stuff.");
        System.out.println("             You can carry 2 extra items in your inventory.");
        System.out.println("Worker of Metal:    You've got a knack for metalwork and smithing.");
        System.out.println("                    You gain +1 to attack power.");
        System.out.println(" ");
        System.out.println("2. ELF - Tall, slender, and graceful. They're known for their keen senses and beautiful features that catches the eye.");
        System.out.println("Pointy-Ears:   AHHHHHH! You-your ears are so pointy! So intimidating!");
        System.out.println("               Monsters receive -1 to their level.");
        System.out.println("Beautacious and Vivacious:   You are the most beautiful person in the world. (In the game at least.)");
        System.out.println("                             You gain +1 to run away.");
        System.out.println(" ");
        System.out.println("3. HALFLING - Small humanoids who are known for their ability to sneak into small spaces. Plus,");
        System.out.println("              they got luck on their side. P.S. They are not hobbits.");
        System.out.println("My Brother-in-law is a Leprechaun:   You're naturally pretty lucky. But people really just think it's");
        System.out.println("                                     because of your brother-in-law.");
        System.out.println("                                     You gain +1 to run away.");
        System.out.println("Small body, Big Heart:   Empathy goes a long way. Tell your exes that.");
        System.out.println("                         You are immune to the bad stuff of Monsters level 1-10.");
        System.out.println("* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");
        System.out.println(" ");
        System.out.print("Select a race by entering the race you want to select. "); 
        String raceSelection = scanner.nextLine(); //user selects one
        setRace(raceSelection); //this will be used to set the race of the user
    }
}