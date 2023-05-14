import java.util.*;

public class Hard extends Computer {
    ArrayList<String> possibleCodes;
    HashSet<String> previousGuesses;

    public Hard() {
        // call the parent constructor
        super();

        // initialize instance variables
        possibleCodes = new ArrayList<>();
        previousGuesses = new HashSet<>();

        // generate all possible codes that meet the criteria
        generateAllPossibleCodes();
    }

    // generate all possible codes and add to list of possible codes
    private void generateAllPossibleCodes() {
        // iterate through all 4-digit codes, add those that meet the criteria to list of possible codes
        for (int i = 0; i < 10000; i++) {
            String code = String.format("%04d", i);
            if (isValidCode(code)) {
                possibleCodes.add(code);
            }
        }
    }

    // check if a given code is valid
    private boolean isValidCode(String code) {
        // check if code has 4 UNIQUE digits
        Set<Character> uniqueDigits = new HashSet<>();
        for (char c : code.toCharArray()) {
            uniqueDigits.add(c);
        }
        return uniqueDigits.size() == 4;
    }

    // generate new guess based on previous guesses + results
    @Override
    public String makeGuess() {
        // for FIRST guess, generate random code and return it
        if (previousGuesses.isEmpty()) {
            String guess = generateSecretCode();
            previousGuesses.add(guess);
            return guess;
        }
        // for next guesses, use previous guess(es) + results to eliminate invalid codes then generate a new guess
        else {
            String lastGuess = (String) previousGuesses.toArray()[previousGuesses.size() - 1];
            String lastResult = getResult(lastGuess);

            // eliminate codes that don't match the last guess's results
            Iterator<String> iter = possibleCodes.iterator();
            while (iter.hasNext()) {
                String code = iter.next();
                if (!getResultForComparison(code, lastGuess).equals(lastResult)) {
                    iter.remove();
                }
            }

            // generate new guess randomly from remaining possible codes
            String guess = possibleCodes.get(new Random().nextInt(possibleCodes.size()));
            previousGuesses.add(guess);
            return guess;
        }
    }

    // get guess result
    private String getResult(String guess) {
        return getResultForComparison(secretCode, guess);
    }

    // get the result of a guess compared to a code

    private String getResultForComparison(String code, String guess) {
        int bulls = 0, cows = 0;

        // count bulls + cows
        for (int i = 0; i < 4; i++) {
            if (code.charAt(i) == guess.charAt(i)) {
                bulls++;
            } else if (code.contains(Character.toString(guess.charAt(i)))) {
                cows++;
            }
        }

        // return result as string
        return bulls + "B" + cows + "C";
    }
}
