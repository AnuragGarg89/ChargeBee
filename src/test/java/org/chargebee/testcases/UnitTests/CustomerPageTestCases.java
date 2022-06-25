package org.chargebee.testcases.UnitTests;

import com.aventstack.extentreports.MediaEntityBuilder;
import org.assertj.core.api.Assertions;
import org.chargebee.assignment.pages.CustomersPage;
import org.chargebee.assignment.pages.LoginPage;
import org.chargebee.assignment.utility.BaseClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CustomerPageTestCases extends BaseClass {

    private WebDriver driver;
    private CustomersPage customerPage;


    @BeforeClass
    public void setup() {
        driver = browserEngine.initializeWebDriver(driver, readPropertyFile.getBrowser(), readPropertyFile.getAppUrl());
        LoginPage login = PageFactory.initElements(driver, LoginPage.class);
        login.loginToCraterApp();
        customerPage = PageFactory.initElements(driver, CustomersPage.class);
    }

    @Test(priority = 1, description = "Verify create new Customer functionality")
    public void createNewCustomer() {
        String randomNumber = getCurrentDateTime().substring(17);
        String customerName = "CusTestCreate" + randomNumber;
        testSteps = reports.createTest("Test create new customer functionality");
        String actualMessage = customerPage
                .createNewCustomer(customerName)
                .getConfirmationMessage();
        Assertions.assertThat(actualMessage).as("Verify Customer is created successfully").isEqualTo("Customer created successfully");
        customerPage.closeConfirmationBox();
        testSteps.info("New Customer created : " + customerName);
        customerPage
                .filterUsingCustomerName(customerName)
                .deleteCustomer(customerName);
    }

    @Test(priority = 2, description = "Verify edit customer functionality")
    public void editCustomer() {
        String randomNumber = getCurrentDateTime().substring(17);
        String customerName = "CusTestEdit" + randomNumber;
        testSteps = reports.createTest("Test edit customer functionality");
        String emailAddress = "test" + randomNumber + "@gmail.com";
        String actualMessage = customerPage
                .createNewCustomer(customerName)
                .filterUsingCustomerName(customerName)
                .updateCustomersEmailAddress(emailAddress)
                .getConfirmationMessage();
        Assertions.assertThat(actualMessage).as("Verify Customer is updated successfully").isEqualTo("Customer updated successfully");
        customerPage.closeConfirmationBox();
        testSteps.info("Customer's email address : " + emailAddress + " updated");
        customerPage
                .filterUsingCustomerName(customerName)
                .deleteCustomer(customerName);
    }

    @Test(priority = 3, description = "Verify delete customer functionality")
    public void deleteCustomer() {
        String randomNumber = getCurrentDateTime().substring(17);
        String customerName = "CusTestDel" + randomNumber;
        testSteps = reports.createTest("Test delete customer functionality");
        String actualMessage = customerPage
                .createNewCustomer(customerName)
                .filterUsingCustomerName(customerName)
                .deleteCustomer(customerName)
                .getConfirmationMessage();
        Assertions.assertThat(actualMessage).as("Verify Customer is deleted successfully").isEqualTo("Customer deleted successfully");
        testSteps.info("Customer deleted : " + customerName);
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


