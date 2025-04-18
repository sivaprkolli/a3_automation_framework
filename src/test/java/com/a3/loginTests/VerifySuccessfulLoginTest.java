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
    String email = null;
    String pwd = null;

    @BeforeClass
    public void initializePages() throws IOException {
        loginPage = new LoginPage(driver);
        productsPage = new ProductsPage(driver);
        menuPage = new MenuPage(driver);
        email = ReadDataFromExcel.readDataFromExcel("userData", "users", "performance", "UserName");
        pwd = ReadDataFromExcel.readDataFromExcel("userData", "users", "performance", "Password");
    }

    // mvn test -DfilePath=testNgSuites/a3suite
    @Test(dataProvider = "userCredentials", dataProviderClass = ReadDataFromExcel.class)
    public void verifyLoginSuccessWithValidCredentials(String number, String userName, String password) {
        if (userName != null) {
            loginPage.enterCredentials(userName, password);
            loginPage.clickOnLoginButton();
            int numberOfProducts = productsPage.getNumberOfProducts();
            Assert.assertEquals(numberOfProducts, 6);
            menuPage.logout();
        }
    }

}
