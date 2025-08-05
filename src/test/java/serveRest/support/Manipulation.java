package serveRest.support;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class Manipulation {
    private static Properties props = new Properties();

    static {
        try {
            String path = "src/test/java/serveRest/qa-properties.properties";
            props.load(Files.newInputStream(Paths.get(path)));
        } catch (Exception e) {
            throw new RuntimeException("Erro ao carregar propriedades", e);
        }
    }

    public static String getEmail() {
        return props.getProperty("email");
    }

    public static String getPassword() {
        return props.getProperty("password");
    }
}
