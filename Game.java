//Jared Lee
//April 29, 2025
//This is a text-based RPG game based on the card game Munchkin.
import java.util.Scanner;
//import java.util.ArrayList;

class Game {
    //This is the main class for the game. It will handle the game loop and user input.

    public static void gameIntro() throws InterruptedException {
        Scanner scanner = new Scanner(System.in);

        //Introduce the user to the game and ask for their information.
        System.out.println("(It is recommended you read the README file before playing this game.)");
        System.out.println(" ");;
        System.out.println("Welcome to the epicness that is Munchkin! The game of pure uniqueness and chance, probability, and...yeah. You get the point.");
        Thread.sleep(4000);//this will be used to pause the program for 3 seconds
        System.out.println("To start off your journey, tell me about yourself!");
        System.out.print("What is your name? ");
        String name = scanner.nextLine(); //this will be used to create the user object of the class Creature

        System.out.print("What is your gender? Male/Female: ");
        String gender = scanner.nextLine(); //this will be used to create the user object of the class Creature

        Creature user = new Creature(name, gender); //uses the constructor to create the user object

        System.out.println("Wow, " + user.getName() + "! What an eh name... It's no Godfrey the Slayer of Fae! Or Beyonce the Queen Bee! Now, what class are you?");
        Thread.sleep(4000); //this will be used to pause the program for 2 seconds
        user.selectClass(); //this will be used to select the class of the user

        System.out.println("I see I see... how interesting. Lastly what race are you? (I know, sensitive topic, but I need to know.)");
        Thread.sleep(4000); //this will be used to pause the program for 2 seconds
        user.selectRace(); //this will be used to select the race of the user

        System.out.println("Fascinating! Well it's nice to meet you, " + user.getName() + " the " + user.getUserRace() + " " + user.getUserClass() + "!");

        //bring up the selectRace method to allow the user to select one

        scanner.close();
    }
    public static void main(String[] args) throws InterruptedException {
        gameIntro();
    }
}