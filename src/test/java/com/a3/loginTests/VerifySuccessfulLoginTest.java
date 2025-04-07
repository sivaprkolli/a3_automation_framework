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
    String userName = null;
    String password = null;

    @BeforeClass
    public void initializePages() throws IOException {
        loginPage = new LoginPage(driver);
        productsPage = new ProductsPage(driver);
        menuPage = new MenuPage(driver);
        userName = ReadDataFromExcel.readDataFromExcel("userData", "users", "performance", "UserName");
        password = ReadDataFromExcel.readDataFromExcel("userData", "users", "performance", "Password");
    }

    // mvn test -DfilePath=testNgSuites/a3suite
    @Test
    public void verifyLoginSuccessWithValidCredentials() {
        loginPage.enterCredentials(userName, password);
        loginPage.clickOnLoginButton();
        int numberOfProducts = productsPage.getNumberOfProducts();
        Assert.assertEquals(numberOfProducts, 5);
        menuPage.logout();
    }

}
