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

    @Override
    public String toString() {
        return "Level " + level + " - " + name + "\n" + description;
    }
}