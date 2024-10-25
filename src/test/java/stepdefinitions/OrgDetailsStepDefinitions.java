package stepdefinitions;

import data.OrgDetails;
import io.cucumber.java.DataTableType;
import io.cucumber.java.en.When;
import pages.OrgDetailsPage;
import utils.TestContext;

import java.util.Map;

public class OrgDetailsStepDefinitions extends BaseStepDefinitions {

    OrgDetailsPage orgDetailsPage;

    public OrgDetailsStepDefinitions(TestContext testContext) {
        super(testContext);
        orgDetailsPage = pageObjectManager.orgDetailsPage(driver);
    }

    @DataTableType
    public OrgDetails orgData(Map<String, String> entry) {
        return new OrgDetails(
                entry.get("tradingName"),
                entry.get("postcode"),
                entry.get("industryType"),
                entry.get("numOfWorkers"),
                entry.get("payeeRefNumber"),
                entry.get("emailAddress"),
                entry.get("howDidYouFindUs"),
                entry.get("stagingOrStartDate"));
    }

    @When("User searches for an organisation register number {string}")
    public void searchOrg(String regNum) {
        orgDetailsPage.searchOrganization(regNum);
    }

    @When("User fills organisation details as below")
    public void fillOrgDetails(OrgDetails orgData) {
        if(orgData.getTradingName() != null)
            orgDetailsPage.enterTradingName(orgData.getTradingName());
        if(orgData.getPostcode() != null)
            orgDetailsPage.enterAddressByPostcodeSearch(orgData.getPostcode());
        if(orgData.getIndustryType() != null)
            orgDetailsPage.selectTypeOfIndustry(orgData.getIndustryType());
        if(orgData.getNumOfWorkers() != null)
            orgDetailsPage.selectNumOfWorkers(orgData.getNumOfWorkers());
        if(orgData.getPayeeRefNumber() != null)
            orgDetailsPage.enterPayeeRefTextBox(orgData.getPayeeRefNumber());
        orgDetailsPage.selectRandomStagingDate();
        if(orgData.getEmailAddress() != null)
            orgDetailsPage.enterEmail(orgData.getEmailAddress());
        if(orgData.getHowDidYouFindUs() != null)
            orgDetailsPage.selectReferral(orgData.getHowDidYouFindUs());
    }

    @When("User agrees to terms of use & privacy policy of NowPensions")
    public void agreeTermsAndPrivacy() {
        orgDetailsPage.selectTermsCheckbox();
        orgDetailsPage.captureScreenshot(screenshotFolder + "OrgDetails.PNG");
    }

    @When("User clicks on continue button")
    public void clickContinueBtn() {
        orgDetailsPage.clickContinueButton();
        orgDetailsPage.waitForPageToLoad(10);
    }

}
