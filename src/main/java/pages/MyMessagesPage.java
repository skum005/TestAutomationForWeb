package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MyMessagesPage extends CommonPage {

    private static final Logger logger = LogManager.getLogger(MyMessagesPage.class);

    public MyMessagesPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "p+h2")
    private WebElement pageHeading;

    public String extractPageHeading() {
        logger.info("Extracting page title on MyMessages screen");
        return extractTextFromElement(pageHeading);
    }

}
