package org.chargebee.assignment.pages;

import org.chargebee.assignment.utility.Waits;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class EstimatesPage extends Waits {

    public WebDriver localDriver;

    public EstimatesPage(WebDriver driver) {
        this.localDriver = driver;
    }

    @FindBy(xpath = "//a[text()=' Estimates']")
    WebElement estimatesTab;
    @FindBy(xpath = "//*[text()=' New Estimate']")
    WebElement newEstimateButton;
    @FindBy(xpath = "//*[text()='New Customer ']")
    WebElement newCustomerLabel;
    @FindBy(xpath = "//*[text()='Add New Customer']")
    WebElement addNewCustomerLabel;
    @FindBy(xpath = "(//input[@name='name'])[1]")
    WebElement name;
    @FindBy(xpath = "//button[@type='submit' and text()=' Save']")
    WebElement saveButton;
    @FindBy(xpath = "//button[@type='submit']")
    WebElement saveEstimateButton;
    @FindBy(xpath = "(//input[@type='text'])[5]")
    WebElement itemsTextBox;
    @FindBy(xpath = "//input[@type='tel']")
    WebElement priceTextBox;
    @FindBy(xpath = "//button[contains(.,'Mark as Sent')]")
    WebElement markAsSentButton;
    @FindBy(xpath = "(//button[@type='button'])[3]/button")
    WebElement ellipses;
    @FindBy(xpath = "//*[text()=' Mark as accepted']")
    WebElement markAsAcceptedButton;
    @FindBy(xpath = "//*[text()=' Mark as rejected']")
    WebElement markAsRejectedButton;
    @FindBy(xpath = "//*[text()=' Convert to Invoice']")
    WebElement convertToInvoiceButton;
    @FindBy(xpath = "//*[text()=' Delete']")
    WebElement deleteButton;
    @FindBy(xpath = "//*[text()=' Edit']")
    WebElement editButton;
    @FindBy(xpath = "//*[text()='Ok']")
    WebElement okButtonOnPopUp;
    @FindBy(css = ".flex.items-center>button:nth-of-type(1)")
    WebElement filterButtonEstimatesPage;
    @FindBy(xpath = "//*[@label-value='name']//input")
    WebElement filterTextBoxForNameOnEstimatesPage;
    @FindBy(xpath = "(//*[@type='text'])[3]")
    WebElement filterTextBoxForStatusOnEstimatesPage;
    @FindBy(xpath = "//tr[@class='bg-white']/td[3]/a")
    WebElement estimateNumberFromTableOnEstimatesPage;
    @FindBy(xpath = "//input[@placeholder='Search']")
    WebElement searchLabel;
    @FindBy(xpath = "//*[@class='flex-2']/span")
    WebElement currentStatusOfEstimate;
    @FindBy(css = ".flex-1.w-0.ml-3.text-left>p:nth-of-type(2)")
    WebElement confirmationMessage;
    @FindBy(css = ".w-6.h-6>path[clip-rule='evenodd']")
    WebElement closeConfirmationBox;
    @FindBy(xpath = "//*[@type='number' and @step='any']")
    WebElement quantityTextBox;

    public EstimatesPage createNewEstimate(String customerName, String items, String amount) {
        waitForElementToBeClickable(localDriver, estimatesTab).click();
        hardWait(3);
        waitForElementToBeClickable(localDriver, newEstimateButton).click();
        hardWait(3);
        waitForElementToBeClickable(localDriver, newCustomerLabel).click();
        hardWait(3);
        waitForElementToBeClickable(localDriver, addNewCustomerLabel).click();
        hardWait(3);
        waitForElementToBeClickable(localDriver, name).click();
        hardWait(3);
        name.sendKeys(customerName);
        hardWait(3);
        waitForElementToBeClickable(localDriver, saveButton).click();
        hardWait(3);
        closeConfirmationBox();
        return createNewEstimateFromCustomerPage(items, amount);
    }

    public EstimatesPage searchForEstimates(String customerName, String status) {
        waitForElementToBeClickable(localDriver, estimatesTab).click();
        hardWait(3);
        waitForElementToBeClickable(localDriver, filterButtonEstimatesPage).click();
        hardWait(3);
        waitForElementToBeClickable(localDriver, filterTextBoxForNameOnEstimatesPage).click();
        hardWait(3);
        filterTextBoxForNameOnEstimatesPage.sendKeys(customerName);
        hardWait(3);
        filterTextBoxForNameOnEstimatesPage.sendKeys((Keys.ENTER));
        hardWait(3);
        waitForElementToBeClickable(localDriver, filterTextBoxForStatusOnEstimatesPage).click();
        filterTextBoxForStatusOnEstimatesPage.clear();
        filterTextBoxForStatusOnEstimatesPage.sendKeys(status);
        hardWait(3);
        filterTextBoxForStatusOnEstimatesPage.sendKeys(Keys.ENTER);
        hardWait(3);
        waitForElementToBeClickable(localDriver, estimateNumberFromTableOnEstimatesPage).click();
        hardWait(3);
        waitForElementToBeClickable(localDriver, searchLabel).click();
        hardWait(3);
        searchLabel.sendKeys(customerName);
        hardWait(3);
        searchLabel.sendKeys(Keys.ENTER);
        hardWait(3);
        return this;
    }

    public EstimatesPage createNewEstimateFromCustomerPage(String items, String amount) {
        waitForElementToBeClickable(localDriver, itemsTextBox).click();
        hardWait(3);
        itemsTextBox.sendKeys(items);
        hardWait(3);
        waitForElementToBeClickable(localDriver, priceTextBox).click();
        priceTextBox.clear();
        priceTextBox.sendKeys(amount);
        hardWait(3);
        waitForElementToBeClickable(localDriver, saveEstimateButton).click();
        hardWait(3);
        return this;
    }

    public EstimatesPage markAsSent() {
        waitForElementToBeClickable(localDriver, markAsSentButton).click();
        hardWait(3);
        waitForElementToBeClickable(localDriver, okButtonOnPopUp).click();
        hardWait(3);
        return this;
    }

    public EstimatesPage markAsAccepted() {
        waitForElementToBeClickable(localDriver, ellipses).click();
        hardWait(3);
        waitForElementToBeClickable(localDriver, markAsAcceptedButton).click();
        hardWait(3);
        waitForElementToBeClickable(localDriver, okButtonOnPopUp).click();
        hardWait(3);
        return this;
    }

    public EstimatesPage markAsRejected() {
        waitForElementToBeClickable(localDriver, ellipses).click();
        hardWait(3);
        waitForElementToBeClickable(localDriver, markAsRejectedButton).click();
        hardWait(3);
        waitForElementToBeClickable(localDriver, okButtonOnPopUp).click();
        hardWait(3);
        return this;
    }

    public InvoicesPage convertToInvoiceButtonOnEstimatesPage() {
        waitForElementToBeClickable(localDriver,ellipses).click();
        hardWait(3);
        waitForElementToBeClickable(localDriver, convertToInvoiceButton).click();
        hardWait(3);
        waitForElementToBeClickable(localDriver,okButtonOnPopUp).click();
        hardWait(3);
        return PageFactory.initElements(localDriver, InvoicesPage.class);
    }

    public String getCurrentStatusOfEstimate() {
        waitForTextToBePresentInElement(localDriver, currentStatusOfEstimate, currentStatusOfEstimate.getText());
        return currentStatusOfEstimate.getText();
    }

    public EstimatesPage refreshEstimatesPage() {
        localDriver.navigate().refresh();
        hardWait(3);
        return this;
    }

    public EstimatesPage deleteEstimate() {
        waitForElementToBeClickable(localDriver,ellipses).click();
        hardWait(3);
        waitForElementToBeClickable(localDriver, deleteButton).click();
        hardWait(3);
        waitForElementToBeClickable(localDriver,okButtonOnPopUp).click();
        hardWait(3);
        return this;
    }

    public EstimatesPage updateQuantity(String quantity) {
        waitForElementToBeClickable(localDriver, ellipses).click();
        hardWait(3);
        waitForElementToBeClickable(localDriver, editButton).click();
        hardWait(3);
        waitForElementToBeClickable(localDriver,quantityTextBox).click();
        quantityTextBox.clear();
        quantityTextBox.sendKeys(quantity);
        hardWait(3);
        waitForElementToBeClickable(localDriver,saveEstimateButton).click();
        hardWait(3);
        return this;
    }

    public String getConfirmationMessage() {
        return confirmationMessage.getText();
    }

    public void closeConfirmationBox() {
        waitForElementToBeClickable(localDriver,closeConfirmationBox).click();
        hardWait(3);
    }
}
