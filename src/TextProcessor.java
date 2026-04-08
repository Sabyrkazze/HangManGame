public class TextProcessor {

    private TextProcessor(){}

    public static void putLettersToList(String wordFromFile, char guessingLetter, char[] hiddenWord) {
        for (int i = 0; i < wordFromFile.length(); i++) {
            if (wordFromFile.charAt(i) == guessingLetter) {
                hiddenWord[i] = guessingLetter;
            }
        }
    }

    public static int countOccurrences(char[] hiddenWord, char guessLetter) {
        int count = 0;
        for (char letter : hiddenWord) {
            if (letter == guessLetter) {
                count++;
            }
        }
        return count;
    }
}
