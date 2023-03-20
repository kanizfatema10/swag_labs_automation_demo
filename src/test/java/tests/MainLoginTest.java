package tests;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.Base;
import model.Credentials;
import model.InventoryLocators;
import model.MainLoginLocators;
import model.TestData;
import model.UtilMethods;
import pages.MainLoginPage;


public class MainLoginTest extends Base{

    RemoteWebDriver driver;
    Credentials credentials = new Credentials();   

    @Test(dataProvider = "inputs", dataProviderClass = TestData.class)
    public void mainLoginTest(String userName, String password){

        driver = super.getActiveDriver();
        MainLoginPage mainLoginPage = new MainLoginPage(driver);

        mainLoginPage.openHomePage();   
        UtilMethods.waitForSeconds(0.5); 

        mainLoginPage.setUserName(userName);
        UtilMethods.waitForSeconds(0.5);  
        mainLoginPage.setPassword(password);
        UtilMethods.waitForSeconds(0.5);  
        mainLoginPage.clickOnLogin();
        UtilMethods.waitForSeconds(3);
        
        if (userName == "standard_user" && password == "secret_sauce") {
            WebElement findAppLogoNameClass = driver.findElement(InventoryLocators.appLogoName);
            String actualAppLogoName = findAppLogoNameClass.getText();
            String expectedAppLogoName = "Swag Labs";

            Assert.assertEquals(actualAppLogoName, expectedAppLogoName);
            
        } else {
            WebElement findH3HeaderText = driver.findElement(MainLoginLocators.errorMessageForInvalidCredential);
            String actualErrorMessage = findH3HeaderText.getText();
            String expectedErrorMessage = "Epic sadface: Username and password do not match any user in this service";
            
            Assert.assertEquals(actualErrorMessage, expectedErrorMessage);
        }
    }
}
    

    


