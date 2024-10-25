package utils;

import org.openqa.selenium.WebDriver;
import pages.*;

public class PageObjectManager {

    private HomePage homePage;
    private LoginPage loginPage;
    private OrgDetailsPage orgDetailsPage;
    private CommonPage commonPage;

    public HomePage getHomePage(WebDriver driver) {
        return homePage == null ? new HomePage(driver) : homePage;
    }

    public LoginPage getLoginPage(WebDriver driver) {
        return loginPage == null ? new LoginPage(driver) : loginPage;
    }

    public OrgDetailsPage orgDetailsPage(WebDriver driver) {
        return orgDetailsPage == null ? new OrgDetailsPage(driver) : orgDetailsPage;
    }

    public CommonPage getCommonPage(WebDriver driver) {
        return commonPage == null ? new CommonPage(driver) : commonPage;
    }

}
