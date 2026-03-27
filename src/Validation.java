public class Validation {

    public boolean isInCorrectAlphabet(char guessingLetter){
        return !String.valueOf(guessingLetter).matches("[a-zA-z]+");
    }

    public boolean hasValidLength(String input) {
        return input.length() == 1;
    }

    public boolean contains(char[] hiddenWord, char guessLetter){
        for (char letter : hiddenWord){
            if (letter == guessLetter){
                return true;
            }
        }
        return false;
    }
}
