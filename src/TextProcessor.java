public class TextProcessor {



    public int putLettersToListAndCount(String word, char guessingLetter, char[] hiddenWord, int winCount) {
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == guessingLetter){
                hiddenWord[i] = guessingLetter;
                winCount++;
            }
        }
        return winCount;
    }
}
