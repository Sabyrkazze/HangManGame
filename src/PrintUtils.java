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

    public void printHiddenWord(ArrayList<Character> hiddenWord){
        System.out.print("Hidden word: ");
        System.out.println(hiddenWord);
    }

    public void printWrongLetters(StringBuilder wrongLetters) {
        System.out.println("Wrong letters: " + wrongLetters);
    }



    public void printVictory(String word) {
        System.out.println();
        System.out.println("       VICTORY !");
        System.out.println();
        System.out.println("Hidden word: " + word.toUpperCase());
        System.out.println();
    }

    public void printGameOver(String word) {
        System.out.println("  You couldn't find the letter:( \n" +
                "  GAME OVER.");
        System.out.println();
        System.out.println("Hidden word: " + word.toUpperCase());
        System.out.println();
    }


    public void printNotValidChoice(){
        System.out.println();
        System.out.println("Type only the specified numbers!");
        System.out.println();
    }

    public void printInputTypeError(){
        System.out.println();
        System.out.println("Please, enter a letter.");
        System.out.println();
    }

    public void printInputLengthError(){
        System.out.println();
        System.out.println("Please, enter only one letter.");
        System.out.println();
    }
}
