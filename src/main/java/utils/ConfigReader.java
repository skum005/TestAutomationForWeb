package utils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Properties;

public class ConfigReader {

    Properties properties;

    public ConfigReader() {
        properties = new Properties();
        try {
            properties.load(new FileReader(Paths.get(System.getProperty("user.dir"),"src", "main", "resources", "config.properties").toString()));
        } catch (IOException e) {
            throw new RuntimeException("Error occurred while reading properties file " + e.getMessage());
        }
    }

    public String readProperty(String propertyName) {
        return properties.getProperty(propertyName);
    }

    public String getScreenshotDirBasePath() {
        return System.getProperty("user.dir") + File.separator + Paths.get("screenshots");
    }

    public String getEnvironment() {
        String env = System.getProperty("environment");
        return env == null ? readProperty("environment") : env;
    }

    public String getBrowserName() {
        String browser = System.getProperty("browser.name");
        return browser == null ? readProperty("browser.name") : browser;
    }

    public String getGridURL() {
        return readProperty("grid.url");
    }

    public String getAppURL() {
        String env = getEnvironment();
        if(env == null)
            throw new RuntimeException("Invalid Environment Specified. Provide valid application environment value like test/uat");
        return readProperty(getEnvironment());
    }

    public String getExecutionLocation() {
        return readProperty("exec.location");
    }

    public String getBrowserVersion() {
        String browserVersion = System.getProperty("browser.version");
        return browserVersion == null ? readProperty("browser.version") : browserVersion;
    }

    public String getOSName() {
        String _os_name = System.getProperty("os.name");
        return _os_name == null ? readProperty("os.name") : _os_name;
    }

}
