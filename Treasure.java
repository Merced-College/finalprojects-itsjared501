
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Treasure {

    // Method to pull a treasure item and apply its attack power
    public static void applyTreasureToCreature(Map<String, List<String[]>> treasures, String section, Creature creature) {
        // Check if the section exists in the HashMap
        if (!treasures.containsKey(section)) {
            System.out.println("Section not found: " + section);
            return;
        }

        // Get the list of items for the section
        List<String[]> items = treasures.get(section);

        // Check if the section has any items
        if (items.isEmpty()) {
            System.out.println("No items found in section: " + section);
            return;
        }

        // Randomly select an item from the list
        Random random = new Random();
        int index = random.nextInt(items.size());
        String[] selectedItem = items.get(index);

        // Parse the attack power from the third column (if it exists)
        try {
            int attackPower = Integer.parseInt(selectedItem[2].trim());
            creature.addAttackPower(attackPower); // Add attack power to the creature
            System.out.println("Equipped item: " + String.join(", ", selectedItem));
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            System.out.println("Invalid attack power for item: " + String.join(", ", selectedItem));
        }
    }
}