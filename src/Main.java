import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    Scanner scanner = new Scanner(System.in);
    PrintUtils printUtils = new PrintUtils();
    Validation validation = new Validation();
    ReadingUtils reading = new ReadingUtils();
    TextProcessor textProcessor = new TextProcessor();
    FileReadingUtils fileReading = new FileReadingUtils();

    public static void main(String[] args) {
        Main main = new Main();
        main.start();
    }

    public void start(){
        while(true){
            int answer;
            try {
                printUtils.printStartOrExitMenu();
                answer = reading.readChoice(scanner);
            } catch (InputMismatchException e) {
                printUtils.printNotValidChoice();
                scanner.nextLine();
                continue;
            }

            if(answer == 0){
                System.out.println("Good Bye!");
                break;
            }
            else if (answer != 1){
                printUtils.printNotValidChoice();
                continue;
            }
            //Game Starts
            StringBuilder wrongLetters = new StringBuilder();
            printUtils.printLetsStart();
            int difficulty;
            while(true) {
                try {
                    printUtils.printDifficultyMenu();
                    difficulty = reading.readChoice(scanner);
                    if(!validation.isValidChoice(difficulty)){
                        printUtils.printNotValidChoice();
                        continue;
                    }
                    break;
                } catch (InputMismatchException e) {
                    printUtils.printNotValidChoice();
                    scanner.nextLine();
                }
            }
            int loseCount = 0;
            int winCount = 0;
            int lineToRead = 0;
            String word = fileReading.readAWord(fileReading.getPathForDifficulty(difficulty)).toUpperCase();
            ArrayList<Character> hiddenWord = textProcessor.fillListWithUnderscore(word);
            fileReading.readGallowState(lineToRead);

            while (loseCount < 6) {
                if (winCount == word.length()) {
                    printUtils.printVictory(word);
                    break;
                }

                printUtils.printHiddenWord(hiddenWord);
                printUtils.printWrongLetters(wrongLetters);

                String input = reading.readInput(scanner);
                char guessingLetter = input.charAt(0);

                if (!validation.isValidLetter(guessingLetter)) {
                    printUtils.printInputTypeError();
                    continue;
                }

                if (!validation.hasValidLength(input)) {
                    printUtils.printInputLengthError();
                    continue;
                }

                if (!validation.isCorrectLetter(word, guessingLetter) && !validation.stringBuilderHasLetter(wrongLetters, guessingLetter)) {
                    lineToRead += 7;
                    loseCount++;
                    textProcessor.checkAndAppendWrongLetter(wrongLetters, guessingLetter);
                }

                if (!validation.listHasLetter(hiddenWord, guessingLetter)) {
                    winCount = textProcessor.putLettersToListAndCount(word, guessingLetter, hiddenWord, winCount);
                    fileReading.readGallowState(lineToRead);
                }
            }
            if (winCount != word.length()) {
                fileReading.readGallowState(lineToRead);
                printUtils.printGameOver(word);
            }
        }
    }
}