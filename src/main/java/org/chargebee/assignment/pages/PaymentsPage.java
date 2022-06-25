package org.chargebee.assignment.pages;

import org.chargebee.assignment.utility.Waits;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class PaymentsPage extends Waits {

    public WebDriver localDriver;

    public PaymentsPage(WebDriver driver) {
        this.localDriver = driver;
    }

    @FindBy(xpath = "//a[text()=' Payments']")
    WebElement paymentsTab;
    @FindBy(xpath = "//*[text()=' Add Payment']")
    WebElement addPaymentButton;
    @FindBy(xpath = "(//*[@type='text'])[4]")
    WebElement customerNameTextBox;
    @FindBy(xpath = "//*[text()=' Add New Customer']")
    WebElement addNewCustomerLabel;
    @FindBy(xpath = "(//input[@name='name'])[1]")
    WebElement name;
    @FindBy(xpath = "//button[@type='submit' and text()=' Save']")
    WebElement saveCustomerButton;
    @FindBy(xpath = "//input[@type='tel']")
    WebElement priceTextBox;
    @FindBy(xpath = "(//button[@type='submit'])[1]")
    WebElement savePaymentButton;
    @FindBy(xpath = "(//button[@type='button'])[3]/button")
    WebElement ellipses;
    @FindBy(xpath = "//*[text()=' Edit']")
    WebElement editButton;
    @FindBy(xpath = "//*[text()=' Delete']")
    WebElement deleteButton;
    @FindBy(xpath = "//input[@placeholder='Search']")
    WebElement searchLabel;
    @FindBy(css = ".flex-1.w-0.ml-3.text-left>p:nth-of-type(2)")
    WebElement confirmationMessage;
    @FindBy(css = ".w-6.h-6>path[clip-rule='evenodd']")
    WebElement closeConfirmationBox;
    @FindBy(css = ".flex-1.whitespace-nowrap.right>span")
    WebElement amountOnPaymentListPane;
    @FindBy(css = ".flex.items-center>button:nth-of-type(1)")
    WebElement filterButtonPaymentsPage;
    @FindBy(xpath = "//*[@label-value='name']//input")
    WebElement filterTextBoxForNameOnPaymentsPage;
    @FindBy(xpath = "(//*[@type='text'])[3]")
    WebElement filterTextBoxForPaymentNumberOnPaymentsPage;
    @FindBy(xpath = "//tr[@class='bg-white']/td[3]/a")
    WebElement paymentNumberFromTableOnPaymentsPage;
    @FindBy(xpath = "//*[text()='Ok']")
    WebElement okButtonOnPopUp;
    @FindBy(xpath = "(//*[@type='text'])[5]")
    WebElement paymentModeTextBox;
    @FindBy(css = ".w-full.overflow-y-auto>ul>li")
    List<WebElement> dropdownValues;
    @FindBy(xpath = "//*[text()=' Update Payment']")
    WebElement updatePaymentButton;

    public PaymentsPage createNewPayment(String customerName, String amount) {
        waitForElementToBeClickable(localDriver, paymentsTab).click();
        hardWait(3);
        waitForElementToBeClickable(localDriver, addPaymentButton).click();
        hardWait(3);
        waitForElementToBeClickable(localDriver, customerNameTextBox).click();
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
        return createNewPaymentFromCustomerPage(customerName, amount);
    }

    public PaymentsPage createNewPaymentFromCustomerPage(String customerName, String amount) {
        waitForElementToBeClickable(localDriver, customerNameTextBox).click();
        hardWait(3);
        customerNameTextBox.sendKeys(customerName);
        hardWait(3);
        customerNameTextBox.sendKeys(Keys.ENTER);
        hardWait(3);
        for (WebElement we : dropdownValues) {
            if (we.getText().equals(customerName)) {
                we.click();
                hardWait(3);
            }
        }
        waitForElementToBeClickable(localDriver, priceTextBox).click();
        hardWait(3);
        priceTextBox.clear();
        priceTextBox.sendKeys(amount);
        hardWait(3);
        waitForElementToBeClickable(localDriver, savePaymentButton).click();
        hardWait(3);
        return this;
    }

    public PaymentsPage searchForPayment(String customerName) {
        waitForElementToBeClickable(localDriver, paymentsTab).click();
        hardWait(3);
        waitForElementToBeClickable(localDriver, filterButtonPaymentsPage).click();
        hardWait(3);
        waitForElementToBeClickable(localDriver, filterTextBoxForNameOnPaymentsPage).click();
        hardWait(3);
        filterTextBoxForNameOnPaymentsPage.sendKeys(customerName);
        hardWait(3);
        filterTextBoxForNameOnPaymentsPage.sendKeys((Keys.ENTER));
        hardWait(3);
        waitForElementToBeClickable(localDriver, paymentNumberFromTableOnPaymentsPage).click();
        hardWait(3);
        waitForElementToBeClickable(localDriver, searchLabel).click();
        hardWait(3);
        searchLabel.sendKeys(customerName);
        searchLabel.sendKeys(Keys.ENTER);
        hardWait(3);
        return this;
    }

    public PaymentsPage deletePayment() {
        waitForElementToBeClickable(localDriver, ellipses).click();
        hardWait(3);
        waitForElementToBeClickable(localDriver, deleteButton).click();
        hardWait(3);
        waitForElementToBeClickable(localDriver, okButtonOnPopUp).click();
        hardWait(3);
        return this;
    }

    public PaymentsPage updatePaymentMode(String mode) {
        waitForElementToBeClickable(localDriver, ellipses).click();
        hardWait(3);
        waitForElementToBeClickable(localDriver, editButton).click();
        hardWait(3);
        waitForElementToBeClickable(localDriver, paymentModeTextBox).click();
        hardWait(3);
        paymentModeTextBox.clear();
        paymentModeTextBox.sendKeys(mode);
        hardWait(3);
        paymentModeTextBox.sendKeys(Keys.ENTER);
        hardWait(3);
        for (WebElement we : dropdownValues) {
            if (we.getText().equals(mode)) {
                we.click();
                hardWait(3);
            }
        }
        waitForElementToBeClickable(localDriver, updatePaymentButton).click();
        hardWait(3);
        return this;
    }

    public String getConfirmationMessage() {
        return confirmationMessage.getText();
    }

    public PaymentsPage closeConfirmationBox() {
        waitForElementToBeClickable(localDriver, closeConfirmationBox).click();
        hardWait(3);
        return this;
    }
}
