package pages;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.util.List;

public class CommonPage {

    WebDriver driver;

    public CommonPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Method to click a web element
     * @param element
     */
    public void clickElement(WebElement element) {
        try {
            element.click();
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new RuntimeException("Error occurred while clicking an element" + exception.getMessage());
        }
    }

    /**
     * Method to input text into text boxes/text area elements
     * @param textBoxElement element into which text needs to be entered
     * @param inputText required text that needs to be enetred
     */
    public void inputText(WebElement textBoxElement, String inputText) {
        try {
            textBoxElement.sendKeys(inputText);
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new RuntimeException("Error occurred while trying text into a text field/area" + exception.getMessage());
        }
    }

    /**
     * Selects a value from a dropdown by utilizing the select class of Selenium
     * @param dropdownElement
     * @param visibleText
     */
    public void selectFromDropdown(WebElement dropdownElement, String visibleText) {
        try {
            Select select = new Select(dropdownElement);
            select.selectByVisibleText(visibleText);
        } catch (Exception exception) {
            exception.printStackTrace();
            if(!dropdownElement.getTagName().equalsIgnoreCase("select")) {
                throw new RuntimeException("This method doesnt support selecting from a dropdown for elements not having select tags. Please use the method : ");
            } else {
                throw new RuntimeException("Error occurred while selecting a value from a dropdown");
            }
        }
    }

    /**
     * Select a value from a dropdown
     * @param dropdownElement
     * @param dropdownValueElements
     * @param desiredValue
     */
    public void selectFromDropdown_NonSelectElements(WebElement dropdownElement, List<WebElement> dropdownValueElements, String desiredValue) {
        boolean isClicked = false;
        try {
            clickElement(dropdownElement);
            for (WebElement element : dropdownValueElements) {
                if (extractTextFromElement(element).equalsIgnoreCase(desiredValue)) {
                    clickElement(element);
                    isClicked = true;
                    break;
                }
            }
            if(!isClicked)
                throw new RuntimeException("Failed to select a value from dropdown");
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new RuntimeException("Failed to select a value from dropdown" + exception.getMessage());
        }
    }

    /**
     * Extracts text from the given web element
     * @param element
     * @return
     */
    public String extractTextFromElement(WebElement element) {
        try {
            return element.getText();
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new RuntimeException("Error occurred while extracting text from element. " + exception.getMessage());
        }
    }

    /**
     * Captures a screenshot of the webpage and saves in the specified path
     * @param fileName
     */
    public void captureScreenshot(String fileName) {
        try {
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenshot, new File(fileName));
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new RuntimeException("Error occurred while capturing screenshot");
        }
    }

    /**
     * Loads a website in the browser
     * @param _URL
     */
    public void loadWebsite(String _URL) {
        try {
            driver.get(_URL);
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new RuntimeException("Error occurred while loading website: " + _URL);
        }
    }

    public String getPageTitle() {
        try {
            return driver.getTitle();
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new RuntimeException("Failed to retrieve page tile." + exception.getMessage());
        }
    }

}
