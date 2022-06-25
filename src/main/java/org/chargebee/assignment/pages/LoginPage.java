package org.chargebee.assignment.pages;

import org.chargebee.assignment.utility.Waits;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends Waits {

    public WebDriver localDriver;

    public LoginPage(WebDriver driver) {
        this.localDriver = driver;
    }

    @FindBy(xpath = "//button[@type='submit']")
    WebElement loginButton;
    @FindBy(css = ".flex-1.w-0.ml-3.text-left>p:nth-of-type(2)")
    WebElement confirmationMessage;
    @FindBy(css = ".w-6.h-6>path[clip-rule='evenodd']")
    WebElement closeConfirmationBox;
    public void loginToCraterApp() {
        loginButton.click();
    }


}
