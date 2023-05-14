public class Easy extends Computer {

    // override the makeGuess method of the Computer class
    @Override
    public String makeGuess() {

        // generate new guess using generateSecretCode method of the superclass
        return generateSecretCode();
    }
}
