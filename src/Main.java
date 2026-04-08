import java.util.LinkedHashSet;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        try (Scanner scanner = new Scanner(System.in)) {
            ReadingUtils.init(scanner);
            GameProcess game = new GameProcess(
                    new GameState(new LinkedHashSet<>()),
                    new TextProcessor(),
                    new InputValidator(),
                    new GameValidator()
            );
            game.start();
        }
    }
}