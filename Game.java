//Jared Lee
//April 29, 2025
//This is a text-based RPG game based on the card game Munchkin.
import java.util.Scanner;
//import java.util.ArrayList;

class Game {
    //This is the main class for the game. It will handle the game loop and user input.
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //Introduce the user to the game and ask for their information.
        System.out.println("Welcome to the epicness that is Munchkin! To start off your journey, tell me about yourself!");
        System.out.print("What is your name? ");
        String name = scanner.nextLine(); //this will be used to create the user object of the class Creature

        System.out.print("What is your gender? Male/Female");
        String gender = scanner.nextLine(); //this will be used to create the user object of the class Creature

        Creature user = new Creature(name, gender); //uses the constructor to create the user object

        System.out.println("Wow, " + user.getName() + "! What an eh name... It's no Godfrey the Slayer of Fae! Or Beyonce the Queen Bee! Now, what race are you?");

        //bring up the selectRace method to allow the user to select one

        //bring up the selectClass method to allow the user to select one

        scanner.close();
    }
}