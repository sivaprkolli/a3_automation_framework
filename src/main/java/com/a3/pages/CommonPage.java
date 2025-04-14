package com.a3.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CommonPage {

    public CommonPage(){

    }

    @FindBy(id="user-name")
    private WebElement userNameInput;

    @FindBy(id="password")
    private WebElement passwordInput;

    @FindBy(css="[name='login-button']")
    private WebElement submitButton;

    public void loginAsUser(String un, String pwd){
        userNameInput.sendKeys(un);
        passwordInput.sendKeys(pwd);
        submitButton.click();
    }
}
