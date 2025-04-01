package com.a3.pages;

import com.a3.utils.SeleniumActions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    private WebDriver driver;
    private SeleniumActions commonActions;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        commonActions = new SeleniumActions(driver);
    }

    @FindBy(id="user-name")
    private WebElement userNameInput;

    @FindBy(id="password")
    private WebElement passwordInput;

    @FindBy(css="[name='login-button']")
    private WebElement submitButton;

    public void enterCredentials(String userName, String password) {
        commonActions.typeValue(userNameInput, userName);
        commonActions.typeValue(passwordInput, password);
    }

    public void clickOnLoginButton() {
        commonActions.clickOnWebElement(submitButton);
    }
}

