package exercise;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Files;


// BEGIN
public class App {

    public static void save(Path path, Car car) throws IOException {
        var content = car.serialize();
        Files.writeString(path, content);
    }

    public static Car extract(Path path) throws IOException {
        var content = Files.readString(path);
        return Car.unserialize(content);
    }
}
// END
