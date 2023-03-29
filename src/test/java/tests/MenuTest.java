package tests;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.Base;
import model.Credentials;
import model.UtilMethods;
import pages.InventoryPage;
import pages.MainLoginPage;

public class MenuTest extends Base{
    RemoteWebDriver driver;
    Credentials credentials = new Credentials();


    @Test
    public void menuTest(){
        driver = super.getActiveDriver();
        MainLoginPage mainLoginPage = new MainLoginPage(driver);
        InventoryPage inventoryPage = new InventoryPage(driver);

        mainLoginPage.openHomePage();   
        UtilMethods.waitForSeconds(0.5); 

        mainLoginPage.setUserName(credentials.loginUserNameInputText);
        UtilMethods.waitForSeconds(0.5);  
        mainLoginPage.setPassword(credentials.loginPasswordInputText);
        UtilMethods.waitForSeconds(0.5);  
        mainLoginPage.clickOnLogin();
        UtilMethods.waitForSeconds(1);
        
        inventoryPage.clickOnBurgerMenu();
        UtilMethods.waitForSeconds(1);
        inventoryPage.clickOnAboutMenu();
        UtilMethods.waitForSeconds(2);
        String expectedAboutPageURL = "https://saucelabs.com/";
        String actualAboutPageURL = inventoryPage.getAboutPageURL();

        Assert.assertEquals(actualAboutPageURL, expectedAboutPageURL);
        System.out.println("About menu is working fine");
        driver.navigate().back();
        UtilMethods.waitForSeconds(1);

        inventoryPage.clickOnLogoutMenu();
        UtilMethods.waitForSeconds(1);
        String expectedLoginPageURL = "https://www.saucedemo.com/";
        String actualLoginPageURL = inventoryPage.getAboutPageURL();
        
        Assert.assertEquals(actualLoginPageURL, expectedLoginPageURL);
        System.out.println("Logout is working fine");

    }


}
