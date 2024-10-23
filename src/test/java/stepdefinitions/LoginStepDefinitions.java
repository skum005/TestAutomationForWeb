package stepdefinitions;

import io.cucumber.java.en.When;
import pages.LoginPage;
import utils.TestContext;

public class LoginStepDefinitions extends BaseStepDefinitions {

    private LoginPage loginPage;

    public LoginStepDefinitions(TestContext testContext) {
        super(testContext);
        loginPage = pageObjectManager.getLoginPage(driver);
    }

    @When("User logs in with username as {string} and password as {string}")
    public void login(String username, String password) {
        loginPage.performLogin(username, password);
        loginPage.captureScreenshot(screenshotFolder + "MyMessages.png");
    }

}
