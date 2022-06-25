package org.chargebee.assignment.pages;

import org.chargebee.assignment.utility.Waits;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class CustomersPage extends Waits {

    public WebDriver localDriver;

    public CustomersPage(WebDriver driver) {
        this.localDriver = driver;
    }

    @FindBy(xpath = "//a[contains(text(),'Customers')]")
    WebElement customersTab;
    @FindBy(xpath = "//button[text()=' New Customer']")
    WebElement newCustomerButton;
    @FindBy(xpath = "(//input[@name='name'])[1]")
    WebElement name;
    @FindBy(xpath = "//button[@type='submit']")
    WebElement saveCustomerButton;
    @FindBy(xpath = "//input[@name='email']")
    WebElement email;
    @FindBy(xpath = "(//button[@class='focus:outline-none'])[4]/button")
    WebElement ellipses;
    @FindBy(xpath = "//button[text()='Edit']")
    WebElement editButton;
    @FindBy(xpath = "//*[text()=' Delete']")
    WebElement deleteButton;
    @FindBy(xpath = "//*[text()='Ok']")
    WebElement okButtonOnPopUp;
    @FindBy(css = ".flex.items-center.justify-end.space-x-5>button:nth-of-type(1)")
    WebElement filterButtonOnCustPage;
    @FindBy(xpath = "//*[@name='name' and @type='text']")
    WebElement filterTextBoxForNameOnCustPage;
    @FindBy(css = ".block.mt-1")
    WebElement noRecordsFound;
    @FindBy(xpath = "(//tr[@class='bg-white']/td[2]/a/span[1])[1]")
    WebElement getNameFromTable;
    @FindBy(xpath = "//button[text()='New Transaction']")
    WebElement newTransactionButton;
    @FindBy(xpath = "//a[text()=' New Estimate']")
    WebElement newEstimateButton;
    @FindBy(xpath = "//a[text()=' New Invoice']")
    WebElement newInvoiceButton;
    @FindBy(xpath = "//a[text()=' New Payment']")
    WebElement newPaymentButton;
    @FindBy(xpath = "//a[text()=' New Expense']")
    WebElement newExpenseButton;
    @FindBy(css = ".flex-1.w-0.ml-3.text-left>p:nth-of-type(2)")
    WebElement confirmationMessage;
    @FindBy(css = ".w-6.h-6>path[clip-rule='evenodd']")
    WebElement closeConfirmationBox;


    public CustomersPage createNewCustomer(String customerName) {
        waitForElementToBeClickable(localDriver, customersTab).click();
        hardWait(3);
        waitForElementToBeClickable(localDriver, newCustomerButton).click();
        hardWait(3);
        waitForElementToBeClickable(localDriver, name).click();
        hardWait(3);
        name.sendKeys(customerName);
        hardWait(3);
        waitForElementToBeClickable(localDriver, saveCustomerButton).submit();
        hardWait(3);
        return this;
    }

    public CustomersPage updateCustomersEmailAddress(String emailAddress) {
        waitForElementToBeClickable(localDriver, ellipses).click();
        hardWait(3);
        waitForElementToBeClickable(localDriver, editButton).click();
        hardWait(3);
        waitForElementToBeClickable(localDriver, email).click();
        hardWait(3);
        email.sendKeys(emailAddress);
        hardWait(3);
        waitForElementToBeClickable(localDriver, saveCustomerButton).submit();
        hardWait(3);
        return this;
    }

    public CustomersPage filterUsingCustomerName(String customerName) {
        waitForElementToBeClickable(localDriver, customersTab).click();
        hardWait(3);
        waitForElementToBeClickable(localDriver, filterButtonOnCustPage).click();
        hardWait(3);
        waitForElementToBeClickable(localDriver, filterTextBoxForNameOnCustPage).click();
        hardWait(3);
        filterTextBoxForNameOnCustPage.sendKeys(customerName);
        filterTextBoxForNameOnCustPage.sendKeys(Keys.ENTER);
        hardWait(3);
        waitForElementToBeClickable(localDriver, getNameFromTable).click();
        hardWait(3);
        return this;
    }

    public String returnNoRecordsFoundText() {
        return noRecordsFound.getText();
    }

    public CustomersPage deleteCustomer(String customerName) {
        waitForElementToBeClickable(localDriver,ellipses).click();
        hardWait(3);
        waitForElementToBeClickable(localDriver, deleteButton).click();
        hardWait(3);
        waitForElementToBeClickable(localDriver, okButtonOnPopUp).click();
        hardWait(3);
        return this;
    }


    public EstimatesPage clickOnCreateNewEstimateButton() {
        waitForElementToBeClickable(localDriver, newTransactionButton).click();
        hardWait(3);
        waitForElementToBeClickable(localDriver, newEstimateButton).click();
        hardWait(3);
        return PageFactory.initElements(localDriver, EstimatesPage.class);
    }

    public InvoicesPage createNewInvoice() {
        waitForElementToBeClickable(localDriver, newTransactionButton).click();
        hardWait(3);
        waitForElementToBeClickable(localDriver, newInvoiceButton).click();
        hardWait(3);
        return PageFactory.initElements(localDriver, InvoicesPage.class);
    }

    public PaymentsPage createNewPayment() {
        waitForElementToBeClickable(localDriver, newTransactionButton).click();
        hardWait(3);
        waitForElementToBeClickable(localDriver, newPaymentButton).click();
        hardWait(3);
        return PageFactory.initElements(localDriver, PaymentsPage.class);
    }

    public ExpensesPage createNewExpense() {
        waitForElementToBeClickable(localDriver, newTransactionButton).click();
        hardWait(3);
        waitForElementToBeClickable(localDriver, newExpenseButton).click();
        hardWait(3);
        return new ExpensesPage(localDriver);
    }

    public String getConfirmationMessage() {
        return confirmationMessage.getText();
    }

    public CustomersPage closeConfirmationBox() {
        waitForElementToBeClickable(localDriver, closeConfirmationBox).click();
        hardWait(3);
        return this;
    }

}
