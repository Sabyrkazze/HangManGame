import java.util.Scanner;

public class ReadingUtils {
    private static Scanner scanner;

    private ReadingUtils() {
    }

    public static void init(Scanner scanner) {
        ReadingUtils.scanner = scanner;
    }

    public static void next() {
        scanner.next();
    }

    public static String readInput() {
        System.out.println("Type the letter: ");
        return scanner.next().toUpperCase();
    }

    public static boolean isPresent() {
        return !scanner.hasNextInt();
    }

    public static int readChoice() {
        int difficulty = scanner.nextInt();
        scanner.nextLine();
        return difficulty;
    }
}
