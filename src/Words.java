import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;

public class Words {
    Random random = new Random();
    public String chooseAWord(String filePath){
        int randomNumber = random.nextInt(20);
        try {
            String word = Files.readAllLines(Paths.get(filePath)).get(randomNumber);
            return word;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}