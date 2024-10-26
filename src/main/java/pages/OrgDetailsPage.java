package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.Optional;
import java.util.Random;

public class OrgDetailsPage extends CommonPage {
    
    private static final Logger logger = LogManager.getLogger(OrgDetailsPage.class);

    public OrgDetailsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "input#regNo")
    private WebElement regNoTextBox;

    @FindBy(css = "button#search")
    private WebElement searchButton;

    @FindBy(css = "input#organisationTradingName")
    private WebElement tradingNameTextBox;

    @FindBy(css = "input#postcodeSearchId")
    private WebElement postcodeTextBox;

    @FindBy(css = "button#searchId")
    private WebElement searchButton_ForAddress;

    @FindBy(xpath = "//*[text()='Address Results']/parent::div/following-sibling::div//div[@tabindex='0' and contains(@class, 'space-x-2')]")
    private List<WebElement> addresses;

    @FindBy(xpath = "//*[text()='Address Results']")
    private WebElement addressResultsLabel;

    @FindBy(css = "div#mui-component-select-typeofIndustry")
    private WebElement industryTypeComboBox;

    @FindBy(css = "li[role='option']")
    private List<WebElement> comboBoxOptions;

    @FindBy(css = "div#mui-component-select-noOfEmployees")
    private WebElement numOfWorkersComboBox;

    @FindBy(css = "input#payeRef")
    private WebElement payeeRefNumTextBox;

    @FindBy(css = "input#email2")
    private WebElement emailTextBox;

    @FindBy(css = "div#mui-component-select-hearAboutUs")
    private WebElement howDidYouHeadAbtUsComboBox;

    @FindBy(xpath = "//input[contains(@title, 'agreeing to now:pensions')]")
    private WebElement termsAndPrivacyCheckbox;

    @FindBy(css = "button#submitBusinessInfo")
    private WebElement continueButton;

    @FindBy(xpath = "//input[contains(@title, 'Staging date')]/parent::span/following-sibling::span")
    private WebElement stagingDateCalIcon;

    @FindBy(xpath = "//input[contains(@title, 'Staging date')]/parent::span/following-sibling::span//div[@role='row']/button")
    private List<WebElement> dates;

    @FindBy(xpath = "//div[text()='Apply']")
    private WebElement applyButton;

    public void searchOrganization(String orgRegNum) {
        logger.info("Entering Organisation Registration Number: " + orgRegNum);
        waitForElement(searchButton, 20);
        logger.info("Searching Organization using reg no: " + orgRegNum);
        inputText(regNoTextBox, orgRegNum);
        clickElement(searchButton);
    }

    public void enterTradingName(String tradingName) {
        logger.info("Entering Trading Name: " + tradingName);
        waitForElement(tradingNameTextBox, 10);
        logger.info("Entering trading Name: " + tradingName);
        inputText(tradingNameTextBox, tradingName);
    }

    public void enterAddressByPostcodeSearch(String postcode) {
        logger.info("Entering postcode: " + postcode + " and selecting random address from the result");
        inputText(postcodeTextBox, postcode);
        clickElement(searchButton_ForAddress);
        waitForElement(addressResultsLabel, 10);
        addresses.get(new Random().nextInt(addresses.size() - 1)).click();
    }

    public void selectTypeOfIndustry(String industryType) {
        logger.info("Selecting Industry Type: " + industryType);
        scrollToElement(industryTypeComboBox);
        clickElement(industryTypeComboBox);
        waitForPageToLoad(5);
        selectOptionFromComboBox(industryType);
    }

    private void selectOptionFromComboBox(String value) {
        Optional<WebElement> desiredElement = comboBoxOptions.stream().filter(option -> extractTextFromElement(option).equalsIgnoreCase(value)).findFirst();
        if(desiredElement.isPresent())
            clickElement(desiredElement.get());
        else {
            throw new RuntimeException("Failed to find option: " + value + " from the combo box");
        }
    }

    public void selectNumOfWorkers(String numOfWorkers) {
        logger.info("Selecting Number of Workers: " + numOfWorkers);
        scrollToElement(numOfWorkersComboBox);
        clickElement(numOfWorkersComboBox);
        waitForPageToLoad(5);
        selectOptionFromComboBox(numOfWorkers);
    }

    public void enterPayeeRefTextBox(String payeeRefNum) {
        logger.info("Entering PAYEE reference number: " + payeeRefNum);
        scrollToElement(payeeRefNumTextBox);
        inputText(payeeRefNumTextBox, payeeRefNum);
    }

    public void enterEmail(String email) {
        logger.info("Enter email address: " + email);
        scrollToElement(emailTextBox);
        inputText(emailTextBox, email);
    }

    public void selectTermsCheckbox() {
        logger.info("Selecting terms and privacy checkbox");
        scrollToElement(termsAndPrivacyCheckbox);
        clickElement(termsAndPrivacyCheckbox);
    }

    public void clickContinueButton() {
        logger.info("Clicking on continue button");
        clickElement(continueButton);
    }

    public void selectReferral(String referralName) {
        logger.info("Selecting a value from Where did you hear about us combo box");
        clickElement(howDidYouHeadAbtUsComboBox);
        waitForPageToLoad(5);
        selectOptionFromComboBox(referralName);
    }

    public void selectRandomStagingDate() {
        logger.info("Selecting staging date");
        stagingDateCalIcon.click();
        waitForPageToLoad(2);
        clickElement(dates.get(new Random().nextInt(dates.size()-1)));
        clickElement(applyButton);
    }

}
