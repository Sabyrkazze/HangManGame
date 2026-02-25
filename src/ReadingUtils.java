import java.util.Scanner;

public class ReadingUtils {

    public String readInput(Scanner scanner) {
        System.out.println("Type the letter: ");
        String input = scanner.next().toUpperCase();
        return input;
    }

    public int readChoice(Scanner scanner){
        int difficulty = scanner.nextInt();
        scanner.nextLine();
        return difficulty;
    }
}
