import java.util.ArrayList;

public class Validation {

    public boolean isValidChoice(int difficulty){
        return !(difficulty != 1 && difficulty != 2 && difficulty != 3);
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

    public boolean listHasLetter(ArrayList<Character> hiddenWord, char guessingLetter) {
        return hiddenWord.contains(guessingLetter);
    }

    public boolean stringBuilderHasLetter(StringBuilder wrongLetters, char guessingLetter) {
        return String.valueOf(wrongLetters).contains(String.valueOf(guessingLetter));
    }
}
