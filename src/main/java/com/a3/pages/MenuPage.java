package com.a3.pages;

import com.a3.utils.SeleniumActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MenuPage {

    private SeleniumActions seleniumActions;

    public MenuPage(WebDriver driver){
        PageFactory.initElements(driver, this);
        seleniumActions = new SeleniumActions(driver);
    }

    @FindBy(css = "[data-test=\"open-menu\"]")
    private WebElement hamburgerMenu;


    @FindBy(css = "#react-burger-menu-btn")
    private WebElement logoutLink;

    private By logoutLink1 = By.id("logout_sidebar_link");
    public void logout(){
        seleniumActions.clickOnWebElement(hamburgerMenu);
        seleniumActions.clickOnWebElement(logoutLink);
    }

}
