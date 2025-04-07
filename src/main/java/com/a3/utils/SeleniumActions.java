package com.a3.utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SeleniumActions {
    private WebDriver driver;
    public Logger logger = LoggerFactory.getLogger(SeleniumActions.class);
    public SeleniumActions(WebDriver driver){
        this.driver = driver;
    }

    public void getFullScreenshot() throws IOException {
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File sourceImage = takesScreenshot.getScreenshotAs(OutputType.FILE);
        File destinationImage = new File(System.getProperty("user.dir")+"/screenshots/testImage.png");
        FileUtils.copyFile(sourceImage, destinationImage);
    }

    @Test
    public void getElementScreenshot() throws IOException {
        WebElement image = driver.findElement(By.cssSelector("[alt=\"Fork me on GitHub\"]"));
        File sourceImage = image.getScreenshotAs(OutputType.FILE);
        File destinationImage = new File(System.getProperty("user.dir")+"/screenshots/element.png");
        FileUtils.copyFile(sourceImage, destinationImage);
    }

    public int getProductsList(WebDriver driver, By by){
        return driver.findElements(by).size();
    }

    public int getProductsList(By by){
        return driver.findElements(by).size();
    }

    public int getProductsList(List<WebElement> element){

            if(element.size()>0){
                logger.info("Successfully fetched number of elements");
            } else {
                logger.error("Element not found so returning empty list ::" + element);
            }
        return element.size();
    }

    public void clickOnWebElement(WebElement element){
        try {
            element.click();
            logger.info("Successfully clicked on element :: " + element);
        }catch (ElementClickInterceptedException ec){
            JavascriptExecutor jse = (JavascriptExecutor) driver;
            jse.executeScript("arguments[0].click()", element);
            logger.info("Successfully clicked on element using JSE  :: " + element);
        }
    }

    public void clickOnWebElement(By byLocator){
        driver.findElement(byLocator).click();
    }

    public void typeValue(WebElement element, String data){
        element.sendKeys(data);
        logger.info("Successfully entered value in the input field :: " + element);
    }

    public String getElementText(WebElement element){
        return element.getText();
    }

    public List<String> getMultipleElementsText(List<WebElement> elements){
        List<String> actualRowData = new ArrayList<>();
        for(int i=0;i< elements.size(); i++){
            actualRowData.add(elements.get(i).getText());
        }
        System.out.println(actualRowData);
        return actualRowData;
    }

    public List<String> getMultipleElementsText(List<WebElement> elements, String name){
        List<String> actualRowData = new ArrayList<>();
        for(int i=0;i< elements.size(); i++){
            actualRowData.add(elements.get(i).getText());
        }
        System.out.println(actualRowData);
        return actualRowData;
    }
}
