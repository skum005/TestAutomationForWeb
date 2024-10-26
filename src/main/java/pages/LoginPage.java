package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends CommonPage {

    private static final Logger logger = LogManager.getLogger(LoginPage.class);

    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "input[name='login']")
    private WebElement userNameTextBox;

    @FindBy(css = "input[name='password']")
    private WebElement passwordTextBox;

    @FindBy(css = "input[type='submit']")
    private WebElement goButton;

    public void performLogin(String username, String password) {
        logger.info("Logging into the application");
        inputText(userNameTextBox, username);
        inputText(passwordTextBox, password);
        clickElement(goButton);
    }
}
