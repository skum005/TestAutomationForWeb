package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MyMessagesPage extends CommonPage {

    public MyMessagesPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "p+h2")
    private WebElement pageHeading;

    public String extractPageHeading() {
        return extractTextFromElement(pageHeading);
    }

}
