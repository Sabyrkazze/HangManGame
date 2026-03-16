import java.util.Set;
import java.util.ArrayList;
import java.util.LinkedHashSet;

public class GameProcess {

    private static final int QUIT = 0;
    private static final int PLAY = 1;
    private static final int MAX_MISTAKES = 6;
    private static final int GALLOW_SIZE_IN_FILE = 7;
    private static final int TWO_DIGIT_NUMBER = 10;
    private final PrintUtils printUtils;
    private final ReadingUtils readingUtils;
    private final FileReadingUtils fileReading;
    private final TextProcessor textProcessor;
    private final Validation validation;

    public GameProcess(PrintUtils printUtils, ReadingUtils reading, FileReadingUtils fileReading, TextProcessor textProcessor, Validation validation){
        this.printUtils = printUtils;
        this.readingUtils = reading;
        this.fileReading = fileReading;
        this.textProcessor = textProcessor;
        this.validation = validation;
    }

    public void start(){
        while(true) {
            printUtils.printStartOrExitMenu();

            int answer = getAnswer();
            if (answer == PLAY) {
                startRound();
                break;
            }
            if (answer == QUIT) {
                printUtils.printGoodBye();
                break;
            }
            if (answer >= TWO_DIGIT_NUMBER) {
                printUtils.printNumberLengthError();
            } else {
                printUtils.printWrongNumberError();
            }
        }
    }

    private void startRound(){

        Set<Character> wrongLetters = new LinkedHashSet<>();
//        StringBuilder wrongLetters = new StringBuilder();
        printUtils.printLetsStart();
        printUtils.printDifficultyMenu();

        int difficulty = getUserChoice();
        int mistakeCount = 0;
        int correctAnswersCount = 0;
        int gallowsLineToRead = 0;

        String wordFromFile = fileReading.readAWord(fileReading.getPathForDifficulty(difficulty)).toUpperCase();
        ArrayList<Character> hiddenWord = textProcessor.fillListWithUnderscore(wordFromFile);
        fileReading.readGallowState(gallowsLineToRead);

        while (mistakeCount < MAX_MISTAKES) {
            if (correctAnswersCount == wordFromFile.length()) {
                printUtils.printVictory(wordFromFile);
                break;
            }

            printUtils.printHiddenWord(hiddenWord);
            printUtils.printWrongLetters(wrongLetters);
            printUtils.printMistakeCounter(mistakeCount);

            String input = readingUtils.readInput();
            char guessLetter = input.charAt(0);

            if(validation.isInCorrectAlphabet(guessLetter)){
                printUtils.printWrongAlphabetUsed();
                continue;
            }

            if (!validation.isValidLetter(guessLetter)) {
                printUtils.printLetterTypeError();
                continue;
            }

            if (!validation.hasValidLength(input)) {
                printUtils.printLetterLengthError();
                continue;
            }

            if (!validation.isCorrectLetter(wordFromFile, guessLetter) && !wrongLetters.contains(guessLetter)) {
                gallowsLineToRead += GALLOW_SIZE_IN_FILE;
                mistakeCount++;
                wrongLetters.add(guessLetter);
            } else if(wrongLetters.contains(guessLetter)){
                printUtils.printLetterWasTried();
                fileReading.readGallowState(gallowsLineToRead);
                continue;
            }

            if (!hiddenWord.contains(guessLetter)) {
                correctAnswersCount = textProcessor.putLettersToListAndCount(wordFromFile, guessLetter, hiddenWord, correctAnswersCount);
                fileReading.readGallowState(gallowsLineToRead);
            }else{
                printUtils.printLetterWasGuessed();
            }
        }
        if (correctAnswersCount != wordFromFile.length()) {
            printUtils.printGameOver(wordFromFile);
            start();
        }
    }



    private int getAnswer() {

        while (readingUtils.isPresent()){
            readingUtils.next();
            printUtils.printNumberTypeError();
            printUtils.printStartOrExitMenu();
        }
        return readingUtils.readChoice();
    }

    private int getUserChoice() {
        while(true){
            if (readingUtils.isPresent()){
                printUtils.printNumberTypeError();
                printUtils.printDifficultyMenu();
                readingUtils.next();
                continue;
            }
            int difficulty = readingUtils.readChoice();
            if(difficulty >= TWO_DIGIT_NUMBER) {
                printUtils.printNumberLengthError();
                printUtils.printDifficultyMenu();
                continue;
            }
            if(difficulty == 1 || difficulty == 2 || difficulty == 3){
                return difficulty;
            }else {
                printUtils.printWrongNumberError();
                printUtils.printDifficultyMenu();
            }
        }
    }
}