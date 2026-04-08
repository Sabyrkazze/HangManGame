public class InputValidator {

    public boolean isInCorrectAlphabet(char guessingLetter) {
        return !String.valueOf(guessingLetter).matches("[a-zA-z]+");
    }

    public boolean hasValidLength(String input) {
        return input.length() == 1;
    }
}
