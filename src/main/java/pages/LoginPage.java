package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends CommonPage {

    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "input#userIdTxt")
    private WebElement userNameTextBox;

    @FindBy(css = "input#passwordTxt")
    private WebElement passwordTextBox;

    @FindBy(css = "button#submitBtn")
    private WebElement loginBtn;

    @FindBy(css = "input[name='verifiedCode']")
    private WebElement otpTextBox;

    @FindBy(css = "button#verifyBtn")
    private WebElement confirmOtpBtn;

    public void performLogin(String username, String password) {
        waitForElement(userNameTextBox, 10);
        inputText(userNameTextBox, username);
        inputText(passwordTextBox, password);
        clickElement(loginBtn);
        waitForPageToLoad(5);
    }

   public void enterOtp(String otp) {
        waitForElement(otpTextBox, 10);
        System.out.println("Entering OTP");
        inputText(otpTextBox, otp);
        clickElement(confirmOtpBtn);
        waitForPageToLoad(5);
   }
}