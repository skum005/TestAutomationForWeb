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

    @FindBy(css = "#onetrust-banner-sdk button#onetrust-accept-btn-handler")
    private WebElement acceptCookieBtn;

    public void acceptCookies() {
        waitForElement(acceptCookieBtn, 10);
        System.out.println("Clicking on \"Yes, I Agree\" button on the Cookies overlay");
        clickElement(acceptCookieBtn);
    }

}
