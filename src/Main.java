import java.util.LinkedHashSet;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        try(Scanner scanner = new Scanner(System.in)){
            FileReadingUtils fileReading = new FileReadingUtils();
             GameProcess game = new GameProcess(
                new GameState(new LinkedHashSet<>(), fileReading),
                new PrintUtils(),
                new ReadingUtils(scanner),
                new FileReadingUtils(),
                new TextProcessor(),
                new Validation()
            );
            game.start();
        }
    }
}