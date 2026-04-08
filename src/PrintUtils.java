import java.util.Set;

public class PrintUtils {

    private PrintUtils() {

    }

    public static void printStartOrExitMenu() {
        System.out.println("Start the game - 1");
        System.out.println("Exit - 0");
    }

    public static void printDifficultyMenu() {
        System.out.println("Choose difficulty: ");
        System.out.println("1 - Easy");
        System.out.println("2 - Medium");
        System.out.println("3 - Hard");
    }

    public static void printLetsStart() {
        System.out.println();
        System.out.println("Let's start! ");
        System.out.println();
    }

    public static void printGoodBye() {
        System.out.println("Good Bye!");
    }

    public static void printGallowsStage(int mistakes) {
        System.out.println(GallowsStages.fromOrdinal(mistakes).getImage());
    }

    public static void printHiddenWord(char[] hiddenWord) {
        System.out.print("Hidden word: ");
        for (char letter : hiddenWord) {
            System.out.print(letter + " ");
        }
        System.out.println();
    }

    public static void printWrongLetters(Set<Character> wrongLetters) {
        System.out.print("Wrong letters: ");
        for (char letter : wrongLetters) {
            System.out.print(letter + " ");
        }
        System.out.println();
    }

    public static void printMistakeCounter(int loseCount) {
        System.out.println("Mistakes: " + loseCount);
    }

    public static void printWrongAlphabetUsed() {
        System.out.println();
        System.out.println("LETTERS SHOULD BE ONLY FROM ENGLISH ALPHABET.");
        System.out.println();
    }

    public static void printLetterWasGuessed() {
        System.out.println();
        System.out.println("THIS LETTER HAS ALREADY BEEN GUESSED.");
        System.out.println();
    }

    public static void printLetterWasTried() {
        System.out.println();
        System.out.println("THIS LETTER HAS ALREADY BEEN TRIED.");
        System.out.println();
    }

    public static void printVictory(String word) {
        System.out.println();
        System.out.println("       VICTORY !       ");
        System.out.println();
        System.out.println(" Hidden word: " + word.toUpperCase());
        System.out.println();
    }

    public static void printGameOver(String word) {
        System.out.println("  You couldn't find the word:( \n" +
                "           GAME OVER.");
        System.out.println();
        System.out.println("Hidden word: " + word.toUpperCase());
        System.out.println();
    }

    public static void printWrongNumberError() {
        System.out.println();
        System.out.println("TYPE ONLY THE SPECIFIED NUMBERS!");
        System.out.println();
    }

    public static void printNumberTypeError() {
        System.out.println();
        System.out.println("TYPE ONLY THE NUMBER!");
        System.out.println();
    }

    public static void printNumberLengthError() {
        System.out.println();
        System.out.println("LONG INPUT. LENGTH MUST BE 1!");
        System.out.println();

    }

    public static void printLetterLengthError() {
        System.out.println();
        System.out.println("PLEASE, ENTER ONLY ONE LETTER.");
        System.out.println();
    }
}
