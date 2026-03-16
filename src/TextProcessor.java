import java.util.ArrayList;

public class TextProcessor {

    public ArrayList<Character> fillListWithUnderscore(String word) {
        ArrayList<Character> hiddenWord = new ArrayList<>();
        for (int i = 0; i < word.length(); i++) {
            hiddenWord.add('_');
        }
        return hiddenWord;
    }

    public int putLettersToListAndCount(String word, char guessingLetter, ArrayList<Character> hiddenWord, int winCount) {
        ArrayList<Integer> indexes = letterIndexesInWord(word, guessingLetter);
        for (int i = 0; i < indexes.size(); i++) {
            hiddenWord.set(indexes.get(i), guessingLetter);
            winCount++;
        }
        return winCount;
    }

    public ArrayList<Integer> letterIndexesInWord(String word, char letterToFind){
        ArrayList<Integer> indexes = new ArrayList<>();
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == letterToFind){
                indexes.add(i);
            }
        }
        return indexes;
    }

}
