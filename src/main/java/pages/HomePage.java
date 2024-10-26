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

    @FindBy(xpath = "//a[contains(@href,'news')]")
    private WebElement newsLink;

    @FindBy(xpath = "//a[contains(@href,'my_messages')]")
    private WebElement myMessagesLink;

    public void clickNewsLink() {
        logger.info("Clicking on news link");
        clickElement(newsLink);
    }

    public void clickMyMessagesLink() {
        logger.info("Clicking on My Messages link");
        clickElement(myMessagesLink);
    }

}
