package stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;
import utils.ConfigReader;
import utils.ScenarioContext;
import utils.TestContext;

import java.io.File;

public class Hooks {

    private Scenario scenario;
    private TestContext testContext;
    ScenarioContext scenarioContext;
    private ConfigReader configReader;
    private WebDriver driver;

    public Hooks(TestContext testContext) {
        this.testContext = testContext;
        this.scenarioContext = testContext.getScenarioContext();
        this.configReader = testContext.getConfigReader();
    }

    @Before
    public void setUp(Scenario scenario) {
        this.scenario = scenario;
        scenarioContext.setScenarioName(trimScenarioName(this.scenario));
        createScreenshotDirectory(scenarioContext.getScenarioName());
        if(configReader.getExecutionLocation().equalsIgnoreCase("local"))
            driver = testContext.getDriverFactory().createLocalDriver(configReader.getBrowserName());
        else {
            driver = testContext
                    .getDriverFactory()
                    .createRemoteDriver(
                            configReader.getGridURL(),
                            configReader.getBrowserName(),
                            configReader.getBrowserVersion(),
                            configReader.getOSName());
        }
        testContext.setDriver(driver);
    }

    @After
    public void tearDown() {
        if(scenario.isFailed()) {
            // implement code for taking screenshot when a test is failed
        }
        try {
            testContext.getDriver().quit();
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new RuntimeException("Failed to close browsers. " + exception.getMessage());
        }
    }

    private void createScreenshotDirectory(String scenarioName) {
        try {
            String directory = configReader.getScreenshotDirBasePath() + File.separator + scenarioName;
            File screenshotDir = new File(directory);
            scenarioContext.setScreenshotPath(directory + File.separator);
            if(!screenshotDir.exists()) {
                screenshotDir.mkdirs();
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new RuntimeException("Failed to create screenshot directory for the scenario: " + scenarioName);
        }
    }

    private String trimScenarioName(Scenario scenario) {
        String scenarioName = scenario.getName();
        if(scenarioName.contains(";")) {
            scenarioName = scenarioName.split(";")[0].trim();
        }
        if(scenarioName.length() > 15)
            scenarioName = scenarioName.substring(0,14);
        return scenarioName;
    }
}
