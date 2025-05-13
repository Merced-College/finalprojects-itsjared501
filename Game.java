import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Game.java
 * @author Jared Lee
 * @since 2025-04-29
 * @version 1.0
 * Main class for the Munchkin game
 * A test-based RPG game based on the card game Munchkin
 */
//this class serves as the main class for the game, including main aspects of the game, inclduing intro, combat, and other game loops.
class Game {
    private static Scanner scanner = new Scanner(System.in); //this will be used to create a scanner object to read user input
    private static Map<String, Treasure> treasureMap = new HashMap<>(); //this will be used to create a hashmap of the treasure cards
    private static Map<String, Monster> monsterMap = new HashMap<>(); //this will be used to create a hashmap of the monster cards
    private static Creature user; //this will be used to create the user object of the class Creature

    //csv reader for the treasure cards, method that reads the treasure from the csv file and creates a hashmap of the treasure cards
    public static Map<String, Treasure> readTreasureCards(String filePath) {

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = br.readLine()) != null) {
                // Skip empty lines
                if (line.trim().isEmpty()) {
                    continue;
                }

                // Split the line into columns
                String[] columns = line.split(",", -1); // -1 ensures empty columns are preserved

                // Parse the treasure details
                try {
                    String section = columns[0].trim(); // Column 1: Section (e.g., "Armor")
                    String name = columns[1].trim();    // Column 2: Name (unique key)
                    int attackPower = Integer.parseInt(columns[2].trim()); // Column 3: Attack power
                    String description = columns[3].trim(); // Column 4: Description (if present)

                    // Create a Treasure object
                    Treasure treasure = new Treasure(section, name, attackPower, description);

                    // Add the Treasure object to the HashMap with the name as the key
                    treasureMap.put(name, treasure);
                } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                    System.err.println("Invalid line format: " + line);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }

        return treasureMap;
    }

    //csv reader for the monster cards into a hashmap
    public static Map<String, Monster> readMonsterCards(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = br.readLine()) != null) {
                // Skip empty lines
                if (line.trim().isEmpty()) {
                    continue;
                }

                // Split the line into columns
                String[] columns = line.split(",", -1); // -1 ensures empty columns are preserved

                // Parse the monster details
                try {
                    int level = Integer.parseInt(columns[0].trim()); // Column 2: Level
                    String name = columns[1].trim();    // Column 1: Name (unique key)
                    String description = columns[2].trim(); // Column 3: Description
                    String badStuff = columns[3].trim(); // Column 4: Bad Stuff (if present)

                    // Create a Monster object
                    Monster monster = new Monster(name, level, description, badStuff);

                    // Add the Monster object to the HashMap with the name as the key
                    monsterMap.put(name, monster);
                } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                    System.err.println("Invalid line format: " + line);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }

        return monsterMap;
    }


    /**
     * Method to introduce the user to the game, asking for their name, class, and race
     * Postcondition: user object is created with the name and the user has selected their clas and race.
     * @throws InterruptedException
     *     used for the thread.sleep method
     */
    public static void gameIntro() throws InterruptedException { //The thread.sleep method is used and pulled from copilot

        //Introduce the user to the game and ask for their information.
        System.out.println("(It is recommended you read the README file before playing this game.)"); //this will help ensure that the user has read over the rules of the game and understands how it works
        System.out.println(" "); //this will be used to create a space between the two lines
        System.out.println("Welcome to the epicness that is Munchkin! The game of pure uniqueness and chance, probability, and...yeah. You get the point."); //just a fun little intro to the game
        Thread.sleep(4000);//this will be used to pause the program for 3 seconds, allowing the user to read the previous text
        System.out.println("To start off your journey, tell me about yourself!");
        System.out.print("What is your name? ");
        String name = scanner.nextLine(); //this will be used to create the user object of the class Creature

        user = new Creature(name); //uses the constructor to create the user object and set their name and gender

        //bring up the selectName method to allow the user to select one
        System.out.println("Wow, " + user.getName() + "! What an eh name... It's no Godfrey the Slayer of Fae! Or Beyonce the Queen Bee! Now, what class are you?");
        Thread.sleep(4000); //this will be used to pause the program for 4 seconds, allowing the user to read the previous text
        user.selectClass(); //the method is called to select the class of the user

        //bring up the selectRace method to allow the user to select one
        System.out.println("I see I see... how interesting. Lastly what race are you? (I know, sensitive topic, but I need to know.)");
        Thread.sleep(4000); //this will be used to pause the program for 4 seconds, allowing the user to read the previous text
        user.selectRace(); //the method is called to select the race of the user

        //finalize the user name, race, and class
        System.out.println("Fascinating! Well it's nice to meet you, " + user.getName() + " the " + user.getUserRace() + " " + user.getUserClass() + "!");
        Thread.sleep(2000); //this will be used to pause the program for 2 seconds, allowing the user to read the previous text
        System.out.print("Now, are you ready to begin your journey into the unknown oh faithful " + user.getUserClass() + "? Y/N: ");
        String ready = scanner.nextLine(); //this will be used to get the user input of whether they are ready or not
        if (ready.equalsIgnoreCase("Y")) {
            //method to begin the game
        }
        else if (ready.equalsIgnoreCase("N")) {
            System.out.println("Well that's too bad. You're already here, so here's the damn game.");
            //method to begin the game
        }

    }

    //the method is used when the user reaches level 10 or dies
    public static void gameOutro() {
        if (user.getLevel() == 10) { //when level 10
            System.out.println("\nYou have reached level 10! You have won Munchkin!"); 
            System.out.println("Thank you " + user.getName() + ". Your abilities have helped rid of terrible creatures in this world."); 
            System.out.println("May your future adventures prove fruitful. The world is far safer with you here to protect it!\n"); 
        }
        else if (!user.isAlive()) { // when dead
            System.out.println("\nDeath has laid their gaze upon your being. You have failed to complete your journey.");
            System.out.println("You have fought well " + user.getName() + ". But not well enough.\n");

        }
    }

    /**
     * the method will pull a random treasure object from the treasureMap
     * precondition: the treasureMap exists
     * postcondition: user gained a treasure
     * @param treasureMap
     * @return selectedTreasure
     *     the treasure object that was selected
     */
    public static Treasure getRandomTreasure(Map<String, Treasure> treasureMap) {
        //if the map is empty, then display that no treasures are available
        if (treasureMap.isEmpty()) {
            System.out.println("There are no more treasures!");
            return null;
        }

        // Get a random key from the HashMap
        Object[] keys = treasureMap.keySet().toArray(); //creates an array based on the keys from the treasureMap
        Random random = new Random();
        String randomKey = (String) keys[random.nextInt(keys.length)]; //a random key based on the length of the array previously created

        // Get the Treasure object associated with the random key
        Treasure selectedTreasure = treasureMap.get(randomKey);

        // Remove the selected Treasure from the HashMap
        treasureMap.remove(randomKey);

        // Return the selected Treasure
        return selectedTreasure;
    }

    /**
     * the method will pull a random monster object from the monsterMap
     * precondition: the monsterMap exists
     * postcondition: user fights a monster
     * @param monsterMap
     * @return selectedMonster
     *     the monster object that was selected
     */
    public static Monster getRandomMonster(Map<String, Monster> monsterMap) {
        //if the map is empty, then display that no monsters are available
        if (monsterMap.isEmpty()) {
            System.out.println("There are no more monsters in the dungeon!");
            return null;
        } 

        // Get a random key from the HashMap
        Object[] keys = monsterMap.keySet().toArray(); //creates an array based on the keys from the treasureMap
        Random random = new Random();
        String randomKey = (String) keys[random.nextInt(keys.length)]; //a random key based on the length of the array previously created

        // Get the Monster object associated with the random key
        Monster selectedMonster = monsterMap.get(randomKey);

        // Remove the selected Monster from the HashMap
        monsterMap.remove(randomKey);

        // Return the selected Monster
        return selectedMonster;
    }

    //the method will give the user a new item(s) following their victory; the amount of treasures based on the monsters level
    public static void newTreasure(Monster monster) {
        //when the monster is level 14 and above, give the user two treasures
        if (monster.getLevel() >= 14) {
            Treasure item1 = getRandomTreasure(treasureMap); //this will be used to get a random treasure card from the hashmap and remove it from the hashmap
            if (item1 != null) {
                System.out.println("New Item: " + item1 + "\n"); //this will be used to display the random treasure card that was drawn
                user.addToInventory(item1); //this will be used to add the item to the user's inventory
                treasureMap.remove(item1.getName()); //removes the item from the treasureMap
            }
            Treasure item2 = getRandomTreasure(treasureMap); //this will be used to get a random treasure card from the hashmap and remove it from the hashmap
            if (item2 != null) {
                System.out.println("New Item: " + item2); //this will be used to display the random treasure card that was drawn
                user.addToInventory(item2); //this will be used to add the item to the user's inventory
                treasureMap.remove(item2.getName()); //removes the item from the treasureMap
            }
        }
        //when the monster is anything lower, then the user only receives one treasure
        else {
            Treasure item1 = getRandomTreasure(treasureMap); //this will be used to get a random treasure card from the hashmap and remove it from the hashmap
            if (item1 != null) {
                System.out.println("New Item: " + item1); //this will be used to display the random treasure card that was drawn
                user.addToInventory(item1); //this will be used to add the item to the user's inventory
                treasureMap.remove(item1.getName()); //removes the item from the treasureMap
            }   
        }
        //when the user is a theif, they gain an extra treasure due to class passive
        if (user.getUserClass().equalsIgnoreCase("Thief")) {
            Treasure thiefBonus = getRandomTreasure(treasureMap); //this will be used to get a random treasure card from the hashmap and remove it from the hashmap
            if (thiefBonus != null) {
                System.out.println("\nYour sly hands picked up an extra treasure!");
                System.out.println("New Item: " + thiefBonus); //this will be used to display the random treasure card that was drawn
                user.addToInventory(thiefBonus); //this will be used to add the item to the user's inventory
                treasureMap.remove(thiefBonus.getName()); //removes the item from the treasureMap
            }   
        }
    }    

    //the method gets a new monster for the user to fight
    public static Monster newMonster() {
        Monster randomMonster = getRandomMonster(monsterMap); //this will be used to get a random monster card from the hashmap and remove it from the hashmap
        //if the user is an elf, cleric, or wizard, the monster receives a -1 to their level
        if (user.getUserRace().equalsIgnoreCase("Elf") || user.getUserClass().equalsIgnoreCase("Cleric") || user.getUserClass().equalsIgnoreCase("Wizard") && randomMonster.getLevel() > 1) {
            randomMonster.setLevel(randomMonster.getLevel() - 1);
        }
        return randomMonster; //this will be used to return the random monster card that was drawn
    }

    //this method gets random items for the user in the beginning of the game
    public static void starterItems() {
        Treasure starterItem1 = getRandomTreasure(treasureMap);
        Treasure starterItem2 = getRandomTreasure(treasureMap);
        Treasure starterItem3 = getRandomTreasure(treasureMap);
        user.addToInventory(starterItem1);
        user.addToInventory(starterItem2);
        user.addToInventory(starterItem3);
        System.out.println("\nStarter items added to inventory!");
    }

    //This method serves as a way for the user to access their inventory (not yet implemented), equipment (not yet implemented), and continue to the next combat.
    public static void userTravelChoose() {
        user.resetTempAP(); //if items were used in the combat prior, its effects are removed
        while (true) {
        System.out.println("\nWhat would you like to do? Your level: " + user.getLevel() + " Your attack power: " + user.getAttackPower() + "\n(1) Check Inventory\n(2) Check Equipment\n(3) Continue to Combat");
        int choice = scanner.nextInt(); //this will be used to get the user input of what they want to do
        switch (choice) {
            case 1: //check inventory
                user.accessInventory(); //this will be used to call the method to check the inventory
                System.out.println("P.S. You cannot use items (not including weapons, armor or footgear) until you are in combat.");
                useItemChoice();
                break; //goes back to choice
            case 2: //check equipment
                user.accessEquipment(); //this will be used to call the method to check the equipment
                discardEquippedChoice();//after the equipment is displayed, the user will be asked if they want to discard an item
                break; //goes back to choice
            case 3: //continue to combat
                kickDownDoor();
                return; //get out of method
            default: //if anything else is typed
                System.out.println("Can you hear me? I said 1, 2, or 3. Not whatever the hell you just typed.");
                userTravelChoose(); //this will be used to call the method again if the user inputs an invalid choice
            }
        }
    }

    //This method serves as a way to fight the monster, access the inventory, and run away from the monster.
    public static void userFightChoose(Monster monster) {
        while (true) {
            System.out.println("\nQuick! What do you do? Your level: " + user.getLevel() + " Your attack power: " + user.getAttackPower() + "\n(1) Fight the Monster\n(2) Check Inventory\n(3) Try to Run Away");
            int choice = scanner.nextInt(); //this will be used to get the user input of what they want to do
            switch (choice) {
                case 1: //fight the monster
                    combat(monster); //this will be used to call the method to fight the monster
                    return; //get out of method
                case 2: //check inventory
                    user.accessInventory(); //this will be used to call the method to check the inventory
                    useItemChoice(); //ask what item to use
                    break; //goes back to choice
                case 3: //try to run away
                    runAway(monster); //this will be used to call the method to run away from the monster
                    return; //get out of method
                default:
                    System.out.println("Are you serious?! We cannot afford mistakes like this! 1, 2, or 3!!");
                    userFightChoose(monster); //this will be used to call the method again if the user inputs an invalid choice
            }
        }
    }


    /**
     * This algorithm will be used when the user decides to run away from a monster, determining if they are successful.
     * Precondition: the user is in combat with a monster and has chosen to run away
     * Postcondition: the user has either ran away successfully or now faces bad stuff
     * @param monster
     * @return
     *   true if the user ran away successfully, false if they failed
     */
    public static boolean runAway(Monster monster) {
        Random random = new Random();
        int bonus = 0; //a bonus that only applies under certain circumstances
        //if the user is an elf, halfling, or thief, they get a +1 to their roll
        if (user.getUserRace().equalsIgnoreCase("Elf") || user.getUserRace().equalsIgnoreCase("Halfling") || user.getUserClass().equalsIgnoreCase("Thief")) { 
            bonus++; //add 1 to bonus
        }
        //generate a die roll from 1-6
        int roll = random.nextInt(6)+1+bonus; //this will be used to roll a die 1-6 and will add the bonus if it applies
        //the player needs to roll a 4 or higher to successfully run away from the monster
        if (user.getLevel() <= 4 && monster.getLevel() >= 16) { //monsters level 16+ will NOT pursue the user if they're level 4 or below
            System.out.println("The monster found you weak and did not pursue you.");
            return true; //ran away
        }
        else if (roll < 4) { //the roll failed
            System.out.println("You rolled a " + roll + "! You failed to run away from the monster!");
            monster.badStuffOccurs(user);
            return false; //run away fails
        } else { //the roll succeeded
            System.out.println("You rolled a " + roll + "! You have successfully ran away from the monster!");
            System.out.println("You have not leveled up; you sleep the night in. Waking up to face the same door again.");
            return true; //ran away
        }
    }

    //this method makes the user face a monster and go into combat
    public static void kickDownDoor() {
        System.out.println("\nYou kick down the door in front of you and find a monster!");
        Monster monster = newMonster(); //this will be used to get a random monster card from the hashmap and remove it from the hashmap
        if (monster == null) {//if no monster was returned, then no monster is found
            System.out.println("No monster found!"); //this will be used to display the random monster that was drawn
            return; //this will be used to return if there is no monster   
        }
        System.out.println("\n" + monster.toString()); //this will be used to display the random monster that was drawn
        userFightChoose(monster); //this will be used to call the method to fight the monster
    }

    //this method compares the user level to the monster level and determines if the user wins or loses
    public static void combat(Monster monster) {
        //if the user is a warrior, they just need to match the monster's level to defeat it
        if (user.getUserClass().equalsIgnoreCase("Warrior")) {
            if (user.getAttackPower() >= monster.getLevel()) { //the warrior's AP has to be greater than or equal to the monster's level to beat it
                System.out.println("\nYou have defeated the " + monster.getName() + "!");
                lootAndLevel(monster); //this will be used to call the method to give loot after defeating a monster
            } else {
                System.out.println("You have been defeated by the " + monster.getName() + "!");
                monster.badStuffOccurs(user);
            }
            return; //will return so the combat doesn't occur twice
        }
        //under normal circumstances, the user's AP must be greater than the monster's level
        if (user.getAttackPower() > monster.getLevel()) {
            System.out.println("\nYou have defeated the " + monster.getName() + "!");
            lootAndLevel(monster); //this will be used to call the method to give loot after defeating a monster
        } else {
            System.out.println("You have been defeated by the " + monster.getName() + "!");
            monster.badStuffOccurs(user); //bad stuff will happen to the user
        }
    }

    //defeatedMonster method (gives loot after defeating a monster or looting the room)
    public static void lootAndLevel(Monster monster) {
        user.levelUp(monster); //this will be used to level up the user
        newTreasure(monster); //this will be used to get a random treasure card from the hashmap and remove it from the hashmap
    }

    //this method will ask the user if they want use any items in their inventory after they access their inventory
    public static void useItemChoice() {
        System.out.println("Would you like to use any items in your inventory? Y/N: ");
        scanner.nextLine(); //used to help read in the choice
        String choice = scanner.nextLine(); //this will be used to get the user input of what they want to do
        if (choice.equalsIgnoreCase("Y")) {
            System.out.println("What item would you like to use? (type in full name of item)"); //this will be used to ask the user what item they want to use
            String item = scanner.nextLine().trim(); //this will be used to get the user input of what item they want to use
            user.searchInventory(item); //this will be used to call the method to search the inventory for the possible item
        } else if (choice.equalsIgnoreCase("N")) {
            System.out.println("Okay, then onward!");
        } 
    }

    //this method will ask the user if they want to discard any of their equipment after they access their equipment
    public static void discardEquippedChoice() {
        System.out.println("Would you like to discard any of your equipment? Y/N: ");
        scanner.nextLine(); //used to help read in the choice
        String choice = scanner.nextLine(); //this will be used to get the user input of what they want to do
        if (choice.equalsIgnoreCase("Y")) {
            System.out.println("What item would you like to discard? (type in full name of item)"); //this will be used to ask the user what item they want to discard
            String item = scanner.nextLine().trim(); //this will be used to get the user input of what item they want to discard
            user.discardEquipped(item); //this will be used to call the method to discard the equipped item
        } else if (choice.equalsIgnoreCase("N")) {
            System.out.println("Okay, then onward!");
        }
    }

    /**
     * Main method to run the game
     * @param args
     * @throws InterruptedException
     *   used for the thread.sleep method that is used in the gameIntro method
     */
    public static void main(String[] args) throws InterruptedException {
        treasureMap = readTreasureCards("Treasure Cards(Sheet1) (1).csv"); //this will be used to read the treasure cards from the csv file
        monsterMap = readMonsterCards("Monster Cards(Sheet1).csv"); //this will be used to read the monster cards from the csv file
        
        gameIntro(); //call gameIntro method to start the game
        user.setLivability(true);
        starterItems(); //gives the user items in the beginning of the game
        
        //do-while loop to keep the game going until the user is level 10 or is dead
        do {
            //if the user is a cleric and has nothing equipped, they gain a +2 AP bonus, otherwise, the bonus doesn't apply
            if (user.getUserClass().equalsIgnoreCase("Cleric")) {
                if (user.getEquipment().isEmpty() && !user.isClericBonusApplied()) { //if equipment is empty
                    user.addAttackPower(2);
                    user.setClericBonus(true);
                }
                else if (!user.getEquipment().isEmpty() && user.isClericBonusApplied()) { //if there is something in equipment
                    user.removeAttackPower(2);
                    user.setClericBonus(false);
                }
            }
            userTravelChoose(); //this will be used to call the method to check the inventory, equipment, or continue to combat
        }
        while (user.isAlive() && user.getLevel() < 10); //this will be used to keep the game going until the user is level 10 or is dead
        
        gameOutro(); //the user has reached level 10 or has died

        scanner.close();
    }
    
    
}