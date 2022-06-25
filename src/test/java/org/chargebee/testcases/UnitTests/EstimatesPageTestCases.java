package org.chargebee.testcases.UnitTests;

import com.aventstack.extentreports.MediaEntityBuilder;
import org.assertj.core.api.Assertions;
import org.chargebee.assignment.pages.EstimatesPage;
import org.chargebee.assignment.pages.LoginPage;
import org.chargebee.assignment.utility.BaseClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class EstimatesPageTestCases extends BaseClass {

    private WebDriver driver;
    private EstimatesPage estimatesPage;

    @BeforeClass
    public void setup() {
        driver = browserEngine.initializeWebDriver(driver, readPropertyFile.getBrowser(), readPropertyFile.getAppUrl());
        LoginPage login = PageFactory.initElements(driver, LoginPage.class);
        login.loginToCraterApp();
        estimatesPage = PageFactory.initElements(driver, EstimatesPage.class);
    }

    @Test(priority = 1, description = "Verify create new estimate functionality")
    public void createNewEstimate() {
        String randomNumber = getCurrentDateTime().substring(17);
        testSteps = reports.createTest("Verify create new estimate functionality");
        String actualMessage = estimatesPage
                .createNewEstimate("create" + randomNumber, "Test" + randomNumber, "20.00")
                .getConfirmationMessage();
        Assertions.assertThat(actualMessage).as("Verify Estimate is created successfully").isEqualTo("Estimate created successfully");
        testSteps.info("New Estimate Created");
        estimatesPage
                .searchForEstimates("create" + randomNumber, "DRAFT")
                .deleteEstimate()
                .closeConfirmationBox();

    }

    @Test(priority = 2, description = "Verify edit estimate functionality")
    public void editEstimate() {
        String randomNumber = getCurrentDateTime().substring(17);
        testSteps = reports.createTest("Verify edit estimate functionality");
        String actualMessage = estimatesPage
                .createNewEstimate("edit" + randomNumber, "edit", "20.00")
                .searchForEstimates("edit" + randomNumber, "DRAFT")
                .updateQuantity("2")
                .getConfirmationMessage();
        Assertions.assertThat(actualMessage).as("Verify Estimate is updated successfully").isEqualTo("Estimate updated successfully");
        testSteps.info("Quantity of items in estimate updated successfully to : 2 from : 1");
        estimatesPage
                .searchForEstimates("edit" + randomNumber, "DRAFT")
                .deleteEstimate()
                .closeConfirmationBox();
    }

    @Test(priority = 3, description = "Verify delete estimate functionality")
    public void deleteEstimate() {
     String randomNumber = getCurrentDateTime().substring(17);
        testSteps = reports.createTest("Verify delete estimate functionality");
        String actualMessage = estimatesPage
                .createNewEstimate("delete" + randomNumber, "delete", "20.00")
                .searchForEstimates("delete" + randomNumber, "DRAFT")
                .deleteEstimate()
                .getConfirmationMessage();
        Assertions.assertThat(actualMessage).as("Verify Estimate is deleted successfully").isEqualTo("Estimate deleted successfully");
        testSteps.info("Estimate Deleted Successfully");
    }


    @Test(priority = 4, description = "Verify change of status from DRAFT to SENT for an estimate")
    public void verifyStatusChangeAfterSendingToCustomer() {
        String randomNumber = getCurrentDateTime().substring(17);
        testSteps = reports.createTest("Verify change of status from DRAFT to SENT for an estimate");
        String statusBeforeClickingSent = estimatesPage
                .createNewEstimate("sent" + randomNumber, "sent", "20.00")
                .searchForEstimates("sent" + randomNumber, "DRAFT")
                .getCurrentStatusOfEstimate();

        Assertions.assertThat(statusBeforeClickingSent).as("Status of estimate should be : DRAFT").isEqualTo("DRAFT");

        String statusAfterClickingSent = estimatesPage.markAsSent()
                .refreshEstimatesPage()
                .getCurrentStatusOfEstimate();

        Assertions.assertThat(statusAfterClickingSent).as("Status of estimate should be : SENT").isEqualTo("SENT");

        testSteps.info("Status updated from DRAFT to SENT");
        estimatesPage
                .searchForEstimates("sent" + randomNumber, "SENT")
                .deleteEstimate()
                .closeConfirmationBox();
    }

    @Test(priority = 5, description = "Verify change of status from DRAFT to ACCEPTED for an estimate")
    public void verifyStatusChangeAfterItIsAcceptedByCustomer() {
        String randomNumber = getCurrentDateTime().substring(17);
        testSteps = reports.createTest("Verify change of status from DRAFT to ACCEPTED for an estimate");
        String statusBeforeClickingSent = estimatesPage
                .createNewEstimate("accept" + randomNumber, "approved", "20.00")
                .searchForEstimates("accept" + randomNumber, "DRAFT")
                .getCurrentStatusOfEstimate();

        Assertions.assertThat(statusBeforeClickingSent).as("Status of estimate should be : DRAFT").isEqualTo("DRAFT");

        String statusAfterClickingSent = estimatesPage
                .markAsAccepted()
                .refreshEstimatesPage()
                .getCurrentStatusOfEstimate();

        Assertions.assertThat(statusAfterClickingSent).as("Status of estimate should be : ACCEPTED").isEqualTo("ACCEPTED");
        testSteps.info("Status updated from DRAFT to ACCEPTED");
        estimatesPage
                .searchForEstimates("accept" + randomNumber, "ACCEPTED")
                .deleteEstimate()
                .closeConfirmationBox();
    }

    @Test(priority = 6, description = "Verify change of status from DRAFT to REJECTED for an estimate")
    public void verifyStatusChangeAfterItIsRejectedByCustomer() {
        String randomNumber = getCurrentDateTime().substring(17);
        testSteps = reports.createTest("Verify change of status from DRAFT to REJECTED for an estimate");
        String statusBeforeClickingSent = estimatesPage
                .createNewEstimate("reject" + randomNumber, "rejected", "20.00")
                .searchForEstimates("reject" + randomNumber, "DRAFT")
                .getCurrentStatusOfEstimate();

        Assertions.assertThat(statusBeforeClickingSent).as("Status of estimate should be : DRAFT").isEqualTo("DRAFT");
        String statusAfterClickingSent = estimatesPage.markAsRejected()
                .refreshEstimatesPage()
                .getCurrentStatusOfEstimate();
        Assertions.assertThat(statusAfterClickingSent).as("Status of estimate should be : REJECTED").isEqualTo("REJECTED");
        testSteps.info("Status updated from DRAFT to REJECTED");
        estimatesPage
                .searchForEstimates("reject" + randomNumber, "REJECTED")
                .deleteEstimate()
                .closeConfirmationBox();
    }

    @AfterMethod()
    public void generateReports(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            testSteps.fail("Test failed", MediaEntityBuilder.createScreenCaptureFromPath(captureScreenShot(driver)).build());
        } else {
            testSteps.pass("Test Passed");
        }
        reports.flush();
    }

    @AfterClass()
    public void tearDown() {
        browserEngine.quitBrowser(driver);
    }

}
