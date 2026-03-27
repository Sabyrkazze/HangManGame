public class GameProcess {

    private static final int QUIT = 0;
    private static final int PLAY = 1;
    private static final int EASY = 1;
    private static final int MEDIUM = 2;
    private static final int HARD = 3;
    private static final int MAX_MISTAKES = 6;
    private static final int TWO_DIGIT_NUMBER = 10;
    private final GameState gameState;
    private final PrintUtils printUtils;
    private final ReadingUtils readingUtils;
    private final FileReadingUtils fileReading;
    private final TextProcessor textProcessor;
    private final Validation validation;

    public GameProcess(GameState gameState, PrintUtils printUtils, ReadingUtils reading, FileReadingUtils fileReading, TextProcessor textProcessor, Validation validation){
        this.gameState = gameState;
        this.printUtils = printUtils;
        this.readingUtils = reading;
        this.fileReading = fileReading;
        this.textProcessor = textProcessor;
        this.validation = validation;
    }

    public void start(){

        gameState.resetGame();
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
        }
    }

    private void startRound(){

        printUtils.printLetsStart();
        printUtils.printDifficultyMenu();

        int difficulty = getUserChoice();
        gameState.initWord(difficulty);
        gameState.createCharArray(gameState.getWordFromFile());

        while (gameState.getMistakeCount() < MAX_MISTAKES) {
            if (gameState.getCorrectAnswersCount() == gameState.getWordFromFile().length()) {
                printUtils.printVictory(gameState.getWordFromFile());
                start();
                return;
            }
            fileReading.readGallowState(gameState.getGallowsLineToRead());
            printUtils.printHiddenWord(gameState.getHiddenWord());
            printUtils.printWrongLetters(gameState.getWrongLetters());
            printUtils.printMistakeCounter(gameState.getMistakeCount());

            String input = readingUtils.readInput();
            char guessLetter = input.charAt(0);

            if (!validation.hasValidLength(input)) {
                printUtils.printLetterLengthError();
                continue;
            }

            if(validation.isInCorrectAlphabet(guessLetter)){
                printUtils.printWrongAlphabetUsed();
                continue;
            }

            if (!gameState.getWordFromFile().contains(String.valueOf(guessLetter)) && !gameState.getWrongLetters().contains(guessLetter)) {
                gameState.makeAMistake();
                gameState.updateGallow();
                gameState.getWrongLetters().add(guessLetter);
            } else if(gameState.getWrongLetters().contains(guessLetter)){
                printUtils.printLetterWasTried();
                continue;
            }

            if (!validation.contains(gameState.getHiddenWord(), guessLetter)) {
                gameState.setCorrectAnswersCount(textProcessor.putLettersToListAndCount(gameState.getWordFromFile(), guessLetter, gameState.getHiddenWord(), gameState.getCorrectAnswersCount()));
            }else{
                printUtils.printLetterWasGuessed();
            }
        }
        if (gameState.getCorrectAnswersCount() != gameState.getWordFromFile().length()) {
            printUtils.printGameOver(gameState.getWordFromFile());
            start();
        }
    }

    private int getAnswer() {

        while (true){
            if (readingUtils.isPresent()){
                printUtils.printNumberTypeError();
                printUtils.printStartOrExitMenu();
                readingUtils.next();
                continue;
            }
            int answer = readingUtils.readChoice();
            if(answer >= TWO_DIGIT_NUMBER) {
                printUtils.printNumberLengthError();
                printUtils.printStartOrExitMenu();
                continue;
            }
            if(answer == PLAY || answer == QUIT ){
                return answer;
            }else {
                printUtils.printWrongNumberError();
                printUtils.printStartOrExitMenu();
            }
        }
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
            if(difficulty == EASY || difficulty == MEDIUM || difficulty == HARD){
                return difficulty;
            }else {
                printUtils.printWrongNumberError();
                printUtils.printDifficultyMenu();
            }
        }
    }
}