package com.a3.automation;

import com.aventstack.chaintest.plugins.ChainTestListener;
import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.time.Duration;

@Listeners(ChainTestListener.class)
public class BaseA3 {
    public WebDriver driver;

    @BeforeTest
    public void launchApplication(){
        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    }

    @AfterTest
    public void tearDown(){
        driver.quit();
    }

    public void getScreenShot() throws IOException {
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File sourceImage = takesScreenshot.getScreenshotAs(OutputType.FILE);
        File destinationImage = new File(System.getProperty("user.dir")+"/screenshots/testImage.png");
        FileUtils.copyFile(sourceImage, destinationImage);
    }

    @AfterMethod
    public void captureScreenshotIfTestFails(ITestResult iTestResult, Method method) throws IOException {
        if(iTestResult.getStatus() == ITestResult.FAILURE){
            getScreenShot();
            Allure.addAttachment(method.getName(),
                    new ByteArrayInputStream(((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES)));
        }
    }
}
