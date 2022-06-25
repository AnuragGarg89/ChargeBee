package org.chargebee.assignment.pages;

import org.chargebee.assignment.utility.Waits;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class InvoicesPage extends Waits {

    public WebDriver localDriver;

    public InvoicesPage(WebDriver driver) {
        this.localDriver = driver;
    }

    @FindBy(xpath = "//a[text()=' Invoices']")
    WebElement invoicesTab;
    @FindBy(xpath = "//*[text()=' New Invoice']")
    WebElement newInvoiceButton;
    @FindBy(xpath = "//*[text()='New Customer ']")
    WebElement newCustomerLabel;
    @FindBy(xpath = "//*[text()='Add New Customer']")
    WebElement addNewCustomerLabel;
    @FindBy(xpath = "(//input[@name='name'])[1]")
    WebElement name;
    @FindBy(xpath = "//button[@type='submit' and text()=' Save']")
    WebElement saveCustomerButton;
    @FindBy(xpath = "(//input[@type='text'])[5]")
    WebElement itemsTextBox;
    @FindBy(xpath = "//input[@type='tel']")
    WebElement priceTextBox;
    @FindBy(xpath = "//button[@type='submit']")
    WebElement saveInvoiceButton;
    @FindBy(xpath = "(//button[@type='button'])[3]/button")
    WebElement ellipses;
    @FindBy(xpath = "//button[contains(.,'Mark as sent')]")
    WebElement markAsSentButton;
    @FindBy(xpath = "//*[text()='Ok']")
    WebElement okButtonOnPopUp;
    @FindBy(xpath = "//*[text()='Record Payment'])")
    WebElement recordPaymentButton;
    @FindBy(xpath = "//*[text()=' Delete']")
    WebElement deleteButton;
    @FindBy(xpath = "//input[@placeholder='Search']")
    WebElement searchLabel;
    @FindBy(xpath = "(//*[@class='flex-2']/span)[1]")
    WebElement currentStatusOfInvoice;
    @FindBy(css = ".flex-1.w-0.ml-3.text-left>p:nth-of-type(2)")
    WebElement confirmationMessage;
    @FindBy(css = ".w-6.h-6>path[clip-rule='evenodd']")
    WebElement closeConfirmationBox;
    @FindBy(xpath = "//*[@type='number' and @step='any']")
    WebElement quantityTextBox;
    @FindBy(xpath = "//*[text()=' Edit']")
    WebElement editButton;
    @FindBy(css = ".flex.items-center>button:nth-of-type(1)")
    WebElement filterButtonInvoicesPage;
    @FindBy(xpath = "//*[@label-value='name']//input")
    WebElement filterTextBoxForNameOnInvoicesPage;
    @FindBy(xpath = "(//*[@type='text'])[3]")
    WebElement filterTextBoxForStatusOnInvoicesPage;
    @FindBy(xpath = "//tr[@class='bg-white']/td[3]/a")
    WebElement estimateNumberFromTableOnInvoicePage;
    @FindBy(css = ".w-full.overflow-y-auto>ul>li")
    List<WebElement> dropdownValues;

    public InvoicesPage createNewInvoice(String customerName, String items, String amount) {
        waitForElementToBeClickable(localDriver, invoicesTab).click();
        hardWait(3);
        waitForElementToBeClickable(localDriver, newInvoiceButton).click();
        hardWait(3);
        waitForElementToBeClickable(localDriver, newCustomerLabel).click();
        hardWait(3);
        waitForElementToBeClickable(localDriver, addNewCustomerLabel).click();
        hardWait(3);
        waitForElementToBeClickable(localDriver, name).click();
        hardWait(3);
        name.sendKeys(customerName);
        hardWait(3);
        waitForElementToBeClickable(localDriver, saveCustomerButton).click();
        hardWait(3);
        closeConfirmationBox();
        return createNewInvoiceFromCustomerPage(items, amount);
    }

    public InvoicesPage createNewInvoiceFromCustomerPage(String items, String amount) {
        waitForElementToBeClickable(localDriver, itemsTextBox).click();
        hardWait(3);
        itemsTextBox.sendKeys(items);
        hardWait(3);
        waitForElementToBeClickable(localDriver, priceTextBox).click();
        hardWait(3);
        priceTextBox.clear();
        priceTextBox.sendKeys(amount);
        hardWait(3);
        waitForElementToBeClickable(localDriver, saveInvoiceButton).click();
        hardWait(3);
        return this;
    }

    public InvoicesPage markAsSent() {
        waitForElementToBeClickable(localDriver, markAsSentButton).click();
        hardWait(3);
        waitForElementToBeClickable(localDriver, okButtonOnPopUp).click();
        hardWait(3);
        return this;
    }

    public InvoicesPage clickSaveInvoiceButtonOnInvoicesPage() {
        waitForElementToBeClickable(localDriver, saveInvoiceButton).click();
        hardWait(3);
        waitForElementToBeClickable(localDriver, okButtonOnPopUp).click();
        hardWait(3);
        return this;
    }

    public InvoicesPage searchForInvoice(String customerName, String status) {
        waitForElementToBeClickable(localDriver, invoicesTab).click();
        hardWait(3);
        waitForElementToBeClickable(localDriver, filterButtonInvoicesPage).click();
        hardWait(3);
        waitForElementToBeClickable(localDriver, filterTextBoxForNameOnInvoicesPage).click();
        hardWait(3);
        filterTextBoxForNameOnInvoicesPage.sendKeys(customerName);
        hardWait(3);
        filterTextBoxForNameOnInvoicesPage.sendKeys(Keys.ENTER);
        hardWait(3);
        for (WebElement we : dropdownValues) {
            if (we.getText().equals(customerName)) {
                we.click();
                hardWait(3);
            }
        }
        waitForElementToBeClickable(localDriver, filterTextBoxForStatusOnInvoicesPage).click();
        filterTextBoxForStatusOnInvoicesPage.clear();
        filterTextBoxForStatusOnInvoicesPage.sendKeys(status);
        hardWait(3);
        filterTextBoxForStatusOnInvoicesPage.sendKeys(Keys.ENTER);
        hardWait(3);
        waitForElementToBeClickable(localDriver, estimateNumberFromTableOnInvoicePage).click();
        hardWait(3);
        waitForElementToBeClickable(localDriver, searchLabel).click();
        hardWait(3);
        searchLabel.sendKeys(customerName);
        hardWait(3);
        searchLabel.sendKeys(Keys.ENTER);
        hardWait(3);
        return this;
    }

    public InvoicesPage refreshInvoicePage() {
        localDriver.navigate().refresh();
        hardWait(3);
        return this;
    }

    public InvoicesPage deleteInvoice() {
        waitForElementToBeClickable(localDriver, ellipses).click();
        hardWait(3);
        waitForElementToBeClickable(localDriver, deleteButton).click();
        hardWait(3);
        waitForElementToBeClickable(localDriver, okButtonOnPopUp).click();
        hardWait(3);
        return this;
    }

    public InvoicesPage updateQuantity(String quantity) {
        waitForElementToBeClickable(localDriver, ellipses).click();
        hardWait(3);
        waitForElementToBeClickable(localDriver, editButton).click();
        hardWait(3);
        waitForElementToBeClickable(localDriver, quantityTextBox).click();
        hardWait(3);
        quantityTextBox.clear();
        quantityTextBox.sendKeys(quantity);
        hardWait(3);
        waitForElementToBeClickable(localDriver, saveInvoiceButton).click();
        hardWait(3);
        return this;
    }

    public String getConfirmationMessage() {
        return confirmationMessage.getText();
    }

    public InvoicesPage closeConfirmationBox() {
        waitForElementToBeClickable(localDriver, closeConfirmationBox).click();
        hardWait(3);
        return this;
    }

    public String getCurrentStatusOfInvoice() {
        waitForTextToBePresentInElement(localDriver, currentStatusOfInvoice, currentStatusOfInvoice.getText());
        return currentStatusOfInvoice.getText();
    }

    public PaymentsPage clickOnRecordPayment() {
        waitForElementToBeClickable(localDriver, recordPaymentButton).click();
        hardWait(3);
        return PageFactory.initElements(localDriver, PaymentsPage.class);
    }
}
