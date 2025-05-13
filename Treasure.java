
/**
 * Treasure.java
 * @author Jared Lee
 * Object-class that represents the treasures in the game
 *      contains the treasure's name, type, attack power, and description
 */
public class Treasure {
    private String type; // e.g., "Armor", "Hand"
    private String name;    // Unique key (column 2)
    private int attackPower; // Attack power (column 3, if applicable)
    private String description; // Additional description (column 4, if applicable)

    // Constructor
    public Treasure(String type, String name, int attackPower, String description) {
        this.type = type;
        this.name = name;
        this.attackPower = attackPower;
        this.description = description;
    }

    // Getters
    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public int getAttackPower() {
        return attackPower;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return name + " (" + type + ") \n" + description;
    }
    
}