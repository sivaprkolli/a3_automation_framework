package com.a3.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public interface Actions {

    void clickOnWebElement(WebElement element);
    void clickOnWebElement(By by);
    void clickOnWebElement(WebElement element, int time);
    int getProductsList(List<WebElement> elementList);
}
