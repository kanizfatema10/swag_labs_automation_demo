package tests;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.Base;
import model.Credentials;
import model.InventoryLocators;
import model.MainLoginLocators;
import model.UtilMethods;
import pages.MainLoginPage;


public class MainLoginTest extends Base{

    RemoteWebDriver driver;
    Credentials credentials = new Credentials();   
    
    @Test
    public void loginTestWithInvalidCredential(){
        driver = super.getActiveDriver();
        MainLoginPage mainLoginPage = new MainLoginPage(driver);

        mainLoginPage.openHomePage();   
        UtilMethods.waitForSeconds(0.5);  
        mainLoginPage.setInvalidUserName("test");
        UtilMethods.waitForSeconds(0.5);  
        mainLoginPage.setPassword(credentials.loginPasswordInputText);
        UtilMethods.waitForSeconds(0.5);  
        mainLoginPage.clickOnLogin();
        UtilMethods.waitForSeconds(3);


        WebElement findH3HeaderText = driver.findElement(MainLoginLocators.errorMessageForInvalidCredential);
        String actualErrorMessage = findH3HeaderText.getText();
        String expectedErrorMessage = "Epic sadface: Username and password do not match any user in this service";
        
        Assert.assertEquals(actualErrorMessage, expectedErrorMessage);

        driver.navigate().refresh();
        UtilMethods.waitForSeconds(1.5);

        mainLoginPage.setUserName(credentials.loginUserNameInputText);;
        UtilMethods.waitForSeconds(0.5);  
        mainLoginPage.setInvalidPassword("1234");
        UtilMethods.waitForSeconds(0.5);  
        mainLoginPage.clickOnLogin();
        UtilMethods.waitForSeconds(3);
        
        Assert.assertEquals(actualErrorMessage, expectedErrorMessage);

        driver.navigate().refresh();
        UtilMethods.waitForSeconds(1.5);

        mainLoginPage.setUserName(credentials.loginUserNameInputText);
        UtilMethods.waitForSeconds(0.5);  
        mainLoginPage.setPassword(credentials.loginPasswordInputText);
        UtilMethods.waitForSeconds(0.5);  
        mainLoginPage.clickOnLogin();
        UtilMethods.waitForSeconds(3);

        WebElement findAppLogoNameClass = driver.findElement(InventoryLocators.appLogoName);
        String actualAppLogoName = findAppLogoNameClass.getText();
        String expectedAppLogoName = "Swag Labs";

        Assert.assertEquals(actualAppLogoName, expectedAppLogoName);
    }
    
}


