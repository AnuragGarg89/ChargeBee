package org.chargebee.assignment.pages;

import org.chargebee.assignment.utility.Waits;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ExpensesPage extends Waits {

    public WebDriver localDriver;

    public ExpensesPage(WebDriver driver) {
        this.localDriver = driver;
    }

    @FindBy(css = ".flex-1.w-0.ml-3.text-left>p:nth-of-type(2)")
    WebElement confirmationMessage;
    @FindBy(css = ".w-6.h-6>path[clip-rule='evenodd']")
    WebElement closeConfirmationBox;

    public String getConfirmationMessage(){
        return confirmationMessage.getText();
    }
    public void closeConfirmationBox(){
        closeConfirmationBox.click();
        hardWait(2);
    }
}
