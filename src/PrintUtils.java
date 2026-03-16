import java.util.ArrayList;

public class PrintUtils {

    public void printStartOrExitMenu(){
        System.out.println("Start the game - 1");
        System.out.println("Exit - 0");
    }

    public void printDifficultyMenu() {
        System.out.println("Choose difficulty: ");
        System.out.println("1 - Easy");
        System.out.println("2 - Medium");
        System.out.println("3 - Hard");
    }

    public void printLetsStart() {
        System.out.println();
        System.out.println("Let's start! ");
        System.out.println();
    }

    public void printGoodBye(){
        System.out.println("Good Bye!");
    }

    public void printHiddenWord(ArrayList<Character> hiddenWord){
        System.out.print("Hidden word: ");
        System.out.println(hiddenWord);
    }

    public void printWrongLetters(StringBuilder wrongLetters) {
        System.out.println("Wrong letters: " + wrongLetters);
    }

    public void printMistakeCounter(int loseCount){
        System.out.println("Mistakes: " + loseCount);
    }

    public void printWrongAlphabetUsed(){
        System.out.println("LETTERS SHOULD BE ONLY FROM ENGLISH ALPHABET.");
        System.out.println();
    }

    public void printLetterWasGuessed(){
        System.out.println("THIS LETTER HAS ALREADY BEEN GUESSED.");
        System.out.println();
    }

    public void printLetterWasTried(){
        System.out.println("THIS LETTER HAS ALREADY BEEN TRIED.");
        System.out.println();
    }

    public void printVictory(String word) {
        System.out.println();
        System.out.println("       VICTORY !       ");
        System.out.println();
        System.out.println("Hidden word: " + word.toUpperCase());
        System.out.println();
    }

    public void printGameOver(String word) {
        System.out.println("  You couldn't find the word:( \n" +
                "  GAME OVER.");
        System.out.println();
        System.out.println("Hidden word: " + word.toUpperCase());
        System.out.println();
    }

    public void printWrongNumberError(){
        System.out.println();
        System.out.println("TYPE ONLY THE SPECIFIED NUMBERS!");
        System.out.println();
    }

    public void printNumberTypeError(){
        System.out.println();
        System.out.println("TYPE ONLY THE NUMBER!");
        System.out.println();
    }

    public void printNumberLengthError(){
        System.out.println();
        System.out.println("LONG INPUT. LENGTH MUST BE 1!");
        System.out.println();

    }

    public void printLetterTypeError(){
        System.out.println();
        System.out.println("PLEASE, ENTER A LETTER.");
        System.out.println();
    }

    public void printLetterLengthError(){
        System.out.println();
        System.out.println("PLEASE, ENTER ONLY ONE LETTER.");
        System.out.println();
    }
}
