public class TextProcessor {

    public void putLettersToList(String wordFromFile, char guessingLetter, char[] hiddenWord) {
        for (int i = 0; i < wordFromFile.length(); i++) {
            if (wordFromFile.charAt(i) == guessingLetter) {
                hiddenWord[i] = guessingLetter;
            }
        }
    }

    public int countOccurrences(char[] hiddenWord, char guessLetter) {
        int count = 0;
        for (char letter : hiddenWord) {
            if (letter == guessLetter) {
                count++;
            }
        }
        return count;
    }
}
