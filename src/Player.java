public class Player {
    // define a String instance variable named secretCode
    String secretCode;

    // define a constructor method that is called when a Player object is created
    public Player() {
        // set the value of the secretCode variable to an empty string
        secretCode = "";
    }

    // define method setSecretCode to take a String argument named secretCode
    public void setSecretCode(String secretCode) {
        // set the value of the secretCode instance variable to the value of the secretCode argument
        this.secretCode = secretCode;
    }

    // define method getSecretCode to return value of secretCode instance variable
    public String getSecretCode() {
        // return value of secretCode instance variable
        return secretCode;
    }
}
