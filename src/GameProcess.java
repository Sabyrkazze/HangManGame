public class GameProcess {

    private static final int QUIT = 0;
    private static final int PLAY = 1;
    private static final int EASY = 1;
    private static final int MEDIUM = 2;
    private static final int HARD = 3;
    private static final int MAX_MISTAKES = 6;
    private static final int TWO_DIGIT_NUMBER = 10;
    private final GameState gameState;
    private final InputValidator inputValidator;
    private final GameValidator gameValidator;

    public GameProcess(GameState gameState, InputValidator inputValidator, GameValidator gameValidator) {
        this.gameState = gameState;
        this.inputValidator = inputValidator;
        this.gameValidator = gameValidator;
    }

    public void start() {

        while (true) {
            PrintUtils.printStartOrExitMenu();
            int answer = getAnswer();

            if (answer == PLAY) {
                gameState.resetGame();
                startRound();
            }
            if (answer == QUIT) {
                PrintUtils.printGoodBye();
                break;
            }
        }
    }

    private void startRound() {

        PrintUtils.printLetsStart();
        PrintUtils.printDifficultyMenu();

        gameState.setDifficulty(getUserChoice());
        gameState.initWord(gameState.getDifficulty());
        gameState.createCharArray(gameState.getWordFromFile());

        while (gameState.getMistakeCount() < MAX_MISTAKES) {
            if (gameState.getCorrectAnswersCount() == gameState.getWordFromFile().length()) {
                PrintUtils.printVictory(gameState.getWordFromFile());
                return;
            }
            PrintUtils.printGallowsStage(gameState.getMistakeCount());
            PrintUtils.printHiddenWord(gameState.getHiddenWord());
            PrintUtils.printWrongLetters(gameState.getWrongLetters());
            PrintUtils.printMistakeCounter(gameState.getMistakeCount());

            String input = ReadingUtils.readInput();
            char guessLetter = input.charAt(0);

            if (!inputValidator.hasValidLength(input)) {
                PrintUtils.printLetterLengthError();
                continue;
            }

            if (inputValidator.isInCorrectAlphabet(guessLetter)) {
                PrintUtils.printWrongAlphabetUsed();
                continue;
            }

            if (!gameState.getWordFromFile().contains(String.valueOf(guessLetter)) && !gameState.getWrongLetters().contains(guessLetter)) {
                gameState.makeAMistake();
                gameState.getWrongLetters().add(guessLetter);
            } else if (gameState.getWrongLetters().contains(guessLetter)) {
                PrintUtils.printLetterWasTried();
                continue;
            }

            if (!gameValidator.contains(gameState.getHiddenWord(), guessLetter)) {
                TextProcessor.putLettersToList(gameState.getWordFromFile(), guessLetter, gameState.getHiddenWord());
                gameState.addToCorrectAnswersCount(TextProcessor.countOccurrences(gameState.getHiddenWord(), guessLetter));
            } else {
                PrintUtils.printLetterWasGuessed();
            }
        }
        if (gameState.getCorrectAnswersCount() != gameState.getWordFromFile().length()) {
            PrintUtils.printGameOver(gameState.getWordFromFile());
        }
    }

    private int getAnswer() {

        while (true) {
            if (ReadingUtils.isPresent()) {
                PrintUtils.printNumberTypeError();
                PrintUtils.printStartOrExitMenu();
                ReadingUtils.next();
                continue;
            }
            int answer = ReadingUtils.readChoice();
            if (answer >= TWO_DIGIT_NUMBER) {
                PrintUtils.printNumberLengthError();
                PrintUtils.printStartOrExitMenu();
                continue;
            }
            if (answer == PLAY || answer == QUIT) {
                return answer;
            } else {
                PrintUtils.printWrongNumberError();
                PrintUtils.printStartOrExitMenu();
            }
        }
    }

    private int getUserChoice() {

        while (true) {
            if (ReadingUtils.isPresent()) {
                PrintUtils.printNumberTypeError();
                PrintUtils.printDifficultyMenu();
                ReadingUtils.next();
                continue;
            }
            int difficulty = ReadingUtils.readChoice();
            if (difficulty >= TWO_DIGIT_NUMBER) {
                PrintUtils.printNumberLengthError();
                PrintUtils.printDifficultyMenu();
                continue;
            }
            if (difficulty == EASY || difficulty == MEDIUM || difficulty == HARD) {
                return difficulty;
            } else {
                PrintUtils.printWrongNumberError();
                PrintUtils.printDifficultyMenu();
            }
        }
    }
}