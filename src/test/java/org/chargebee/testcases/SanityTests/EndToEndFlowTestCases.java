package org.chargebee.testcases.SanityTests;

import org.chargebee.assignment.pages.CustomersPage;
import org.chargebee.assignment.pages.EstimatesPage;
import org.chargebee.assignment.pages.InvoicesPage;
import org.chargebee.assignment.pages.LoginPage;
import org.chargebee.assignment.utility.BaseClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class EndToEndFlowTestCases extends BaseClass {

    private WebDriver driver;
    private CustomersPage customersPage;
    private final String randomNumber = getCurrentDateTime().substring(17);
    private final String customerName = "AutomationTest" + randomNumber;


    @BeforeClass
    public void setup() {
        testSteps = reports.createTest("Test create new customer functionality");
        driver = browserEngine.initializeWebDriver(driver, readPropertyFile.getBrowser(), readPropertyFile.getAppUrl());
        testSteps.info("Browser launched : " + readPropertyFile.getBrowser());
        LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
        loginPage.loginToCraterApp();
        testSteps.info("Logged into application");
        customersPage = PageFactory.initElements(driver, CustomersPage.class);
    }

    @Test(priority = 1)
    public void createNewInvoiceAndEstimateForANewCustomer() {

        customersPage
                .createNewCustomer(customerName)
                .clickOnCreateNewEstimateButton()
                .createNewEstimateFromCustomerPage("Automation" + randomNumber, "20.00")
                .convertToInvoiceButtonOnEstimatesPage()
                .clickSaveInvoiceButtonOnInvoicesPage()
                .markAsSent()
                .clickOnRecordPayment()
                .createNewPayment(customerName,"20.00");

    }
}
