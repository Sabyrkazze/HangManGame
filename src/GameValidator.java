public class GameValidator {
    public boolean contains(char[] hiddenWord, char guessLetter) {
        for (char letter : hiddenWord) {
            if (letter == guessLetter) {
                return true;
            }
        }
        return false;
    }
}
