package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends CommonPage {

    private static Logger logger = LogManager.getLogger(HomePage.class);

    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "#onetrust-banner-sdk button#onetrust-accept-btn-handler")
    private WebElement acceptCookieBtn;

    public void acceptCookies() {
        waitForElement(acceptCookieBtn, 10);
        logger.info("Clicking on \"Yes, I Agree\" button on the Cookies overlay");
        clickElement(acceptCookieBtn);
    }

}
