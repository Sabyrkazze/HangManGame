import java.util.Scanner;

public class ReadingUtils {
    private final Scanner scanner;
    private final PrintUtils printUtils = new PrintUtils();

    public ReadingUtils(Scanner scanner){
        this.scanner = scanner;
    }

    public void next(){
        scanner.next();
    }

    public String readInput() {
        System.out.println("Type the letter: ");
        return scanner.next().toUpperCase();
    }

    public boolean isPresent(){
        return !scanner.hasNextInt();
    }

    public int readChoice(){
        int difficulty = scanner.nextInt();
        scanner.nextLine();
        return difficulty;
    }
}
