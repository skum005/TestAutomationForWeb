package pages;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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

    /**
     * Returns page title of the current page
     * @return
     */
    public String getPageTitle() {
        try {
            return driver.getTitle();
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new RuntimeException("Failed to retrieve page tile." + exception.getMessage());
        }
    }

    /**
     * Use this method to explicitly wait for a web element with a given timeout
     * @param element
     * @param timeoutInSeconds
     */
    public void waitForElement(WebElement element, int timeoutInSeconds) {
        try {
            WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
            explicitWait.until(ExpectedConditions.visibilityOf(element));
        } catch (Exception exception) {
            System.out.println("Error occurred while waiting for a web element" + exception.getMessage());
            exception.printStackTrace();
        }
    }

    /**
     * Use this method to switch to a child window by providing the parent window ID as an argument
     * @param parentWindowId
     */
    public void switchToChildWindow(String parentWindowId) {
        Set<String> windowIds = null;
        try {
            waitForChildWindow(5);
            windowIds = driver.getWindowHandles();
            Optional<String> childWindowId = windowIds.stream().filter(entry -> !entry.equalsIgnoreCase(parentWindowId)).findFirst();
            if(childWindowId.isPresent())
                driver.switchTo().window(childWindowId.get());
            else
                throw new RuntimeException("No child window present");
        } catch (Exception exception) {
            exception.printStackTrace();
            if(windowIds != null && windowIds.size() > 2)
                throw new RuntimeException("2 or More than 2 child windows are present. Expecting only one child window");
            else if (windowIds != null && windowIds.size() == 1)
                throw new RuntimeException("No child window present to switch to");
            else
                throw new RuntimeException("Error occurred while trying to switch to child window. " + exception.getMessage());
        }
    }

    /**
     * Waits for the child window to open(number of windows to be 2)
     * @param timeout
     */
    public void waitForChildWindow(int timeout) {
        try {
            WebDriverWait windowWait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
            windowWait.until(ExpectedConditions.numberOfWindowsToBe(2));
        } catch (Exception exception) {
            System.out.println("Error occurred while waiting for a child window" + exception.getMessage());
            exception.printStackTrace();
        }
    }

}
