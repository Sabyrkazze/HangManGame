import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class HangingMan {
    public String getGallowStateLine(int lineToRead){
        try {
            return Files.readAllLines(Paths.get("resources/gallow_state.txt")).get(lineToRead);
        } catch (IOException e) {
            e.getMessage();
        }
        return "";
    }
}
