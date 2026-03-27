import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;

public class FileReadingUtils {

    private final Random random = new Random();

    public String getPathForDifficulty(int levelOfDifficulty){

        switch(levelOfDifficulty){
            case 1 -> {
                return "resources/easy_words.txt";
            }
            case 2 -> {
                return "resources/medium_words.txt";
            }
            case 3 -> {
                return "resources/hard_words.txt";
            }
            default -> {
                System.out.println("Only specified numbers must be typed!");
                return null;
            }
        }
    }

    public String readAWord(String filePath){

        try {
            List<String> lines = Files.readAllLines(Paths.get(filePath));

            if(lines.isEmpty()){
                throw new RuntimeException("File is empty!");
            }

            int randomIndex = random.nextInt(lines.size());
            return lines.get(randomIndex);

        } catch (IOException e) {
            throw new RuntimeException("Failed to read word from file: " + filePath, e);
        }
    }

    public String readGallowStateLine(int lineToRead){
        try {
            return Files.readAllLines(Paths.get("resources/gallow_state.txt")).get(lineToRead);
        } catch (IOException e) {
            e.getMessage();
        }
        return "";
    }

    public void readGallowState(int lineToRead){
        FileReadingUtils fileReading = new FileReadingUtils();
        for (int i = lineToRead; i < lineToRead + 7; i++) {
            System.out.println(fileReading.readGallowStateLine(i));
        }
    }
}