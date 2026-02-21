import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Words words = new Words();

        while(true){
            int answer;
            String word = words.chooseAWord().toUpperCase();
            try {
                answer = startOrExit(scanner);
            } catch (InputMismatchException e) { // InputMismatchException
                System.out.println("Тек 0 немесе 1 сандарын енгізіңіз!");
                scanner.nextLine();
                continue;
            }

            if(answer == 1){
                StringBuilder wrongLetters = new StringBuilder();
                ArrayList<Character> hiddenWord = fillWithUnderscore(word);

                System.out.println("Ойынды бастайық! ");
                System.out.println();

                int loseCount = 0;
                int winCount = 0;
                int lineToRead = 0;

                while(loseCount < 6){
                    if(winCount == word.length()){
                        congratulateUser(word);
                        break;
                    }
                    showHiddenWordAndWrongLetters(hiddenWord, wrongLetters);
                    getGallowState(lineToRead);

                    System.out.println("Әріпті енгізіңіз: ");
                    String input = scanner.next().toUpperCase();
                    if (input.length() == 1){
                        char guessingLetter = input.charAt(0);

                        if (!Character.isDigit(guessingLetter)){
                            if (!word.contains(String.valueOf(guessingLetter))){
                                getGallowState(lineToRead);
                                lineToRead += 7;
                                loseCount++;
                                if (!String.valueOf(wrongLetters).contains(String.valueOf(guessingLetter))){
                                    wrongLetters.append(guessingLetter).append(" ");
                                }
                            }else {
                                if(!hiddenWord.contains(guessingLetter)){
                                    winCount = putGuessedLettersToList(word, guessingLetter, hiddenWord, winCount);
                                    getGallowState(lineToRead);
                                }else{
                                    getGallowState(lineToRead);
                                    lineToRead += 7;
                                    loseCount++;
                                }
                            }
                        }else {
                            System.out.println("Өтінемін, әріп енгізіңіз.");
                            System.out.println();
                        }
                    }else{
                        System.out.println("Сіз тым ұзын жол енгіздіңіз. Тек бір әріп енгізіңіз.");
                        System.out.println();
                    }

                }
                if (winCount != word.length()){
                    getGallowState(lineToRead);
                    notifyDefeat(word);
                }
            } else if (answer == 0) {
                System.out.println("Сау болыңыз!");
                break;
            }else {
                System.out.println("Тек 0 немесе 1 сандарын енгізіңіз!");
            }
        }
    }

    private static void showHiddenWordAndWrongLetters(ArrayList<Character> hiddenWord, StringBuilder wrongLetters) {
        System.out.print("Жасырылған сөз: ");
        System.out.println(hiddenWord);
        System.out.println("Қате әріптер: " + wrongLetters);
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