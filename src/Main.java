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
                System.out.println("Тек 0 немесе 1 сандарын енгізіңіз!");
                scanner.nextLine();
                continue;
            }

            if(answer == 0){
                System.out.println("Сау болыңыз!");
                break;
            }else if (answer != 1){
                System.out.println("Тек 0 немесе 1 сандарын енгізіңіз!");
                continue;
            }

            System.out.println("Ойынды бастайық! ");
            System.out.println();

            int difficulty;
            while(true) {
                try {
                    difficulty = chooseDifficulty(scanner);
                    if (difficulty != 1 && difficulty != 2 && difficulty != 3) {
                        System.out.println("Тек 1, 2, 3 сандары берілуі керек!");
                        continue;
                    }
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("1,2 немесе 3 сандарын ғана енгізіңіз.");
                    scanner.nextLine();
                }
            }

            StringBuilder wrongLetters = new StringBuilder();
            String word = words.chooseAWord(getPathForDifficulty(difficulty)).toUpperCase();
//            String word = "ЫНТЫМАҚТАСТЫҚ";
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

                System.out.println("Әріпті енгізіңіз: ");
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
            System.out.println("Тек 1, 2, 3 сандары берілуі керек!");
            return null;
        }
    }

    private static boolean isValidLetter(char guessingLetter) {
        if(!Character.isDigit(guessingLetter)){
            return true;
        }else{
            System.out.println("Өтінемін, әріп енгізіңіз.");
            System.out.println();
            return false;
        }
    }

    private static boolean hasValidLength(String input) {
        if(input.length() == 1){
            return true;
        }else{
            System.out.println("Сіз тым ұзын жол енгіздіңіз. Тек бір әріп енгізіңіз.");
            System.out.println();
            return false;
        }
    }
    private static void showHiddenWord(ArrayList<Character> hiddenWord){
        System.out.print("Жасырылған сөз: ");
        System.out.println(hiddenWord);
    }
    private static void showWrongLetters(StringBuilder wrongLetters) {
        System.out.println("Қате әріптер: " + wrongLetters);
    }

    private static int chooseDifficulty(Scanner scanner){
        int difficulty;
        System.out.println("Ойын деңгейін таңдаңыз:");
        System.out.println("1 - Оңай");
        System.out.println("2 - Орташа");
        System.out.println("3 - Қиын");
        difficulty = scanner.nextInt();
        scanner.nextLine();
        return difficulty;
    }

    private static int startOrExit(Scanner scanner) {
        int answer;
        System.out.println("Ойынды бастау - 1");
        System.out.println("Шығу - 0");
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
        System.out.println("  ЖЕҢІС !");
        System.out.println();
        System.out.println("Жасырылған сөз: " + word.toUpperCase());
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
        System.out.println("  Таба алмадыңыз:( \n" +
                "  Ойын аяқталды.");
        System.out.println();
        System.out.println("Жасырылған сөз: " + word.toUpperCase());
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