package com.a3.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.List;

public class SeleniumWaits {

    WebDriverWait webDriverWait;
    Logger logger = LoggerFactory.getLogger(SeleniumWaits.class);
    public SeleniumWaits(WebDriver driver){
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public void waitForElementVisible(WebElement element){
        webDriverWait.until(ExpectedConditions.visibilityOf(element));
        logger.info("Successfully wait completed for element :: " + element);
    }

    public void waitForElementVisible(List<WebElement> elementList){
        webDriverWait.until(ExpectedConditions.visibilityOfAllElements(elementList));
        logger.info("Successfully wait completed for all  elements :: " + elementList);
    }
}
