//Jared Lee
//April 29, 2025
//This is a text-based RPG game based on the card game Munchkin.
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
import java.util.Scanner;
import java.util.HashMap;
//import java.util.LinkedList;
import java.util.Map;
import java.util.Random;

//this class serves as the main class for the game, including main aspects of the game, inclduing intro, combat, and other game loops.
class Game {

    private static Map<String, Treasure> treasureMap = new HashMap<>(); //this will be used to create a hashmap of the treasure cards
    
    //csv reader for the treasure cards, method that reads the treasure from the csv file and creates a hashmap of the treasure cards
    public static Map<String, Treasure> readTreasureCards(String filePath) {
        Map<String, Treasure> treasureMap = new HashMap<>();

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
                    int attackPower = columns[2].trim().isEmpty() ? 0 : Integer.parseInt(columns[2].trim()); // Column 3: Attack power
                    String description = columns.length > 3 ? columns[3].trim() : ""; // Column 4: Description (if present)

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

    public static Treasure getRandomTreasure(Map<String, Treasure> treasureMap) {
        if (treasureMap.isEmpty()) {
            System.out.println("The treasure map is empty!");
            return null;
        }

        // Get a random key from the HashMap
        Object[] keys = treasureMap.keySet().toArray();
        Random random = new Random();
        String randomKey = (String) keys[random.nextInt(keys.length)];

        // Get the Treasure object associated with the random key
        Treasure selectedTreasure = treasureMap.get(randomKey);

        // Remove the selected Treasure from the HashMap
        treasureMap.remove(randomKey);

        // Return the selected Treasure
        return selectedTreasure;
    }

    public static void newTreasure() {
        Treasure randomTreasure = getRandomTreasure(treasureMap); //this will be used to get a random treasure card from the hashmap and remove it from the hashmap
        if (randomTreasure != null) {
            System.out.println("New Item: " + randomTreasure); //this will be used to display the random treasure card that was drawn        }
        }
    }

    //csv reader for the monster cards into an arraylist
    

    //This method serves as getting the user ready for the game, asking for name, gender, class, and race.
    public static void gameIntro() throws InterruptedException { //The thread.sleep method is used and pulled from copilot
        Scanner scanner = new Scanner(System.in);

        //Introduce the user to the game and ask for their information.
        System.out.println("(It is recommended you read the README file before playing this game.)"); //this will help ensure that the user has read over the rules of the game and understands how it works
        System.out.println(" "); //this will be used to create a space between the two lines
        System.out.println("Welcome to the epicness that is Munchkin! The game of pure uniqueness and chance, probability, and...yeah. You get the point."); //just a fun little intro to the game
        Thread.sleep(4000);//this will be used to pause the program for 3 seconds, allowing the user to read the previous text
        System.out.println("To start off your journey, tell me about yourself!");
        System.out.print("What is your name? ");
        String name = scanner.nextLine(); //this will be used to create the user object of the class Creature

        Creature user = new Creature(name); //uses the constructor to create the user object and set their name and gender

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

        scanner.close();
    }

    //This method serves as a way for the user to access their inventory (not yet implemented), equipment (not yet implemented), and continue to the next combat.


    //This method serves as the combat loop, where the user will be able to fight monsters and gain loot.


    //kickDownDoor method (draw a door card, either a monster or a curse)


    //lootOrLook method (if a curse was drawn, the user can either loot the room or look for a monster)


    //treasureLoot method (gives loot after defeating a monster or looting the room)


    //This method serves as the main method to run the game.
    public static void main(String[] args) throws InterruptedException {
        treasureMap = readTreasureCards("Treasure Cards(Sheet1) (1).csv"); //this will be used to read the treasure cards from the csv file

        gameIntro(); //call gameIntro method to start the game

        //do while loop to keep the game going until the user is level 10 or is dead
    }
}