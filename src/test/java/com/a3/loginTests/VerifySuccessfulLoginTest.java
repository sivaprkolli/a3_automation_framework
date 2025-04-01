package com.a3.loginTests;

import com.a3.automation.BaseA3;
import com.a3.dataManager.ReadDataFromExcel;
import com.a3.pages.LoginPage;
import com.a3.pages.MenuPage;
import com.a3.pages.ProductsPage;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

public class VerifySuccessfulLoginTest extends BaseA3 {

    public LoginPage loginPage;
    public ProductsPage productsPage;
    public MenuPage menuPage;

    @BeforeClass
    public void initializePages(){
        loginPage = new LoginPage(driver);
        productsPage = new ProductsPage(driver);
        menuPage = new MenuPage(driver);
    }

    @Test
    public void verifyLoginSuccessWithValidCredentials() throws IOException {
        String userName = ReadDataFromExcel.readDataFromExcel("userData","users","performance", "UserName");
        String password = ReadDataFromExcel.readDataFromExcel("userData","users","performance", "Password");
        loginPage.enterCredentials(userName, password);
        loginPage.clickOnLoginButton();
        int numberOfProducts = productsPage.getNumberOfProducts();
        Assert.assertTrue(numberOfProducts == 6);
        menuPage.logout();
    }

}
