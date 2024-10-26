package pages;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;
import java.util.Optional;
import java.util.Set;

public class CommonPage {

    WebDriver driver;
    private static final Logger logger = LogManager.getLogger(CommonPage.class);

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
            logger.info("Clicking on a web element");
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
            logger.info("Entering text " + inputText + " into a textbox/text area");
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
            logger.info("Selecting a value : " + visibleText + " from a dropdown");
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
     * Extracts text from the given web element
     * @param element
     * @return
     */
    public String extractTextFromElement(WebElement element) {
        try {
            logger.info("Extracting text from a web element");
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
            logger.info("Capturing screenshot and saving it in the path: " + fileName);
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
            logger.info("Launching website : " + _URL);
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
            logger.info("Extracting page title from the browser");
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
        logger.info("Waiting for the page to load");
        try {
            WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
            explicitWait.until(ExpectedConditions.visibilityOf(element));
        } catch (Exception exception) {
            logger.error("Error occurred while waiting for a web element" + exception.getMessage());
            exception.printStackTrace();
        }
    }

    /**
     * Use this method to switch to a child window by providing the parent window ID as an argument
     * @param parentWindowId
     */
    public void switchToChildWindow(String parentWindowId) {
        logger.info("Finding child window ID with the help of given parent window ID");
        Set<String> windowIds = null;
        try {
            waitForChildWindow(5);
            windowIds = driver.getWindowHandles();
            Optional<String> childWindowId = windowIds.stream().filter(entry -> !entry.equalsIgnoreCase(parentWindowId)).findFirst();
            if(childWindowId.isPresent()) {
                logger.info("Switching to child window ID: " + childWindowId.get());
                driver.switchTo().window(childWindowId.get());
            }
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
            logger.info("Waiting for child window ID");
            WebDriverWait windowWait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
            windowWait.until(ExpectedConditions.numberOfWindowsToBe(2));
        } catch (Exception exception) {
            logger.error("Error occurred while waiting for a child window" + exception.getMessage());
            exception.printStackTrace();
        }
    }


    /**
     * Waits for a page to load by checking document ready state
     * @param timeoutInSeconds
     */
    public void waitForPageToLoad(int timeoutInSeconds) {
        try {
            logger.info("Waiting for the page to load");
            WebDriverWait pageloadWait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
            pageloadWait.until((ExpectedCondition<Boolean>) wd ->
                    ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
        } catch (Exception exception) {
            logger.error("Failed to wait for the page to load" + exception.getMessage());
            exception.printStackTrace();
        }
    }


    /**
     * Scrolls to an element
     * @param element
     */
    public void scrollToElement(WebElement element) {
        logger.info("Scrolling to an element");
        try {
            executeJS("arguments[0].scrollIntoView(true);", element);
        } catch (Exception exception) {
            exception.printStackTrace();
            logger.error("Failed to scroll to element" + exception.getMessage());
        }
    }

    /**
     * Executes javascript on a web element
     * @param script
     * @param element
     */
    public void executeJS(String script, WebElement element) {
        logger.error("Executing Javascript: " + script);
        try {
            JavascriptExecutor jsExec = (JavascriptExecutor) driver;
            jsExec.executeScript(script, element);
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new RuntimeException("Failed to execute script: " + script);
        }
    }

    /**
     * This method can be used to temporarily change implicit wait time inorder to wait
     * for desired element shorter/longer. Make sure to set the implicit wait back to
     * default post performing required actions
     * @param seconds
     */
    public void setImplicitWait(int seconds) {
        try {
            logger.info("Temporarily setting implicit wait time to : " + seconds + " seconds");
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(seconds));
        } catch (Exception exception) {
            exception.printStackTrace();
            logger.error("Error occurred while changing implicit wait time");
        }
    }

    /**
     * validates the presence of the given element. Return true if present, false otherwise
     * @param element
     * @return
     */
    public boolean isElementPresent(WebElement element) {
        try {
            logger.info("validating the presence of an element");
            waitForPageToLoad(5);
            setImplicitWait(5);
            if (element.isDisplayed())
                return true;
        } catch (Exception exception) {
            exception.printStackTrace();
            logger.error("Element not found");
        } finally {
            setImplicitWait(30);
        }
        return false;
    }

}