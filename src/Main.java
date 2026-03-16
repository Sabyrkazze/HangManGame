import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        try(Scanner scanner = new Scanner(System.in)){
             GameProcess game = new GameProcess(
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