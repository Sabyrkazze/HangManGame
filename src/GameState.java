import java.util.Arrays;
import java.util.Set;

public class GameState {

    private final Set<Character> wrongLetters;
    private String wordFromFile;
    private char[] hiddenWord;
    private int difficulty;
    private int mistakeCount;
    private int correctAnswersCount;

    public GameState(Set<Character> wrongLetters) {
        this.wrongLetters = wrongLetters;
    }

    public void initWord(int difficulty) {
        this.difficulty = difficulty;
        this.wordFromFile = FileReadingUtils.readAWord(FileReadingUtils.getPathForDifficulty(difficulty)).toUpperCase();
    }

    public void makeAMistake() {
        mistakeCount++;
    }

    void createCharArray(String wordFromFile) {
        this.hiddenWord = new char[wordFromFile.length()];
        Arrays.fill(this.hiddenWord, '_');
    }

    public void resetGame() {
        this.mistakeCount = 0;
        this.correctAnswersCount = 0;
        this.wrongLetters.clear();
    }

    public char[] getHiddenWord() {
        return hiddenWord;
    }

    public Set<Character> getWrongLetters() {
        return wrongLetters;
    }

    public String getWordFromFile() {
        return wordFromFile;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public int getMistakeCount() {
        return mistakeCount;
    }

    public int getCorrectAnswersCount() {
        return correctAnswersCount;
    }

    public void addToCorrectAnswersCount(int correctAnswersCount) {
        this.correctAnswersCount += correctAnswersCount;
    }
}
