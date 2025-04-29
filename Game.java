import java.util.Scanner;
//import java.util.ArrayList;

class Game {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the epicness that is Munchkin! To start off your journey, tell me about yourself!");
        System.out.print("What is your name? ");
        String name = scanner.nextLine();

        System.out.print("What is your gender? ");
        String gender = scanner.nextLine();

        Creature user = new Creature(name, gender);

        System.out.println("Wow, " + user.getName() + "! What a great name! Now, what class do you want to be?");

        scanner.close();
    }
}