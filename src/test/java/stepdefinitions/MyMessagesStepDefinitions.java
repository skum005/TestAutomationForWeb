package stepdefinitions;

import io.cucumber.java.en.Then;
import org.testng.Assert;
import pages.MyMessagesPage;
import utils.TestContext;

public class MyMessagesStepDefinitions extends BaseStepDefinitions {

    private MyMessagesPage myMessagesPage;

    public MyMessagesStepDefinitions(TestContext testContext) {
        super(testContext);
        myMessagesPage = pageObjectManager.getMyMessagesPage(driver);
    }

    @Then("My messages page should be displayed")
    public void validatePage() {
        System.out.println("Page heading is " + myMessagesPage.extractPageHeading());
        Assert.assertEquals(myMessagesPage.getPageTitle(), "My messages",
                "Expected My messages page");
    }

}
