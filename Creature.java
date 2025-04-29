import java.util.ArrayList;

public class Creature {
    private String name;
    private String userGender;
    private String userClass;
    private String userRace;
    private int level = 1;
    private int attackPower;
    
    //private ArrayList<Treasure> inventory = new ArrayList<Treasure>()[10];

    public Creature(String name, String userGender) {
        this.name = name;
        this.userGender = userGender;
    }

    public String getName() {
        return name;
    }

    public String getUserGender() {
        return userGender;
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

    /*public ArrayList<Treasure> getInventory() {
        return inventory;
    }

    public selectClass {

    }

    public selectRace {
        
    }*/
}