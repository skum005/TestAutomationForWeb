package stepdefinitions;

import io.cucumber.java.en.When;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
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
        enterOTP();
    }

    @Step("User enters OTP")
    public void enterOTP() {
        Allure.attachment("OTP - CANNOT BE READ", "NOT IMPLEMENTED YET");
        // read the OTP from DB or skip the otp screen
        //loginPage.enterOtp("");
    }

}
