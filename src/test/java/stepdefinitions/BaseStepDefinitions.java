package stepdefinitions;

import io.cucumber.java.en.Given;
import org.openqa.selenium.WebDriver;
import pages.CommonPage;
import utils.ConfigReader;
import utils.PageObjectManager;
import utils.ScenarioContext;
import utils.TestContext;

import java.io.File;

public class BaseStepDefinitions {

    protected TestContext testContext;
    protected ScenarioContext scenarioContext;
    protected WebDriver driver;
    protected ConfigReader configReader;
    protected PageObjectManager pageObjectManager;
    protected CommonPage commonPage;
    protected String screenshotFolder;

    public BaseStepDefinitions(TestContext testContext) {
        this.testContext = testContext;
        this.scenarioContext = testContext.getScenarioContext();
        driver = testContext.getDriver();
        configReader = testContext.getConfigReader();
        pageObjectManager = testContext.getPageObjectManager();
        commonPage = pageObjectManager.getCommonPage(driver);
        screenshotFolder = scenarioContext.getScreenshotPath();
    }



}
