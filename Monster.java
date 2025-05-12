import java.util.Random;

public class Monster {
    private String name;
    private int level;
    private String description;
    private String badStuff;

    public Monster(String name, int level, String description, String badStuff) {
        this.name = name;
        this.level = level;
        this.description = description;
        this.badStuff = badStuff;
    }

    public void badStuffOccurs(Creature user) {
        //if the user is a halfling, they ignore the bad stuff of monsters level 1-10
        if (user.getUserClass().equalsIgnoreCase("Halfling") && level <= 10) {
            System.out.println("Your halfling being has granted you the grace of ignoring this monster's bad stuff.");
            return;
        }
        //depending on the monster's level, the user will face bad stuff
        if (level <= 4) {
            if (!user.getInventory().isEmpty()) {
                Random random = new Random();
                int randomIndex = random.nextInt(user.getInventory().size());
                Treasure removedItem = user.getInventory().remove(randomIndex);
                System.out.println("Bad Stuff: You lost your " + removedItem.getName());
            }
            else {
                System.out.println("Bad Stuff has occurred, but you don't own anything to lose. Yikes...");
            }
        }
        else if (level >= 6 && level <= 10) {
            if (user.getLevel() > 1) {
                user.decreaseLevel();
                System.out.println("Bad Stuff: You lost a level");
            }
            else {
                System.out.println("Bad Stuff has occurred, but you're already the lowest level. Loser.");
            }
        }
        else if (level >= 12 && level <= 14) {
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
        else if (level <= 20) {
            user.setLivability(false);
            System.out.println("Bad Stuff: You have been slain by the " + name + ".");
        }
    }

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getDescription() {
        return description;
    }

    public String getBadStuff() {
        return badStuff;
    }

    @Override
    public String toString() {
        return "Level " + getLevel() + " - " + name + "\n" + description + "\nBad Stuff: " + badStuff;
    }
}