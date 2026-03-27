import java.util.Arrays;
import java.util.Set;

public class GameState {

    private static final int GALLOW_SIZE_IN_FILE = 7;

    private final FileReadingUtils fileReading;
    private final Set<Character> wrongLetters;
    private String wordFromFile;
    private char[] hiddenWord;
    private int difficulty;
    private int mistakeCount;
    private int correctAnswersCount;
    private int gallowsLineToRead;

    public GameState(Set<Character> wrongLetters, FileReadingUtils fileReading){
        this.wrongLetters = wrongLetters;
        this.fileReading = fileReading;
    }

    public void initWord(int difficulty){
        this.difficulty = difficulty;
        this.wordFromFile = fileReading.readAWord(fileReading.getPathForDifficulty(difficulty)).toUpperCase();
    }

    public void updateGallow(){
        gallowsLineToRead += GALLOW_SIZE_IN_FILE;
    }

    public void makeAMistake(){
        mistakeCount++;
    }

    void createCharArray(String wordFromFile){
        this.hiddenWord = new char[wordFromFile.length()];
        Arrays.fill(this.hiddenWord, '_');
    }

    public void resetGame(){
        this.mistakeCount = 0;
        this.correctAnswersCount = 0;
        this.gallowsLineToRead = 0;
        this.wrongLetters.clear();
    }

    public char[] getHiddenWord() {
        return hiddenWord;
    }

    public Set<Character> getWrongLetters(){
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

    public void setMistakeCount(int mistakeCount) {
        this.mistakeCount = mistakeCount;
    }

    public int getCorrectAnswersCount() {
        return correctAnswersCount;
    }

    public void setCorrectAnswersCount(int correctAnswersCount) {
        this.correctAnswersCount = correctAnswersCount;
    }

    public int getGallowsLineToRead() {
        return gallowsLineToRead;
    }

    public void setGallowsLineToRead(int gallowsLineToRead) {
        this.gallowsLineToRead = gallowsLineToRead;
    }
}
