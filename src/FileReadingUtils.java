import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;

public class FileReadingUtils {

    private static final Random random = new Random();

    private FileReadingUtils() {
    }

    public static String getPathForDifficulty(int levelOfDifficulty) {

        switch (levelOfDifficulty) {
            case 1 -> {
                return "resources/easy_words.txt";
            }
            case 2 -> {
                return "resources/medium_words.txt";
            }
            case 3 -> {
                return "resources/hard_words.txt";
            }
            default -> throw new IllegalArgumentException("Only specified numbers must be typed!");
        }
    }

    public static String readAWord(String filePath) {

        try {
            List<String> lines = Files.readAllLines(Paths.get(filePath));

            if (lines.isEmpty()) {
                throw new RuntimeException("File is empty!");
            }

            int randomIndex = random.nextInt(lines.size());
            return lines.get(randomIndex);

        } catch (IOException e) {
            throw new RuntimeException("Failed to read word from file: " + filePath, e);
        }
    }

}