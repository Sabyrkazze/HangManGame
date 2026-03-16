public class Validation {

    public boolean isInCorrectAlphabet(char guessingLetter){
        return !String.valueOf(guessingLetter).matches("[a-zA-z]+");
    }

    public boolean hasValidLength(String input) {
        return input.length() == 1;
    }

    public boolean isCorrectLetter(String word, char guessingLetter) {
        return word.contains(String.valueOf(guessingLetter));
    }
}
