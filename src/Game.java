import java.util.*;

//import packages
//use main method to define Game class
//create objects of player and computer classes
// use Scanner to take input from user
//set difficulty level based on user input
//create gameSession object

public class Game {
    //create a class called Game
    public static void main(String[] args) {
    //create main method
        Player player = new Player();
        //create a new instance of PLayer class and assign the variable player to create player object
        Computer computer;
        //declare a computer object (but not initialized)

        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Bulls and Cows game!");
        //display welcome message to user
        System.out.println("Please choose difficulty level by entering the digit corresponding to the difficulty level: (1) Easy, (2) Medium, (3) Hard");
        //display message inviting user to choose difficulty

        int difficulty = scanner.nextInt();
        //take user input and store in a variable called difficulty
        while (difficulty < 1 || difficulty > 3) {
            //make sure input is valid
            System.out.println("Invalid difficulty level. Please enter 1, 2, or 3.");
            //display message if input is invalid
            difficulty = scanner.nextInt();
            //if user input invalid, take user input again and store in difficulty variable
        }

        if (difficulty == 1) {
            computer = new Easy();
            //if user enters 1 to select easy, create a new Easy computer object
        } else if (difficulty == 2) {
            computer = new Medium();
            //if user enters 2 to select medium, create a new Medium computer object
        } else {
            computer = new Hard();
            //if user enters 3 (anything but 1 or 2, but >3 will print invalid message) to select Hard, create a new Hard computer object
        }

        GameSession session = new GameSession(player, computer);
        //create new GameSEssion object by calling constructor and passing PLayer and Computer objects as arguments to initialize game session
        session.playGame();
        //call playGame() method of GameSession class to start the game
    }
}
