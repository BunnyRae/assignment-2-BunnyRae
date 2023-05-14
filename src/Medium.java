import java.util.HashSet;

// define new class (Medium) that extends Computer class
public class Medium extends Computer {

    // define HashSet<String> instance variable called previousGuesses
    HashSet<String> previousGuesses;

    // define constructor method for Medium class
    public Medium() {

        // call constructor method of Computer superclass
        super();

        // initialize previousGuesses variable to new/empty HashSet
        previousGuesses = new HashSet<>();
    }

    // override makeGuess method of Computer class
    @Override
    public String makeGuess() {

        // generate new guess using generateSecretCode method of Computer superclass
        String guess = generateSecretCode();

        // check if the guess has already been made by looking it up in the previousGuesses set
        while (previousGuesses.contains(guess)) {

            // if guess HAS already been made, generate a new guess
            guess = generateSecretCode();
        }

        // add new guess to previousGuesses set
        previousGuesses.add(guess);

        // return new guess
        return guess;
    }
}
