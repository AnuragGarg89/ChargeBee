package org.chargebee.testcases.UnitTests;

import org.assertj.core.api.Assertions;
import org.chargebee.assignment.pages.InvoicesPage;
import org.chargebee.assignment.pages.LoginPage;
import org.chargebee.assignment.pages.PaymentsPage;
import org.chargebee.assignment.utility.BaseClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class PaymentsPageTestCases extends BaseClass {

    private WebDriver driver;
    private PaymentsPage paymentsPage;

    @BeforeClass
    public void setup() {
        driver = browserEngine.initializeWebDriver(driver, readPropertyFile.getBrowser(), readPropertyFile.getAppUrl());
        LoginPage login = PageFactory.initElements(driver, LoginPage.class);
        login.loginToCraterApp();
        paymentsPage = PageFactory.initElements(driver, PaymentsPage.class);
    }

    @Test(priority = 1, description = "Verify create new payment functionality")
    public void createNewPayment() {
        String randomNumber = getCurrentDateTime().substring(17);
        testSteps = reports.createTest("Verify create new payment functionality");
        String actualMessage = paymentsPage
                .createNewPayment("AutomationTest" + randomNumber, "20.00")
                .getConfirmationMessage();
        Assertions.assertThat(actualMessage).as("Verify payment is created successfully").isEqualTo("Payment created successfully");
        paymentsPage.closeConfirmationBox();
        testSteps.info("new payment created");
        paymentsPage
                .searchForPayment("AutomationTest" + randomNumber)
                .deletePayment()
                .closeConfirmationBox();
    }

    @Test(priority = 2, description = "Verify edit payment functionality")
    public void editPayment() {
        String randomNumber = getCurrentDateTime().substring(17);
        testSteps = reports.createTest("Verify edit payment functionality");
        String actualMessage = paymentsPage
                .createNewPayment("edit" + randomNumber, "20.00")
                .closeConfirmationBox()
                .searchForPayment("edit" + randomNumber)
                .updatePaymentMode("CASH")
                .getConfirmationMessage();
        Assertions.assertThat(actualMessage).as("Verify payment is updated successfully").isEqualTo("Payment updated successfully");
        paymentsPage.closeConfirmationBox();
        testSteps.info("payment mode is updated successfully to : CASH");
        paymentsPage
                .searchForPayment("edit" + randomNumber)
                .deletePayment()
                .closeConfirmationBox();
    }

    @Test(priority = 3, description = "Verify delete payment functionality")
    public void deleteInvoice() {
        String randomNumber = getCurrentDateTime().substring(17);
        testSteps = reports.createTest("Verify delete payment functionality");
        String actualMessage = paymentsPage
                .createNewPayment("delete" + randomNumber, "20.00")
                .closeConfirmationBox()
                .searchForPayment("delete" + randomNumber)
                .deletePayment()
                .getConfirmationMessage();
        Assertions.assertThat(actualMessage).as("Verify payment is deleted successfully").isEqualTo("Payment deleted successfully");
        paymentsPage.closeConfirmationBox();
        testSteps.info("payment deleted successfully");
    }


}
