public class Validation {

    public boolean isValidChoice(int difficulty){
        return true;
    }

    public boolean isInCorrectAlphabet(char guessingLetter){
        return !String.valueOf(guessingLetter).matches("[a-zA-z]+");
    }

    public boolean isValidLetter(char guessingLetter) {
        return Character.isLetter(guessingLetter);
    }

    public boolean hasValidLength(String input) {
        return input.length() == 1;
    }

    public boolean isCorrectLetter(String word, char guessingLetter) {
        return word.contains(String.valueOf(guessingLetter));
    }
}
