import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Words words = new Words();

        while(true){
            int answer;
            try {
                answer = startOrExit(scanner);
            } catch (InputMismatchException e) {
                System.out.println("Type only numbers 1 or 0!");
                scanner.nextLine();
                continue;
            }

            if(answer == 0){
                System.out.println("Good Bye!");
                break;
            }else if (answer != 1){
                System.out.println("Type only numbers 1 or 0!");
                continue;
            }

            System.out.println("Let's start! ");
            System.out.println();

            int difficulty;
            while(true) {
                try {
                    difficulty = chooseDifficulty(scanner);
                    if (difficulty != 1 && difficulty != 2 && difficulty != 3) {
                        System.out.println("Only numbers 1, 2, 3 must be typed!");
                        continue;
                    }
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Only numbers 1, 2, 3 must be typed!");
                    scanner.nextLine();
                }
            }

            StringBuilder wrongLetters = new StringBuilder();
            String word = words.chooseAWord(getPathForDifficulty(difficulty)).toUpperCase();
//            String word = "LIGHTNING";
            ArrayList<Character> hiddenWord = fillWithUnderscore(word);

            int loseCount = 0;
            int winCount = 0;
            int lineToRead = 0;

            while (loseCount < 6) {
                if (winCount == word.length()) {
                    congratulateUser(word);
                    break;
                }
                showHiddenWord(hiddenWord);
                showWrongLetters(wrongLetters);
                getGallowState(lineToRead);

                System.out.println("Type the letter: ");
                String input = scanner.next().toUpperCase();
                char guessingLetter = input.charAt(0);

                if (!hasValidLength(input)) continue;
                if (!isValidLetter(guessingLetter)) continue;
                if (!word.contains(String.valueOf(guessingLetter))) {
                    getGallowState(lineToRead);
                    lineToRead += 7;
                    loseCount++;
                    if (!String.valueOf(wrongLetters).contains(String.valueOf(guessingLetter))) {
                        wrongLetters.append(guessingLetter).append(" ");
                    }
                } else {
                    if (!hiddenWord.contains(guessingLetter)) {
                        winCount = putGuessedLettersToList(word, guessingLetter, hiddenWord, winCount);
                        getGallowState(lineToRead);
                    } else {
                        getGallowState(lineToRead);
                        lineToRead += 7;
                        loseCount++;
                    }
                }
            }
            if (winCount != word.length()) {
                getGallowState(lineToRead);
                notifyDefeat(word);
            }
        }
    }

    private static String getPathForDifficulty(int levelOfDifficulty){
        if (levelOfDifficulty == 1){
            return "resources/easy_words.txt";
        } else if (levelOfDifficulty == 2) {
            return "resources/medium_words.txt";
        } else if (levelOfDifficulty == 3) {
            return "resources/hard_words.txt";
        }else {
            System.out.println("Only numbers 1, 2, 3 must be typed!");
            return null;
        }
    }

    private static boolean isValidLetter(char guessingLetter) {
        if(!Character.isDigit(guessingLetter)){
            return true;
        }else{
            System.out.println("Please, enter a letter.");
            System.out.println();
            return false;
        }
    }

    private static boolean hasValidLength(String input) {
        if(input.length() == 1){
            return true;
        }else{
            System.out.println("Type only one letter!");
            System.out.println();
            return false;
        }
    }
    private static void showHiddenWord(ArrayList<Character> hiddenWord){
        System.out.print("Hidden word: ");
        System.out.println(hiddenWord);
    }
    private static void showWrongLetters(StringBuilder wrongLetters) {
        System.out.println("Wrong letters: " + wrongLetters);
    }

    private static int chooseDifficulty(Scanner scanner) {
        int difficulty;
        System.out.println("Choose difficulty: ");
        System.out.println("1 - Easy");
        System.out.println("2 - Medium");
        System.out.println("3 - Hard");
        difficulty = scanner.nextInt();
        scanner.nextLine();
        return difficulty;
    }

    private static int startOrExit(Scanner scanner) {
        int answer;
        System.out.println("Start the game - 1");
        System.out.println("Exit - 0");
        answer = scanner.nextInt();
        scanner.nextLine();
        return answer;
    }

    private static ArrayList<Character> fillWithUnderscore(String word) {
        ArrayList<Character> hiddenWord = new ArrayList<>();
        for (int i = 0; i < word.length(); i++) {
            hiddenWord.add('_');
        }
        return hiddenWord;
    }

    private static void congratulateUser(String word) {
        System.out.println("  VICTORY !");
        System.out.println();
        System.out.println("Hidden word: " + word.toUpperCase());
        System.out.println();
    }

    private static int putGuessedLettersToList(String word, char guessingLetter, ArrayList<Character> hiddenWord, int winCount) {
        ArrayList<Integer> indexes = letterIndexesInWord(word, guessingLetter);
        for (int i = 0; i < indexes.size(); i++) {
            hiddenWord.set(indexes.get(i), guessingLetter);
            winCount++;
        }
        return winCount;
    }

    private static void notifyDefeat(String word) {
        System.out.println("  You couldn't find the letter:( \n" +
                "  GAME OVER.");
        System.out.println();
        System.out.println("Hidden word: " + word.toUpperCase());
        System.out.println();
    }

    public static ArrayList<Integer> letterIndexesInWord(String word, char letterToFind){
        ArrayList<Integer> indexes = new ArrayList<>();
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == letterToFind){
                indexes.add(i);
            }
        }
        return indexes;
    }

    public static void getGallowState(int lineToRead){
        HangingMan hangingMan = new HangingMan();
        for (int i = lineToRead; i < lineToRead + 7; i++) {
            System.out.println(hangingMan.getGallowStateLine(i));
        }
    }
}