import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

// GameSession class represents a game session
public class GameSession {
    // declare instance variables
    Player player;
    Computer computer;
    ArrayList<String> playerGuesses;
    ArrayList<String> computerGuesses;
    ArrayList<String> playerResults;
    ArrayList<String> computerResults;

    // use GameSession constructor to initialize instance variables for player, computer, playerGUesses, computerGuesses, playerREsults, and comptuerREsults
    public GameSession(Player player, Computer computer) {
        this.player = player;
        this.computer = computer;
        playerGuesses = new ArrayList<>();
        computerGuesses = new ArrayList<>();
        playerResults = new ArrayList<>();
        computerResults = new ArrayList<>();
    }

    //playGame method allows user to play the game
    public void playGame() {
        Scanner scanner = new Scanner(System.in);
        //prompt user to input secret code
        System.out.println("Enter your secret code (4 unique digits): ");
        String playerSecretCode = scanner.next();
        //if code is invalid, prompt user to enter valid code
        while (!isValidCode(playerSecretCode)) {
            System.out.println("Your secret code is invalid. Please enter a valid secret code (4 unique digits): ");
            playerSecretCode = scanner.next();
        }
        player.setSecretCode(playerSecretCode);

        //computer generates secret code
        computer.generateSecretCode();
        System.out.println("The computer has generated a secret code.");

        // initialize a boolean variable to check whether the game has been won
        boolean gameWon = false;

        // user and computer to make guesses until one wins or seven turns are completed

        for (int turn = 1; turn <= 7 && !gameWon; turn++) {
            System.out.println("Turn " + turn + ":");

            // prompt user to input their guess
            String playerGuess;

            do {
                System.out.println("Enter your guess (4 unique digits): ");
                playerGuess = scanner.next();
            } while (!isValidCode(playerGuess));

            // calculate results of the user's guess and add it to their results list
            String playerResult = getResult(playerGuess, computer.getSecretCode());
            playerGuesses.add(playerGuess);
            playerResults.add(playerResult);

            // display the result of user's guess and check if they won
            System.out.println("Your guess: " + playerGuess + " Result: " + playerResult);
            if (playerResult.equals("4B0C")) {
                System.out.println("Congratulations! You have guessed the computer's secret code.");
                gameWon = true;
            }

            // if user has NOT won, allow computer to make guess
            if (!gameWon) {
                String computerGuess = computer.makeGuess();
                String computerResult = getResult(computerGuess, player.getSecretCode());
                computerGuesses.add(computerGuess);
                computerResults.add(computerResult);

                // display the result of computer's guess, check if the computer won

                System.out.println("Computer's guess: " + computerGuess + " Result: " + computerResult);
                if (computerResult.equals("4B0C")) {
                    //if computer wins, display message to user
                    System.out.println("The computer has guessed your secret code.");
                    gameWon = true;
                }
            }
        }

        // if neither player or computer wins, game was a draw, display prompt to user that game was a draw
        if (!gameWon) {
            System.out.println("The game ended in a draw.");
        }

        // prompt the user to save the game results to a file (yes/no choice)
        System.out.println("Do you want to save the results to a file? (yes/no)");
        String saveResults = scanner.next().toLowerCase();
        // if the user chooses to save the results, user needs to entter a filename, save the results to that file
        if (saveResults.equals("yes")) {
            System.out.println("Enter the filename:");
            String filename = scanner.next();
            saveResultsToFile(filename);
            System.out.println("Results saved to " + filename);
        }
    }

    // check if code is valid/has 4 numbers with boolean isValidCode method
    private boolean isValidCode(String code) {
        Set<Character> uniqueDigits = new HashSet<>();
        for (char c : code.toCharArray()) {
            uniqueDigits.add(c);
        }
        return uniqueDigits.size() == 4;
    }

    // getResult method calculates result of each guess
    private String getResult(String guess, String code) {
        int bulls = 0, cows = 0;

        //calculate how many bulls and how many cows

        for (int i = 0; i < 4; i++) {
            if (code.charAt(i) == guess.charAt(i)) {
                bulls++;
            } else if (code.contains(Character.toString(guess.charAt(i)))) {
                cows++;
            }
        }

        return bulls + "B" + cows + "C";
    }

    // to save results to file, run saveResultsToFile method
    private void saveResultsToFile(String filename) {
        try (FileWriter writer = new FileWriter(filename)) {
            //write the secret codes for player and computer
            writer.write("Player's secret code: " + player.getSecretCode() + "\n");
            writer.write("Computer's secret code: " + computer.getSecretCode() + "\n\n");

            //write results in a table format

            writer.write("Turn | Player's Guess | Result | Computer's Guess | Result\n");
            writer.write("-----|----------------|--------|-----------------|--------\n");

            String format = "%-5s|%-16s|%-8s|%-17s|%s%n";
            for (int i = 0; i < playerGuesses.size(); i++) {
                writer.write(String.format(format, (i + 1), playerGuesses.get(i), playerResults.get(i), computerGuesses.get(i), computerResults.get(i)));
            }

            //include in file if was win or draw, and if win, who won
            if (playerResults.contains("4B0C")) {
                writer.write("\nPlayer won!\n");
            } else if (computerResults.contains("4B0C")) {
                writer.write("\nComputer won!\n");
            } else {
                writer.write("\nThe game was a draw.\n");
            }

            //if can't save results to file, give error message
        } catch (IOException e) {
            System.out.println("Error saving the results to the file.");
            e.printStackTrace();
        }
    }
}