import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Treasure {
    //csv reader for the treasure cards, method that reads the treasure from the csv file and creates a hashmap of the treasure cards
    public static Map<String, List<String[]>> readTreasureCards(String filePath) {
        Map<String, List<String[]>> treasures = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            String currentSection = null;

            while ((line = br.readLine()) != null) {
                // Skip empty lines
                if (line.trim().isEmpty()) {
                    continue;
                }

                // Split the line into columns
                String[] columns = line.split(",", -1); // -1 ensures empty columns are preserved

                // Check if the first column indicates a new section
                if (columns[0].equalsIgnoreCase("Go up a level") ||
                    columns[0].equalsIgnoreCase("Armor") ||
                    columns[0].equalsIgnoreCase("Hand") ||
                    columns[0].equalsIgnoreCase("Hands") ||
                    columns[0].equalsIgnoreCase("Footgear") ||
                    columns[0].equalsIgnoreCase("Mod")) {
                    currentSection = columns[0];
                    treasures.putIfAbsent(currentSection, new ArrayList<>());
                }

                // Add the row to the current section
                if (currentSection != null) {
                    treasures.get(currentSection).add(columns);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
        return treasures;
    }

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