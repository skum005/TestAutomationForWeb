package stepdefinitions;

import io.cucumber.java.en.Given;
import pages.HomePage;
import utils.TestContext;

public class HomePageStepDefinitions extends BaseStepDefinitions {

    private HomePage homePage;

    public HomePageStepDefinitions(TestContext testContext) {
        super(testContext);
        homePage = pageObjectManager.getHomePage(driver);
    }

    @Given("User is on login page")
    public void openLandingPage() {
        commonPage.loadWebsite(configReader.getAppURL());
        homePage.acceptCookies();
        commonPage.captureScreenshot(screenshotFolder + "LoginPage.png");
    }

}
