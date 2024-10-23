package utils;

import org.openqa.selenium.WebDriver;

public class TestContext {

    private DriverFactory driverFactory;
    private ScenarioContext scenarioContext;
    private PageObjectManager pageObjectManager;
    private ConfigReader configReader;
    private WebDriver driver;

    public TestContext() {
        driverFactory = new DriverFactory();
        scenarioContext = new ScenarioContext();
        pageObjectManager = new PageObjectManager();
        configReader = new ConfigReader();
    }

    public DriverFactory getDriverFactory() {
        return driverFactory;
    }

    public ScenarioContext getScenarioContext() {
        return scenarioContext;
    }

    public PageObjectManager getPageObjectManager() {
        return pageObjectManager;
    }

    public ConfigReader getConfigReader() {
        return configReader;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    public WebDriver getDriver() {
        return driver;
    }
}
