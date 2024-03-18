import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileIO{
    static public String read(String path) throws IOException {
        return Files.readString(Path.of(path));
    }

    static public void write(String path, String message)throws IOException{
        Files.writeString(Path.of(path), message);
    }

    static public boolean isExistFile(String path){
        return Files.exists(Path.of(path));
    }
}
