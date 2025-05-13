import java.util.Random;

/**
 * Monster.java
 * @author Jared Lee
 * Object-class that represents the monsters in the game
 *      contains the monster's name, level, description, and bad stuff
 */
public class Monster {
    private String name;
    private int level;
    private String description;
    private String badStuff;

    //constructor
    public Monster(String name, int level, String description, String badStuff) {
        this.name = name;
        this.level = level;
        this.description = description;
        this.badStuff = badStuff;
    }

    /**
     * The method serves as a way to apply the bad stuff of the monster in question to the user
     * precondition: the user failed to run away from the monster in combat
     * postcondition: the user faces the consequences of the monster's bad stuff
     * @param user
     * @return bad stuff
     *      lose an item, lose a level, die
     */
    public void badStuffOccurs(Creature user) {
        //if the user is a halfling, they ignore the bad stuff of monsters level 1-10
        if (user.getUserClass().equalsIgnoreCase("Halfling") && getLevel() <= 10) {
            System.out.println("Your halfling being has granted you the grace of ignoring this monster's bad stuff.");
            return; //return so that the bad stuff doesn't apply
        }
        //depending on the monster's level, the user will face bad stuff
        if (level <= 4) { //if the monster is level 1-4, the user will lose a random item
            if (!user.getInventory().isEmpty()) { //when inventory is not empty
                Random random = new Random();
                int randomIndex = random.nextInt(user.getInventory().size()); //creates a random integer
                Treasure removedItem = user.getInventory().remove(randomIndex); //uses random integer to remove the item at that index
                System.out.println("Bad Stuff: You lost your " + removedItem.getName());
            }
            else { //when inventory is empty
                System.out.println("Bad Stuff has occurred, but you don't own anything to lose. Yikes...");
            }
        } 
        else if (level >= 6 && level <= 10) { //if the monster is level 6-10, the user will lose a level
            if (user.getLevel() > 1) { //user has to be at least level 2 to lose a level
                user.decreaseLevel(); //user level is decremented
                System.out.println("Bad Stuff: You lost a level");
            }
            else { //if the user is level 1, they're already the lowest level
                System.out.println("Bad Stuff has occurred, but you're already the lowest level. Loser.");
            }
        }
        else if (level >= 12 && level <= 14) { //if the monster level is 12-14, the user will lose an item and a level
            //uses the same previous code
            if (!user.getInventory().isEmpty()) {
                Random random = new Random();
                int randomIndex = random.nextInt(user.getInventory().size());
                Treasure removedItem = user.getInventory().remove(randomIndex);
                System.out.println("Bad Stuff: You lost your " + removedItem.getName());
            }
            else {
                System.out.println("Bad Stuff has occurred, but you don't own anything to lose. Yikes...");
            }
            if (user.getLevel() > 1) {
                user.decreaseLevel();
                System.out.println("Bad Stuff: You lost a level");
            }
            else {
                System.out.println("Bad Stuff has occurred, but you're already the lowest level. Loser.");
            }
        }
        else if (level <= 20) { //if the user is 16-20, the user will die
            user.setLivability(false); //livability is set to false, meaning the game ends and game outro is called
            System.out.println("Bad Stuff: You have been slain by the " + name + ".");
        }
    }

    //getters and one setter
    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public String getDescription() {
        return description;
    }

    public String getBadStuff() {
        return badStuff;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "Level " + getLevel() + " - " + name + "\n" + description + "\nBad Stuff: " + badStuff;
    }
}