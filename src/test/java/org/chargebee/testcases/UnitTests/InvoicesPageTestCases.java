package org.chargebee.testcases.UnitTests;

import com.aventstack.extentreports.MediaEntityBuilder;
import org.assertj.core.api.Assertions;
import org.chargebee.assignment.pages.InvoicesPage;
import org.chargebee.assignment.pages.LoginPage;
import org.chargebee.assignment.utility.BaseClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class InvoicesPageTestCases extends BaseClass {

    private WebDriver driver;
    private InvoicesPage invoicesPage;

    @BeforeClass
    public void setup() {
        driver = browserEngine.initializeWebDriver(driver, readPropertyFile.getBrowser(), readPropertyFile.getAppUrl());
        LoginPage login = PageFactory.initElements(driver, LoginPage.class);
        login.loginToCraterApp();
        invoicesPage = PageFactory.initElements(driver, InvoicesPage.class);
    }

    @Test(priority = 1, description = "Verify create new invoice functionality")
    public void createNewInvoice() {
        String randomNumber = getCurrentDateTime().substring(17);
        testSteps = reports.createTest("Verify create new invoice functionality");
        String actualMessage = invoicesPage
                .createNewInvoice("AutomationTest" + randomNumber, "Test" + randomNumber, "20.00")
                .getConfirmationMessage();
        Assertions.assertThat(actualMessage).as("Verify Invoice is created successfully").isEqualTo("Invoice created successfully");
        invoicesPage.closeConfirmationBox();
        testSteps.info("New Invoice Created");
        invoicesPage
                .searchForInvoice("AutomationTest" + randomNumber, "DRAFT")
                .deleteInvoice()
                .closeConfirmationBox();
    }

    @Test(priority = 2, description = "Verify edit invoice functionality")
    public void editInvoice() {
        String randomNumber = getCurrentDateTime().substring(17);
        testSteps = reports.createTest("Verify edit invoice functionality");
        String actualMessage = invoicesPage
                .createNewInvoice("edit" + randomNumber, "edit", "20.00")
                .closeConfirmationBox()
                .searchForInvoice("edit" + randomNumber, "DRAFT")
                .updateQuantity("2")
                .getConfirmationMessage();
        Assertions.assertThat(actualMessage).as("Verify Invoice is updated successfully").isEqualTo("Invoice updated successfully");
        invoicesPage.closeConfirmationBox();
        testSteps.info("Quantity of items in invoice is updated successfully to : 2 from : 1");
        invoicesPage
                .searchForInvoice("edit" + randomNumber, "DRAFT")
                .deleteInvoice()
                .closeConfirmationBox();
    }

    @Test(priority = 3, description = "Verify delete invoice functionality")
    public void deleteInvoice() {
        String randomNumber = getCurrentDateTime().substring(17);
        testSteps = reports.createTest("Verify delete invoice functionality");
        String actualMessage = invoicesPage
                .createNewInvoice("delete" + randomNumber, "delete", "20.00")
                .closeConfirmationBox()
                .searchForInvoice("delete" + randomNumber, "DRAFT")
                .deleteInvoice()
                .getConfirmationMessage();
        Assertions.assertThat(actualMessage).as("Verify Invoice is deleted successfully").isEqualTo("Invoice deleted successfully");
        invoicesPage.closeConfirmationBox();
        testSteps.info("Invoice Deleted Successfully");
    }


    @Test(priority = 4, description = "Verify change of status from DRAFT to SENT for an invoice")
    public void verifyStatusChangeAfterSendingToCustomer() {
        String randomNumber = getCurrentDateTime().substring(17);
        testSteps = reports.createTest("Verify change of status from DRAFT to SENT for an estimate");
        String statusBeforeClickingSent = invoicesPage
                .createNewInvoice("sent" + randomNumber, "sent", "20.00")
                .closeConfirmationBox()
                .searchForInvoice("sent" + randomNumber, "DRAFT")
                .getCurrentStatusOfInvoice();

        Assertions.assertThat(statusBeforeClickingSent).as("Status of estimate should be : DRAFT").isEqualTo("DRAFT");

        String statusAfterClickingSent = invoicesPage.markAsSent()
                .refreshInvoicePage()
                .getCurrentStatusOfInvoice();
        Assertions.assertThat(statusAfterClickingSent).as("Status of estimate should be : SENT").isEqualTo("SENT");
        testSteps.info("Status updated from DRAFT to SENT");
        invoicesPage
                .searchForInvoice("sent" + randomNumber, "SENT")
                .deleteInvoice()
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
