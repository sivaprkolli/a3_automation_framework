package com.a3.pages;

import com.a3.utils.SeleniumActions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SubCommonPage extends CommonPage{


    @FindBy(id="user-name")
    private WebElement userNameInput;

    @FindBy(id="password")
    private WebElement passwordInput;

    @FindBy(css="[name='login-button']")
    private WebElement submitButton;

    private WebDriver driver;
    private SeleniumActions commonActions;

    public SubCommonPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        commonActions = new SeleniumActions(driver);
    }

    @Override
    public void loginAsUser(String un, String pwd){
        commonActions.typeValue(userNameInput, un);
        commonActions.typeValue(passwordInput,pwd);
        commonActions.clickOnWebElement(submitButton, 10);
    }
}
