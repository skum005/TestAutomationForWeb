package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class DriverFactory {

    private WebDriver driver;

    public WebDriver createLocalDriver(String browser) {
        switch (browser.toUpperCase()) {
            case "CHROME" :
                WebDriverManager.chromedriver().setup();
                break;
            case "FIREFOX" :
                WebDriverManager.firefoxdriver().setup();
                break;
            default:
                throw new RuntimeException("Browser name supplied did not match with any supported browsers");
        }
        WebDriver driver = driverMap.get(browser.toUpperCase()).get();
        driver.manage().window().maximize();
        return driver;
    }

    public WebDriver createRemoteDriver(String gridURL, String browserName, String browserVersion, String _os_name) {
        try {
            WebDriver driver = new RemoteWebDriver(new URL(gridURL), getRemoteDriverCapabilities(browserName, browserVersion, _os_name));
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
            return driver;
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new RuntimeException("Error occurred while creating a remote webdriver" + exception.getMessage());
        }
    }

    public DesiredCapabilities getRemoteDriverCapabilities(String browserName, String browserVersion, String platformName) {
        try {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setBrowserName(browserName);
            capabilities.setCapability(CapabilityType.BROWSER_VERSION, browserVersion);
            capabilities.setCapability(CapabilityType.PLATFORM_NAME, platformName);
            // enable to below if required. Make sure to add the same parameter(_os_version) to this method
            //capabilities.setCapability("os.version", _os_version);
            return capabilities;
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new RuntimeException("Error occurred while setting driver config" + exception.getMessage());
        }
    }

    private Supplier<WebDriver> chromeDriver = () -> new ChromeDriver();

    private Supplier<WebDriver> firefoxDriver = () -> new FirefoxDriver();

    Map<String, Supplier<WebDriver>> driverMap = new HashMap<>() {
        {
            put("CHROME", chromeDriver);
            put("FIREFOX", firefoxDriver);
        }
    };

}
