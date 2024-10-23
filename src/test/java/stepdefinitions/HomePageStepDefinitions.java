package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import pages.HomePage;
import utils.TestContext;

public class HomePageStepDefinitions extends BaseStepDefinitions {

    private HomePage homePage;

    public HomePageStepDefinitions(TestContext testContext) {
        super(testContext);
        homePage = pageObjectManager.getHomePage(driver);
    }

    @Given("User is on landing page")
    public void openLandingPage() {
        commonPage.loadWebsite(configReader.getAppURL());
        commonPage.captureScreenshot(screenshotFolder + "HomePage.png");
    }

    @When("User clicks on MyMessages link")
    public void clickMessagesLink() {
        homePage.clickMyMessagesLink();
        homePage.captureScreenshot(screenshotFolder + "LoginPage.png");
    }

    @When("User clicks on News link")
    public void clickNewsLink() {
        homePage.clickNewsLink();
        homePage.captureScreenshot(screenshotFolder + "NewPage.png");
    }

}
