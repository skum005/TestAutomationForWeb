package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.Optional;
import java.util.Random;

public class OrgDetailsPage extends CommonPage {

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
        waitForElement(searchButton, 20);
        System.out.println("Searching Organization using reg no: " + orgRegNum);
        inputText(regNoTextBox, orgRegNum);
        clickElement(searchButton);
    }

    public void enterTradingName(String tradingName) {
        waitForElement(tradingNameTextBox, 10);
        System.out.println("Entering trading Name: " + tradingName);
        inputText(tradingNameTextBox, tradingName);
    }

    public void enterAddressByPostcodeSearch(String postcode) {
        System.out.println("Entering postcode: " + postcode + " and selecting random address from the result");
        inputText(postcodeTextBox, postcode);
        clickElement(searchButton_ForAddress);
        waitForElement(addressResultsLabel, 10);
        addresses.get(new Random().nextInt(addresses.size() - 1)).click();
    }

    public void selectTypeOfIndustry(String industryType) {
        System.out.println("Selecting Industry Type: " + industryType);
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
        System.out.println("Selecting Number of Workers: " + numOfWorkers);
        scrollToElement(numOfWorkersComboBox);
        clickElement(numOfWorkersComboBox);
        waitForPageToLoad(5);
        selectOptionFromComboBox(numOfWorkers);
    }

    public void enterPayeeRefTextBox(String payeeRefNum) {
        System.out.println("Entering PAYEE reference number: " + payeeRefNum);
        scrollToElement(payeeRefNumTextBox);
        inputText(payeeRefNumTextBox, payeeRefNum);
    }

    public void enterEmail(String email) {
        System.out.println("Enter email address: " + email);
        scrollToElement(emailTextBox);
        inputText(emailTextBox, email);
    }

    public void selectTermsCheckbox() {
        System.out.println("Selecting terms and privacy checkbox");
        scrollToElement(termsAndPrivacyCheckbox);
        clickElement(termsAndPrivacyCheckbox);
    }

    public void clickContinueButton() {
        System.out.println("Clicking on continue button");
        clickElement(continueButton);
    }

    public void selectReferral(String referralName) {
        System.out.println("Selecting a value from Where did you hear about us combo box");
        clickElement(howDidYouHeadAbtUsComboBox);
        waitForPageToLoad(5);
        selectOptionFromComboBox(referralName);
    }

    public void selectRandomStagingDate() {
        System.out.println("Selecting staging date");
        stagingDateCalIcon.click();
        waitForPageToLoad(2);
        clickElement(dates.get(new Random().nextInt(dates.size()-1)));
        clickElement(applyButton);
    }

}
