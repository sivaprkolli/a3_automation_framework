package com.a3.pages;

import com.a3.utils.SeleniumActions;
import com.a3.utils.SeleniumWaits;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductsPage {

    private SeleniumActions seleniumActions;
    private SeleniumWaits seleniumWaits;
    public ProductsPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        seleniumActions = new SeleniumActions(driver);
        seleniumWaits = new SeleniumWaits(driver);
    }

    @FindBy(css = ".inventory_item")
    private List<WebElement> productsList;

    public int getNumberOfProducts(){
        seleniumWaits.waitForElementVisible(productsList);
        return seleniumActions.getProductsList(productsList);
    }

}
