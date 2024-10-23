package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends CommonPage {

    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a[contains(@href,'news')]")
    private WebElement newsLink;

    @FindBy(xpath = "//a[contains(@href,'my_messages')]")
    private WebElement myMessagesLink;

    public void clickNewsLink() {
        clickElement(newsLink);
    }

    public void clickMyMessagesLink() {
        clickElement(myMessagesLink);
    }

}
