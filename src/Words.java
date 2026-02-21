import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;

public class Words {
    Random random = new Random();

    public String chooseAWord(){
        int randomNumber = random.nextInt(58);
        try {
            String word = Files.readAllLines(Paths.get("resources/words.txt")).get(randomNumber);
            return word;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
