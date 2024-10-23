package utils;

import org.openqa.selenium.WebDriver;
import pages.CommonPage;
import pages.HomePage;
import pages.LoginPage;
import pages.MyMessagesPage;

public class PageObjectManager {

    private HomePage homePage;
    private LoginPage loginPage;
    private MyMessagesPage myMessagesPage;
    private CommonPage commonPage;

    public HomePage getHomePage(WebDriver driver) {
        return homePage == null ? new HomePage(driver) : homePage;
    }

    public LoginPage getLoginPage(WebDriver driver) {
        return loginPage == null ? new LoginPage(driver) : loginPage;
    }

    public MyMessagesPage getMyMessagesPage(WebDriver driver) {
        return myMessagesPage == null ? new MyMessagesPage(driver) : myMessagesPage;
    }

    public CommonPage getCommonPage(WebDriver driver) {
        return commonPage == null ? new CommonPage(driver) : commonPage;
    }

}
